package com.grp08.capstoneprojectg08.entity.user;

import com.grp08.capstoneprojectg08.entity.order.Invoice;
import com.grp08.capstoneprojectg08.entity.order.Order;
import com.grp08.capstoneprojectg08.repository.OrderRepo;
import com.grp08.capstoneprojectg08.repository.OrderRepoImplement;
import org.json.JSONObject;

import java.util.UUID;

/**
 * @author <a href="https://github.com/becacabe2002">becacabe2002</a>
 */
public class OrderHistory {
    private Order order = null;
    private UUID externalUID;
    private boolean paid = false;

    public OrderHistory(){
    }
    public Invoice getOrderInvoice(){
        if (order == null) return null;
        else{
            return new OrderRepoImplement().findInvoiceOfOrderId(order.getId());
        }
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public UUID getExternalUID() {
        return externalUID;
    }

    public void setExternalUID(UUID externalUID) {
        this.externalUID = externalUID;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public JSONObject toJSON(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("externalUID", this.externalUID.toString());
        jsonObject.put("paid", this.paid);
        jsonObject.put("order", this.order.toJSON());
        return jsonObject;
    }
}
