package com.grp08.capstoneprojectg08.entity.cart;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private final List<CartItem> cartItems;
    private float subTotal;
    private float vat; // 10% of subTotal
    private float total; // = subTotal + vat

    public Cart(){
        this.cartItems = new ArrayList<CartItem>();
        this.subTotal = this.vat = this.total = 0;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public float getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(float subTotal) {
        this.subTotal = subTotal;
    }

    public float getVat() {
        return vat;
    }

    public void setVat(float vat) {
        this.vat = vat;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public void calculate(){
        subTotal = 0;
        for(CartItem cartItem : cartItems){
            subTotal += cartItem.getSubPrice();
        }
        this.vat = subTotal * 0.1f;
        this.total = subTotal + vat;
    }

    private void addItem(int itemId, int quantity){
        boolean check = false;
        for(CartItem cartItem : cartItems){
            if(cartItem.getMediaId() == itemId){
                check = true;
                break;
            }
        }
        if(!check){
            CartItem cartItem = new CartItem(itemId, quantity);
            cartItems.add(cartItem);
            calculate();
        }
    }
    private void removeItem(int itemId){
        for(CartItem cartItem : cartItems){
            if(cartItem.getMediaId() == itemId){
                cartItems.remove(cartItem);
                break;
            }
        }
        calculate();
    }
}
