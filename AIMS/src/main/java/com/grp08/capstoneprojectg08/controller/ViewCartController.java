package com.grp08.capstoneprojectg08.controller;

import com.grp08.capstoneprojectg08.entity.cart.CartItem;
import com.grp08.capstoneprojectg08.entity.media.Media;
import com.grp08.capstoneprojectg08.util.UserSession;

import java.util.ArrayList;
import java.util.List;

public class ViewCartController extends BaseController{

    public ViewCartController(){
        super();
    }

    // change quantity of a cart item, then recalculate price
    public void increaseQuantity(int mediaId){
        checkMediaItemInCart(mediaId).addQuantity();
        UserSession.getInstance().getCart().calculate();
    }
    public void decreaseQuantity(int mediaId){
        checkMediaItemInCart(mediaId).minusQuantity();
        UserSession.getInstance().getCart().calculate();
    }

    // return the list of CartItem is not available (quantity > stock)
    // if size of list is 0, all items are available -> proceed to checkout
    // if size of list is bigger than 0 -> display error message, user can't checkout
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
