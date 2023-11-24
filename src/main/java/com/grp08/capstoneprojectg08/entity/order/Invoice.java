package com.grp08.capstoneprojectg08.entity.order;

import com.grp08.capstoneprojectg08.entity.cart.Cart;

public class Invoice {
    private int totalAmount = 0;
    private Order order = null;

    public Invoice(){
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
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
