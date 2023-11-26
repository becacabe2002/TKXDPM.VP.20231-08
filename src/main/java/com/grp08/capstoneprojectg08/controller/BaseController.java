package com.grp08.capstoneprojectg08.controller;

import com.grp08.capstoneprojectg08.entity.cart.Cart;
import com.grp08.capstoneprojectg08.entity.cart.CartItem;
import com.grp08.capstoneprojectg08.entity.media.Media;
import com.grp08.capstoneprojectg08.util.UserSession;

import java.util.List;

public class BaseController {

    // check if media item is in cart
    // if yes, return cartItem which contains its quantity
    // if no, return null
    public CartItem checkMediaItemInCart(int mediaId){
        List<CartItem> cartItemList = UserSession.getInstance().getCart().getCartItems();
        for(CartItem cartItem : cartItemList){
            if(cartItem.getMediaId() == mediaId){
                return cartItem;
            }
        }
        return null;
    }

    // get user cart
    public Cart getCart(){
        return UserSession.getInstance().getCart();
    }
}
