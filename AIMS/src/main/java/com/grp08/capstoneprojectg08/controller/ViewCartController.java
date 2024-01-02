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

    /**
     * Return info of cart:
     * <ul>
     *     <li>number of cart items: "numberOfItems"</li>
     *     <li>list of cart items: "cartItems"</li>
     *     <li>subtotal price: "subTotal"</li>
     *     <li>vat: "vat"</li>
     *     <li>total (subtotal + vat): "total"</li>
     * </ul>
     */
    public BaseResponse getCartInfo(){
        BaseResponse response = new BaseResponse();
        response.setResponseCode(ResponseCode.OK);
        response.setResponseMessage("");
        JSONObject cartJSON = UserSession.getInstance().getCart().toJson();
        response.setBody(cartJSON);
        return response;
    }

    /**
     * change quantity of a cart item, then recalculate price
     * @param request body contains: mediaId(int), change(int)
     * @return response, can be used to display dialog
     * <ul>
     *     <li>{@link ResponseCode#OK} -> Quantity changed</li>
     *     <li>{@link ResponseCode#BAD_REQUEST} -> passed request body is invalid</li>
     * </ul>
     */

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

    /**
     * Add item to cart
     * @param request body contains: mediaId(int), quantity(int)
     * @return response
     * <ul>
     *     <li>{@link ResponseCode#OK} -> Media Item added to cart</li>
     *     <li>{@link ResponseCode#ITEM_ALREADY_EXIST} -> Media Item already in cart</li>
     *     <li>{@link ResponseCode#BAD_REQUEST} -> passed request body is invalid</li>
     * </ul>
     */
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

    /**
     * Check if items in cart are still available
     * <br> Need to called before create order
     * @return response which body contains list of unavailable cart items ("unavailableList")
     */
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

    /**
     * Remove item from cart
     * @param request body contains: mediaId(int)
     * @return response: OK (item removed from cart), BAD_REQUEST (invalid request body)
     */
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
