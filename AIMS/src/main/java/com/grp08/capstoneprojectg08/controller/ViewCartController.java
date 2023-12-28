package com.grp08.capstoneprojectg08.controller;

import com.grp08.capstoneprojectg08.entity.cart.CartItem;
import com.grp08.capstoneprojectg08.entity.media.Media;
import com.grp08.capstoneprojectg08.util.UserSession;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="https://github.com/becacabe2002">becacabe2002</a>
 */
public class ViewCartController extends BaseController{
    public ViewCartController(){
        super();
    }

    // change quantity of a cart item, then recalculate price
    // TODO: already refactor to CartService
    //  -> receive BaseRequest which contain mediaId and change
    // return BaseResponse success
    public void increaseQuantity(int mediaId){
        cartService.changeMediaItemQuantityInCart(mediaId, 1);
    }
    public void decreaseQuantity(int mediaId){
        cartService.changeMediaItemQuantityInCart(mediaId, -1);
    }

    // return the list of CartItem is not available (quantity > stock)
    // if size of list is 0, all items are available -> proceed to checkout
    // if size of list is bigger than 0 -> display error message, user can't checkout
    // TODO: refactor to CartService
    public List<CartItem> checkAvailability(){
        List<CartItem> unavailableItemList = new ArrayList<>();
        for(CartItem cartItem : UserSession.getInstance().getCart().getCartItems()){
            if(cartItem.getQuantity() <=  cartItem.getMedia().getStockQuantity()){
                unavailableItemList.add(cartItem);
            }
        }
        return unavailableItemList;
    }
}
