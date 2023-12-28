package com.grp08.capstoneprojectg08.controller;

import com.grp08.capstoneprojectg08.entity.payment.PaymentType;
import com.grp08.capstoneprojectg08.request.BaseRequest;
import com.grp08.capstoneprojectg08.response.BaseResponse;
import com.grp08.capstoneprojectg08.response.ResponseCode;
import com.grp08.capstoneprojectg08.service.PaymentService;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author <a href="https://github.com/becacabe2002">becacabe2002</a>
 */
public class PaymentController extends BaseController{

    public PaymentController() {
        super();
    }

    // save payment, order, invoice to database (only when payment success)
    // clear current userSession's invoice
    // receive BaseRequest, return BaseResponse
    // BaseRequest contains PaymentStrategy

    public BaseResponse processPayment(BaseRequest request){
        JSONObject jsonObject = request.getBody();
        try{
            PaymentType paymentType = PaymentType.valueOf(jsonObject.getString("paymentType"));
            PaymentService paymentService = new PaymentService();
            JSONObject returnJSON = paymentService.processPayment(paymentType);
            BaseResponse returnResponse = new BaseResponse(ResponseCode.OK, "");
            returnResponse.setBody(returnJSON);
            return returnResponse;
        } catch (JSONException e){
            return new BaseResponse(ResponseCode.BAD_REQUEST, "Invalid request body");
        }
    }
}
