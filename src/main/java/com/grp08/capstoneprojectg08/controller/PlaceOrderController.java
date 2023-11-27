package com.grp08.capstoneprojectg08.controller;

import com.grp08.capstoneprojectg08.entity.order.Invoice;
import com.grp08.capstoneprojectg08.entity.order.Order;
import com.grp08.capstoneprojectg08.entity.user.UserRole;
import com.grp08.capstoneprojectg08.util.UserSession;

public class PlaceOrderController extends BaseController{
    //

    // create invoice, attach order to it and set it to user session
    // check if user is logged in and is customer
    public void createOrder(){
        // check if user cart is not empty
       if(getCart().getCartItems().isEmpty()){
           warningAlert.setTitle("Warning");
           warningAlert.setHeaderText(null);
           warningAlert.setContentText("Your cart is empty. Please add item to cart before placing order.");
           warningAlert.showAndWait();
           return;
       } else if(UserSession.getInstance().getRole() != UserRole.user || UserSession.getInstance().getUsername() == null){
           warningAlert.setTitle("Warning");
           warningAlert.setHeaderText(null);
           warningAlert.setContentText("You must login as customer to place order.");
           warningAlert.showAndWait();
           return;
       }
       // create order from cart
        UserSession.getInstance().setInvoice(new Invoice(getCart()));
    }

    // TODO: validate input: name, phone, address, shipping instructions, (shipping time and Fast shipping instructions)

    // TODO: calculate shipping fee, update to deliveryInfo in invoice
    // return of pairs cartItem and its shipping fee
    // shipping fee of a cart item = 0.01 * random from 1 - 10 * subPrice of cartItem

    // TODO: update invoice with shipping


}
