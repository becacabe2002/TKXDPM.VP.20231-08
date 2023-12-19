package com.grp08.capstoneprojectg08.entity.order;

import com.grp08.capstoneprojectg08.entity.cart.CartItem;
import org.json.JSONObject;

// entity for table orderInfo
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

    public int getMediaId() {
        return mediaId;
    }

    public void setMediaId(int mediaId) {
        this.mediaId = mediaId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSubPrice() {
        return subPrice;
    }

    public void setSubPrice(int subPrice) {
        this.subPrice = subPrice;
    }
    public JSONObject toJSON(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("mediaId", this.mediaId);
        jsonObject.put("quantity", this.quantity);
        jsonObject.put("subPrice", this.subPrice);
        return jsonObject;
    }
}
