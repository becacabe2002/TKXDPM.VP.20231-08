package com.grp08.capstoneprojectg08.entity.order;

import com.grp08.capstoneprojectg08.entity.cart.CartItem;
import lombok.Getter;
import lombok.Setter;

// entity for table orderInfo
@Getter
@Setter
public class OrderItem {
    // convert from CartItem to OrderItem
    private int mediaId;
    private int quantity;
    private int subPrice;

    public OrderItem(){
    }
    public OrderItem(CartItem cartItem){
        this.mediaId = cartItem.getMediaId();
        this.quantity = cartItem.getQuantity();
        this.subPrice = cartItem.getSubPrice();
    }
}
