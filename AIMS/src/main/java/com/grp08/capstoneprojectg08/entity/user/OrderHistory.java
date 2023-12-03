package com.grp08.capstoneprojectg08.entity.user;

import com.grp08.capstoneprojectg08.entity.order.Invoice;
import com.grp08.capstoneprojectg08.entity.order.Order;
import com.grp08.capstoneprojectg08.repository.OrderRepo;

import java.util.UUID;


public class OrderHistory {
    private Order order = null;
    private UUID externalUID;
    private boolean paid = false;

    public OrderHistory(){
    }
    public Invoice getOrderInvoice(){
        if (order == null) return null;
        else{
            return OrderRepo.findInvoiceOfOrderId(order.getId());
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
}
