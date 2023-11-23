package com.grp08.capstoneprojectg08.entity.user;

import com.grp08.capstoneprojectg08.entity.order.Invoice;
import com.grp08.capstoneprojectg08.entity.order.Order;
import com.grp08.capstoneprojectg08.repository.OrderRepo;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class OrderHistory {
    private Order order = null;
    private UUID externalUID;
    private boolean paid = false;

    public OrderHistory(){
    }
    public Invoice getOrderInvoice(){
        if (order == null) return null;
        else{
            return new OrderRepo().findInvoiceOfOrderId(order.getId());
        }
    }

}
