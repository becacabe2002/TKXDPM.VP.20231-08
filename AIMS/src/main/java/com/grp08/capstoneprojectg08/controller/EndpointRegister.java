package com.grp08.capstoneprojectg08.controller;

import com.grp08.capstoneprojectg08.request.BaseRequest;
import com.grp08.capstoneprojectg08.request.RequestMethod;
import com.grp08.capstoneprojectg08.response.BaseResponse;
import com.grp08.capstoneprojectg08.response.ResponseCode;

/**
 * @author <a href="https://github.com/becacabe2002">becacabe2002</a>
 */
public class EndpointRegister {
    private final HomeController homeController = new HomeController();
    private final PaymentController paymentController = new PaymentController();
    private final PlaceOrderController placeOrderController = new PlaceOrderController();
    private final PlaceRushOrderController placeRushOrderController = new PlaceRushOrderController();
    private final ViewCartController viewCartController = new ViewCartController();

    public EndpointRegister(){
    }

    public BaseResponse handleRequest(BaseRequest baseRequest){
        BaseResponse baseResponse = null;
        RequestMethod requestMethod = baseRequest.getMethod();
        String endpoint = baseRequest.getEndpoint();
        if (requestMethod == RequestMethod.POST){
            // TODO: implement post requests
            if(baseRequest.getEndpoint().equals("/order/payment")){
                return paymentController.processPayment(baseRequest);
            }

        } else if(requestMethod == RequestMethod.GET){
            // TODO: implement get requests
            if(baseRequest.getEndpoint().equals("/home/medias")){
                return homeController.getFilterMedia(baseRequest);
            }
            if(baseRequest.getEndpoint().equals("/cart/check-unavailable")){
                return placeOrderController.checkCartItemsAvailability();
            }

        } else if(requestMethod == RequestMethod.PUT){
            // TODO: implement put requests

        } else if(requestMethod == RequestMethod.DELETE){
            // TODO: implement delete requests
            // right now, we don't have any delete requests

        }
        else{
            baseResponse = new BaseResponse();
            baseResponse.setResponseCode(ResponseCode.BAD_REQUEST);
            baseResponse.setResponseMessage("Invalid request");
            baseResponse.setBody(null);
        }
        return baseResponse;
    }
}
