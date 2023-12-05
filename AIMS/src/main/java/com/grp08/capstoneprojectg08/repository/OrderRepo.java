package com.grp08.capstoneprojectg08.repository;

import com.grp08.capstoneprojectg08.entity.order.Invoice;
import com.grp08.capstoneprojectg08.entity.order.Order;
import com.grp08.capstoneprojectg08.entity.order.OrderItem;
import com.grp08.capstoneprojectg08.util.DatabaseConnection;
import com.grp08.capstoneprojectg08.util.UserSession;

import java.sql.*;

// Used for query on table orderInfo, orderMedia, invoice
public interface OrderRepo extends BaseRepo{

    public int saveOrder(Order order);

    public int saveInvoice(Invoice invoice);

    public Order findOrderById(int orderId);

    public Invoice findInvoiceOfOrderId(int orderId);
}
