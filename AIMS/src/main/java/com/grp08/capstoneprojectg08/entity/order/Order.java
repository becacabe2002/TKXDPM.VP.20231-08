package com.grp08.capstoneprojectg08.entity.order;

import com.grp08.capstoneprojectg08.entity.cart.Cart;
import com.grp08.capstoneprojectg08.entity.cart.CartItem;
import com.grp08.capstoneprojectg08.entity.delivery.DeliveryInfo;

import java.util.ArrayList;
import java.util.List;


public class Order {
    // Order contains OrderItems, DeliveryInfo and Invoice
    private int id;
    private List<OrderItem> orderItems = new ArrayList<OrderItem>();
    private int shippingFees = 0;
    private DeliveryInfo deliveryInfo = null;

    public Order(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public int getShippingFees() {
        return shippingFees;
    }

    public void setShippingFees(int shippingFees) {
        this.shippingFees = shippingFees;
    }

    public DeliveryInfo getDeliveryInfo() {
        return deliveryInfo;
    }

    public void setDeliveryInfo(DeliveryInfo deliveryInfo) {
        this.deliveryInfo = deliveryInfo;
    }

    public void fromCartToOrder(Cart cart){
        for(CartItem cartItem : cart.getCartItems()){
            OrderItem orderItem = new OrderItem(cartItem);
            orderItems.add(orderItem);
        }
    }

}
