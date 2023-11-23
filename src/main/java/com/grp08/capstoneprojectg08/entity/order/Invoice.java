package com.grp08.capstoneprojectg08.entity.order;

import com.grp08.capstoneprojectg08.entity.cart.Cart;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Invoice {
    private int totalAmount = 0;
    private Order order = null;

    public Invoice(){
    }

    public Invoice(Cart cart){
        this.order = new Order();
        this.order.fromCartToOrder(cart);
        for(OrderItem orderItem : this.order.getOrderItems()){
            this.totalAmount += orderItem.getSubPrice();
        }
        this.totalAmount = (int) cart.getTotal() + order.getShippingFees();
    }
}
