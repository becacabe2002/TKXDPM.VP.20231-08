package com.grp08.capstoneprojectg08.controller;


import com.grp08.capstoneprojectg08.entity.cart.Cart;
import com.grp08.capstoneprojectg08.entity.cart.CartItem;
import com.grp08.capstoneprojectg08.entity.media.Media;
import com.grp08.capstoneprojectg08.entity.media.MediaCategory;
import com.grp08.capstoneprojectg08.request.AddItemCartRequest;
import com.grp08.capstoneprojectg08.request.BaseRequest;
import com.grp08.capstoneprojectg08.request.LoadHomeInfoRequest;
import com.grp08.capstoneprojectg08.response.BaseResponse;
import com.grp08.capstoneprojectg08.response.ResponseCode;
import com.grp08.capstoneprojectg08.util.UserSession;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="https://github.com/becacabe2002">becacabe2002</a>
 */
public class HomeController extends BaseController{

    public HomeController(){
        super();
        UserSession.getInstance();
        }

    /**
     * get Media base on its category and name filter
     * <br>Endpoint: {@code /home/medias}
     * @param request contains category(String) and name(String)
     * @return response contains list of media items ("mediaItems":[{mediaItem1}, {mediaItem2}, ...])
     */
    public BaseResponse getFilterMedia(
            BaseRequest request
//            LoadHomeInfoRequest request
    ){
        JSONObject jsonObject = request.getBody();
        try{
            String category = jsonObject.getString("category");
            String name = jsonObject.getString("name");
            List<? extends Media> mediaList = mediaService.getMediaByCategoryAndName(category, name);
            List<Media> returnList = new ArrayList<>(mediaList);
            BaseResponse response = new BaseResponse();
            response.setResponseCode(ResponseCode.OK);
            response.setResponseMessage("Success");
            JSONArray jsonArray = new JSONArray();
            for(Media media: returnList){
                jsonArray.put(media.toJSON());
            }
            response.setBody(new JSONObject().put("mediaItems", jsonArray));
            return response;

        } catch (JSONException e){
            return new BaseResponse(ResponseCode.BAD_REQUEST, "Invalid request body");
        } catch (IllegalArgumentException e){
            return new BaseResponse(ResponseCode.BAD_REQUEST, "Invalid category");
        }
    }

    /**
     * add Media to cart (if it not in cart)
     * <br>Endpoint: {@code /home/addItemToCart}
     * @param request contains mediaId(int) and quantity(int)
     * @return response contains responseCode(int) and responseMessage(String), empty body
     * <ul>
     *   <li>If Media Item has been already added to cart -> responseCode.ITEM_ALREADY_EXIST</li>
     *   <li>If Media Item has been added to cart successfully -> responseCode.OK</li>
     * </ul>
     */
    public BaseResponse addMediaToCart(BaseRequest request){
        JSONObject jsonObject = request.getBody();
        try{
            int mediaId = jsonObject.getInt("mediaId");
            int quantity = jsonObject.getInt("quantity");
            boolean result = cartService.addMediaToCart(mediaId, quantity);
            if(result){
                return new BaseResponse(ResponseCode.OK, "Success");
            } else {
                return new BaseResponse(ResponseCode.ITEM_ALREADY_EXIST, "Media item is already in cart");
            }
        } catch (JSONException e){
            return new BaseResponse(ResponseCode.BAD_REQUEST, "Invalid request body");
        }
    }

    // get number of items inside user cart
    public BaseResponse getNumberCartItems(){
        int number = cartService.getNumberOfCartItems();
        BaseResponse response = new BaseResponse();
        response.setResponseCode(ResponseCode.OK);
        response.setResponseMessage("Success");
        response.setBody(new JSONObject().put("number", number));
        return response;
    }
}
