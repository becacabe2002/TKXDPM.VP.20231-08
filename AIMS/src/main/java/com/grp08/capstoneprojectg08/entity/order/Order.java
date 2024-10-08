package com.grp08.capstoneprojectg08.entity.order;

import com.grp08.capstoneprojectg08.entity.cart.Cart;
import com.grp08.capstoneprojectg08.entity.cart.CartItem;
import com.grp08.capstoneprojectg08.entity.delivery.DeliveryInfo;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="https://github.com/becacabe2002">becacabe2002</a>
 */
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

    public JSONObject toJSON(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", this.id);
        jsonObject.put("shippingFees", this.shippingFees);
        jsonObject.put("deliveryInfo", this.deliveryInfo.toJSON());
        JSONArray jsonArray = new JSONArray();
        for(OrderItem orderItem : this.orderItems){
            jsonArray.put(orderItem.toJSON());
        }
        jsonObject.put("orderItems", jsonArray);
        return jsonObject;
    }
}
