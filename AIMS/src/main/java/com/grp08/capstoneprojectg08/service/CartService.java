package com.grp08.capstoneprojectg08.service;

import com.grp08.capstoneprojectg08.util.UserSession;

public class CartService {
    private UserSession userSession = UserSession.getInstance();
    public CartService() {
    }
    public int getNumberOfCartItems() {
        if (userSession.getCart().getCartItems().isEmpty()) {
            return 0;
        } else return userSession.getCart().getCartItems().size();
    }

    // TODO: add media to cart
    // TODO: check media item in cart
    // TODO: remove media item from cart
    // TODO: update media item quantity in cart
}
