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

    /**
     * Process payment, if success:
     * <ul>
     *     <li>Save payment, order, invoice to database</li>
     *     <li>Clear current UserSession's invoice, cart, FeeMap</li>
     *     <li>Delete media item stock base on user order</li>
     * </ul>
     * @param request - contain paymentType: PAYPAL or VNPAY
     * @return response:
     * <ul>
     *     <li>if success: responseCode = OK, responseMessage = "Payment successful", body = {total, paymentMethod, shippingAddress, username, paymentStatus, invoiceId}</li>
     *     <li>if not success: responseCode = UNACCEPTABLE (406), responseMessage = "Payment failed, please try again!";</li>
     * </ul>
     */
    public BaseResponse processPayment(BaseRequest request){
        JSONObject jsonObject = request.getBody();
        try{
            PaymentType paymentType = PaymentType.valueOf(jsonObject.getString("paymentType"));
            PaymentService paymentService = new PaymentService();
            JSONObject returnJSON = paymentService.processPayment(paymentType);
            if (returnJSON == null){
                return new BaseResponse(ResponseCode.UNACCEPTABLE, "Payment failed, please try again!");
            } else{
                BaseResponse returnResponse = new BaseResponse(ResponseCode.OK, "Payment successful");
                returnResponse.setBody(returnJSON);
                return returnResponse;
            }
        } catch (JSONException e){
            return new BaseResponse(ResponseCode.BAD_REQUEST, "Invalid request body");
        }
    }
}
