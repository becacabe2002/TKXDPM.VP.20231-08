package com.grp08.capstoneprojectg08.controller;

import com.grp08.capstoneprojectg08.entity.delivery.RushDeliveryInfo;
import com.grp08.capstoneprojectg08.entity.media.Media;
import com.grp08.capstoneprojectg08.entity.order.OrderItem;
import com.grp08.capstoneprojectg08.repository.MediaRepo;
import com.grp08.capstoneprojectg08.request.BaseRequest;
import com.grp08.capstoneprojectg08.response.BaseResponse;
import com.grp08.capstoneprojectg08.response.ResponseCode;
import com.grp08.capstoneprojectg08.util.DateUnion;
import com.grp08.capstoneprojectg08.util.UserSession;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="https://github.com/becacabe2002">becacabe2002</a>
 */
public class PlaceRushOrderController extends BaseController{

    public PlaceRushOrderController(){
        super();
    }

    // get the list of fast shipping supported order items
    public BaseResponse getFastShippingSupportedItems(){
        List<Media> supportList = orderService.getFastShippingSupportedOrderItems();
        BaseResponse response = new BaseResponse();
        response.setResponseCode(200);
        response.setResponseMessage("");
        JSONArray jsonArray = new JSONArray();
        for(Media media: supportList){
            jsonArray.put(media.toJSON());
        }
        JSONObject responseBody = new JSONObject();
        responseBody.put("fastShippingSupportedItems", jsonArray);
        response.setBody(responseBody);
        return response;
    }

    // update shipping fee of supported cart items
    // fast shipping fee = 130% of normal shipping fee
    public BaseResponse updateFastShippingFee(){
        orderService.updateFastShippingFee();
        return new BaseResponse(ResponseCode.OK, "Updated fast shipping fee");
    }


    // refactor to OrderService
    // update rush order information
    // input: Order reference, shipping time, rush delivery instructions
    public BaseResponse updateFastShippingInfo(BaseRequest baseRequest){
        try{
            JSONObject body = baseRequest.getBody();
            LocalDate shippingTime = LocalDate.parse(body.getString("time"));
            String rushDeliveryInstructions = body.getString("instructions");
            orderService.updateRushOrderInfo(shippingTime, rushDeliveryInstructions);
            return new BaseResponse(ResponseCode.OK, "Update rush delivery info");
        } catch (JSONException e){
            return new BaseResponse(ResponseCode.BAD_REQUEST, "Invalid request body");
        }
    }

}
