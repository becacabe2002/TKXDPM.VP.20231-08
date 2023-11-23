package com.grp08.capstoneprojectg08.entity.order;

import com.grp08.capstoneprojectg08.entity.cart.Cart;
import com.grp08.capstoneprojectg08.entity.cart.CartItem;
import com.grp08.capstoneprojectg08.entity.delivery.DeliveryInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Order {
    // Order contains OrderItems, DeliveryInfo and Invoice
    private int id;
    private List<OrderItem> orderItems = new ArrayList<OrderItem>();
    private int shippingFees = 0;
    private DeliveryInfo deliveryInfo = null;

    public Order(){
    }

    public void fromCartToOrder(Cart cart){
        for(CartItem cartItem : cart.getCartItems()){
            OrderItem orderItem = new OrderItem(cartItem);
            orderItems.add(orderItem);
        }
    }
}
