package com.grp08.capstoneprojectg08.controller;

import com.grp08.capstoneprojectg08.entity.cart.Cart;
import com.grp08.capstoneprojectg08.entity.cart.CartItem;
import com.grp08.capstoneprojectg08.entity.media.Media;
import com.grp08.capstoneprojectg08.repository.*;
import com.grp08.capstoneprojectg08.util.UserSession;
import javafx.scene.control.Alert;

import java.util.List;

public class BaseController {
    protected DeliveryRepo deliveryRepo = new DeliveryRepoImplement();
    protected ImageRepo imageRepo = new ImageRepoImplement();
    protected MediaRepo mediaRepo = new MediaRepoImplement();
    protected OrderRepo orderRepo = new OrderRepoImplement();
    protected PaymentRepo paymentRepo = new PaymentRepoImplement();
    protected UserRepo userRepo = new UserRepoImplement();
    protected Alert infomationAlert = new Alert(Alert.AlertType.INFORMATION);
    protected Alert errorAlert = new Alert(Alert.AlertType.ERROR);
    protected Alert warningAlert = new Alert(Alert.AlertType.WARNING);

    public BaseController(){
        infomationAlert.setTitle("Information");
        errorAlert.setTitle("Error");
        warningAlert.setTitle("Warning");
    }

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

    // TODO: get cart item numbers
}
