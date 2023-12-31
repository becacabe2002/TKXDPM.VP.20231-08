package com.grp08.capstoneprojectg08.service;

import com.grp08.capstoneprojectg08.entity.order.OrderItem;
import com.grp08.capstoneprojectg08.entity.payment.PaymentType;
import com.grp08.capstoneprojectg08.util.UserSession;
import org.json.JSONObject;

import java.util.List;

/**
 * @author <a href="https://github.com/becacabe2002">becacabe2002</a>
 */
public class PaymentService {
    private UserSession userSession = UserSession.getInstance();
    private MediaService mediaService = new MediaService();
    public PaymentService() {
    }
    public JSONObject processPayment(PaymentType type){
        PaymentStrategy paymentStrategy;
        JSONObject result = new JSONObject();
        if(type == PaymentType.PAYPAL){
            paymentStrategy = new PaypalPaymentStrategy();
            result =  paymentStrategy.savePayment(paymentStrategy.processPayment());
        }
        else if(type == PaymentType.VNPAY){
            paymentStrategy = new VNPayPaymentStrategy();
            result = paymentStrategy.savePayment(paymentStrategy.processPayment());
        }
        if(result == null){
            System.err.println("Error: Couldn't process payment");
            return null;
        } else{
            if(result.getString("paymentStatus").equals("success")){
                result.put("total", userSession.getInvoice().getTotalAmount());
                result.put("shippingAddress", userSession.getInvoice().getOrder().getDeliveryInfo().getAddress());
                // if payment success, delete media item number stock in database based on invoice
                List<OrderItem> orderItemList = userSession.getInvoice().getOrder().getOrderItems();
                for(OrderItem orderItem : orderItemList){
                    mediaService.updateMediaItemStock(orderItem.getMediaId(), orderItem.getQuantity());
                }
                //  and clear user session
                userSession.clearCurrentUser();
            } else{
                result = null;
            }
        }
        return result;
    }
}
