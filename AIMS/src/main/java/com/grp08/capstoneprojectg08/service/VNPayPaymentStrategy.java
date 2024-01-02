package com.grp08.capstoneprojectg08.service;

import com.grp08.capstoneprojectg08.entity.order.Invoice;
import com.grp08.capstoneprojectg08.repository.OrderRepo;
import com.grp08.capstoneprojectg08.repository.OrderRepoImplement;
import com.grp08.capstoneprojectg08.repository.TransactionRepo;
import com.grp08.capstoneprojectg08.repository.TransactionRepoImplement;
import com.grp08.capstoneprojectg08.util.UserSession;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.json.JSONObject;
import com.grp08.capstoneprojectg08.subsystem.vnpay.Config;
import com.grp08.capstoneprojectg08.subsystem.vnpay.VNPayPortalHandler;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author <a href="https://github.com/becacabe2002">becacabe2002</a>
 */
public class VNPayPaymentStrategy implements PaymentStrategy{
    private UserSession userSession = UserSession.getInstance();
    private OrderRepo orderRepo = new OrderRepoImplement();

    private TransactionRepo transactionRepo = new TransactionRepoImplement();
    private URL paymentUrl = null;
    private URL redirectUrl = null;
    //create paymentURL from user session invoice
    @Override
    public JSONObject processPayment() {
        // TODO: implement processPayment of VNPay
        // include create payment url from user session invoice


        try {
            paymentUrl = createPaymentURL(userSession.getInvoice());
        } catch (UnsupportedEncodingException | MalformedURLException e) {
            System.err.println("Error while creating payment url:" + e.getMessage());
            return null;
        }
        if(paymentUrl != null){
            VNPayPortalHandler VNPayPortalHandler = new VNPayPortalHandler();
            VNPayPortalHandler.setPaymentUrl(paymentUrl.toString());
            VNPayPortalHandler.setRedirectUrl(Config.vnp_ReturnUrl);

            // start webpage, pass url to it, and receive return url
            Platform.runLater(() -> {
                try{
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/grp08/capstoneprojectg08/fxml/vnpay-portal-webview.fxml"));
                    Parent root = fxmlLoader.load();
                    fxmlLoader.setController(VNPayPortalHandler);
                    Stage stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setScene(new Scene(root));
                    stage.showAndWait();

                    if(userSession.getResultPaymentAddress() != null){
                        redirectUrl = new URL(userSession.getResultPaymentAddress());
                    }
                } catch (Exception e){
                    System.err.println("Error while loading vnpay portal webview:" + e.getMessage());
                }
            });

            // turn return url into json object
            if (redirectUrl != null){
                String query = redirectUrl.getQuery();
                String[] params = query.split("&");
                Map<String, String> map = new HashMap<>();
                for(String param : params){
                    String[] keyValue = param.split("=");
                    map.put(keyValue[0], keyValue[1]);
                }
                JSONObject returnObject = new JSONObject(map);
                returnObject.put("paymentMethod", "VNPay");
                if(query.contains("vnp_ResponseCode=00")){
                    returnObject.put("paymentStatus", "success");
                } else {
                    returnObject.put("paymentStatus", "failed");
                }
                return returnObject;
            }
        }
        return null;
    }
    @Override
    public JSONObject savePayment(JSONObject result){
        // TODO: implement savePayment of VNPay
        // check if payment is successful
        if(result == null){
            return null;
        } else{
            if(result.getString("paymentStatus").equals("success")){
                // save payment to mongodb
                result.put("username", userSession.getUsername());
                int invoiceId = orderRepo.saveInvoice(userSession.getInvoice());
                result.put("invoiceId", invoiceId);
                transactionRepo.saveTransaction(result);
            }
            return result;
        }
    }


    public URL createPaymentURL(Invoice invoice) throws UnsupportedEncodingException, MalformedURLException {
        String vnp_Version = "2.1.0";
        String vnp_Command = "pay";
        String orderType = "other";
        long amount = (long) userSession.getInvoice().getTotalAmount() * 1000 * 100;

        String vnp_TxnRef = Config.getRandomNumber(8);
        String vnp_IpAddr = Config.getIpAddress();
        String vnp_TmnCode = Config.vnp_TmnCode;
        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", "VND");

        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang" + vnp_TxnRef);
        vnp_Params.put("vnp_OrderType", orderType);

        vnp_Params.put("vnp_Locale", "vn");
        vnp_Params.put("vnp_ReturnUrl", Config.vnp_ReturnUrl);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        List<String> fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        for (String fieldName : fieldNames) {
            String fieldValue = (String) vnp_Params.get(fieldName);
            if((fieldValue != null) && (!fieldValue.isEmpty())){
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII));
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII));
                if(fieldNames.indexOf(fieldName) != (fieldNames.size() - 1)){
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = Config.hmacSHA512(Config.secretKey, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = Config.vnp_PayUrl + "?" + queryUrl;
        return new URL(paymentUrl);
    }
}
