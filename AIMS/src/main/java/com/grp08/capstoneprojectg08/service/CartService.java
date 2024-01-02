package com.grp08.capstoneprojectg08.service;

import com.grp08.capstoneprojectg08.entity.cart.Cart;
import com.grp08.capstoneprojectg08.entity.cart.CartItem;
import com.grp08.capstoneprojectg08.util.UserSession;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="https://github.com/becacabe2002">becacabe2002</a>
 */
public class CartService {
    private Cart userCart = UserSession.getInstance().getCart();
    private MediaService mediaService = new MediaService();
    public CartService() {
    }
    public int getNumberOfCartItems() {
        if (userCart.getCartItems().isEmpty()) {
            return 0;
        } else return userCart.getCartItems().size();
    }

    // check media item in cart
    public CartItem checkMediaItemInCart(int mediaId) {
        for (CartItem cartItem : userCart.getCartItems()) {
            if (cartItem.getMediaId() == mediaId) {
                return cartItem;
            }
        }
        return null;
    }



    // add media to cart
    public boolean addMediaToCart(int mediaId, int quantity) {
        if (checkMediaItemInCart(mediaId) == null) {
            userCart.getCartItems().add(new CartItem(mediaId, quantity));
            userCart.calculate();
            return true;
        } else {
            return false; // return false if media item is already in cart
        }
    }

    // remove media item from cart
    public void removeMediaItemFromCart(int mediaId) {
        CartItem cartItem = checkMediaItemInCart(mediaId);
        if (cartItem != null) {
            userCart.getCartItems().remove(cartItem);
            userCart.calculate();
        }
    }

    // update media item quantity in cart
    public void changeMediaItemQuantityInCart(int mediaId, int change) {
        CartItem cartItem = checkMediaItemInCart(mediaId);
        if (cartItem != null) {
            cartItem.changeQuantity(change);
            userCart.calculate();
        }
    }

    public List<CartItem> checkUnavailableMediaItemsInCart(){
        List<CartItem> returnList = new ArrayList<>();
        for(CartItem cartItem : userCart.getCartItems()){
            if (mediaService.getMediaById(cartItem.getMediaId()).getStockQuantity() < cartItem.getQuantity()){
                returnList.add(cartItem);
            }
        }
        return returnList;
    }
}
