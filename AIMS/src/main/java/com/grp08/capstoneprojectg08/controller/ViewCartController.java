package com.grp08.capstoneprojectg08.controller;

import com.grp08.capstoneprojectg08.entity.cart.CartItem;
import com.grp08.capstoneprojectg08.entity.media.Media;
import com.grp08.capstoneprojectg08.request.BaseRequest;
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
public class ViewCartController extends BaseController{
    public ViewCartController(){
        super();
    }

    // return info of cart
    public BaseResponse getCartInfo(){
        BaseResponse response = new BaseResponse();
        response.setResponseCode(ResponseCode.OK);
        response.setResponseMessage("");
        JSONObject cartJSON = UserSession.getInstance().getCart().toJson();
        response.setBody(cartJSON);
        return response;
    }

    // change quantity of a cart item, then recalculate price
    // receive BaseRequest which contain mediaId and change
    public BaseResponse changeCartItemQuantity(BaseRequest request){
        JSONObject jsonObject = request.getBody();
        try{
            int mediaId = jsonObject.getInt("mediaId");
            int change = jsonObject.getInt("change");
            cartService.changeMediaItemQuantityInCart(mediaId, change);
            return new BaseResponse(ResponseCode.OK, "Success");
        } catch (Exception e){
            return new BaseResponse(ResponseCode.BAD_REQUEST, "Invalid request body");
        }
    }

    public BaseResponse addItemToCart(BaseRequest request){
        JSONObject jsonObject = request.getBody();
        try{
            int mediaId = jsonObject.getInt("mediaId");
            int quantity = jsonObject.getInt("quantity");
            if(cartService.addMediaToCart(mediaId, quantity)){
                return new BaseResponse(ResponseCode.OK, "Success");
            } else {
                return new BaseResponse(ResponseCode.ITEM_ALREADY_EXIST, "Media item is already in cart");
            }
        } catch (JSONException e){
            return new BaseResponse(ResponseCode.BAD_REQUEST, "Invalid request body");
        }
    }

    // check if cart item is still available, return list of unavailable cart items
    public BaseResponse checkCartItemsAvailability(){
        List<CartItem> unavailableCartItems = cartService.checkUnavailableMediaItemsInCart();
        JSONArray unavailableList = new JSONArray();
        for(CartItem cartItem: unavailableCartItems){
            JSONObject unavailableItem = cartItem.toJSON();
            unavailableList.put(unavailableItem);
        }
        JSONObject returnJSON = new JSONObject();
        returnJSON.put("unavailableList", unavailableList);
        BaseResponse response = new BaseResponse(ResponseCode.OK, "");
        response.setBody(returnJSON);
        return response;
    }

    // remove item from cart
    public BaseResponse removeItemFromCart(BaseRequest request){
        JSONObject jsonObject = request.getBody();
        try{
            int mediaId = jsonObject.getInt("mediaId");
            cartService.removeMediaItemFromCart(mediaId);
            return new BaseResponse(ResponseCode.OK, "Item removed from cart");
        } catch (JSONException e){
            return new BaseResponse(ResponseCode.BAD_REQUEST, "Invalid request body");
        }
    }
}
