package com.grp08.capstoneprojectg08;

import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class TestPaymentRedirectURL {
    public static void main(String[] args) {
        String redirectURL = "https://sandbox.vnpayment.vn/tryitnow/Home/VnPayReturn?vnp_Amount=1000000&vnp_BankCode=NCB&vnp_BankTranNo=VNP14266277&vnp_CardType=ATM&vnp_OrderInfo=Thanh+toan+don+hang+thoi+gian%3A+2023-12-31+21%3A17%3A59&vnp_PayDate=20231231211907&vnp_ResponseCode=00&vnp_TmnCode=CTTVNP01&vnp_TransactionNo=14266277&vnp_TransactionStatus=00&vnp_TxnRef=169880&vnp_SecureHash=a1768097298a4df7cf7302a8da547104a0d316149d1906f4ea9bcf7ec36b96da3d2e6350cc4419608d2603f2b3380a348eb15b15c6c1a424aa8ca26819ce0673";
        try{
            URL url = new URL(redirectURL);
            String query = url.getQuery();
            String[] params = query.split("&");
            Map<String, String> map = new HashMap<>();
            for(String param : params){
                String[] keyValue = param.split("=");
                map.put(keyValue[0], keyValue[1]);
            }
            JSONObject returnObject = new JSONObject(map);
            System.out.println(returnObject.toString());
        } catch(MalformedURLException e){
            System.out.println("MalformedURLException: " + e.getMessage());
        }
    }
}
