package com.grp08.capstoneprojectg08.service;

import org.json.JSONObject;

/**
 * @author <a href="https://github.com/becacabe2002">becacabe2002</a>
 */
public class VNPayPaymentStrategy implements PaymentStrategy{
    @Override
    public JSONObject processPayment() {
        // TODO: implement processPayment of VNPay
        // include create payment url from user session invoice
        // start webpage, pass url to it, and receive return url
        // turn return url into json object
        return null;
    }
    @Override
    public JSONObject savePayment(JSONObject result){
        // TODO: implement savePayment of VNPay
        // check if payment is successful
        // if yes, save json object to mongodb
        // return success message
        // if no, return error message
        return null;
    }
}
