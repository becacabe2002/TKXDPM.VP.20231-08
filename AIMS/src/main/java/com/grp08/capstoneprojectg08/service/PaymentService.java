package com.grp08.capstoneprojectg08.service;

import com.grp08.capstoneprojectg08.entity.payment.PaymentType;
import org.json.JSONObject;

/**
 * @author <a href="https://github.com/becacabe2002">becacabe2002</a>
 */
public class PaymentService {
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
        return result;
    }
}
