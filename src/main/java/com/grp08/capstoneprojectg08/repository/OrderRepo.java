package com.grp08.capstoneprojectg08.repository;

import com.grp08.capstoneprojectg08.entity.order.Invoice;
import com.grp08.capstoneprojectg08.entity.order.Order;
import com.grp08.capstoneprojectg08.entity.order.OrderItem;
import com.grp08.capstoneprojectg08.util.DatabaseConnection;
import com.grp08.capstoneprojectg08.util.UserSession;

import java.sql.*;

// Used for query on table orderInfo, orderMedia, invoice
public class OrderRepo {

    private static Connection mysqlConnection = DatabaseConnection.getConnectionMySQL();
    private static PreparedStatement ppStatement = null;
    private static Statement statement = null;
    private static ResultSet resultSet = null;

//    private DeliveryRepo deliveryRepo = new DeliveryRepo();

//    public OrderRepo() {
//    }
    public static int saveOrder(Order order){
        // save OrderInfo
        int resOrderId = -1;
        String queryInsertOrderInfo = "insert into orderInfo(deliveryInfoId, shippingFees) values(?, ?)";
        String queryLastId = "select id from orderInfo order by id desc limit 1";
        try{
            ppStatement = mysqlConnection.prepareStatement(queryInsertOrderInfo);
            ppStatement.setInt(1, DeliveryRepo.saveDeliveryInfo(order.getDeliveryInfo()));
            ppStatement.setInt(2, order.getShippingFees());
            ppStatement.executeUpdate();

            ppStatement = mysqlConnection.prepareStatement(queryLastId);
            resultSet = ppStatement.executeQuery();
            resultSet.next();
            resOrderId = resultSet.getInt("id");

        } catch (SQLException e){
            System.err.println(e.getMessage());
        }
        // save OrderMedia
        String queryInsertOrderMedia = "insert into orderMedia(orderId, mediaId, quantity, price) values(?, ?, ?, ?)";
        try{
            ppStatement = mysqlConnection.prepareStatement(queryInsertOrderMedia);
            ppStatement.setInt(1, resOrderId);
            for(OrderItem orderItem : order.getOrderItems()){
                ppStatement.setInt(2, orderItem.getMediaId());
                ppStatement.setInt(3, orderItem.getQuantity());
                ppStatement.setInt(4, orderItem.getSubPrice());
                ppStatement.executeUpdate();
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        // save orderHistory
        String queryInsertOrderHistory = "insert into orderHistory(orderId, uid, paid) values(?, ?, ?)";
        try{
            ppStatement = mysqlConnection.prepareStatement(queryInsertOrderHistory);
            ppStatement.setInt(1, resOrderId);
            ppStatement.setString(2, UserSession.getInstance().getCurrentUserUID().toString());
            ppStatement.setBoolean(3, true); // set to be true
            ppStatement.executeUpdate();
        } catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return resOrderId;
    }


    public static int saveInvoice(Invoice invoice){
        /*
          save all information of invoice to database
         */
        int resInvoiceId = -1;

        int orderId = saveOrder(invoice.getOrder());
        int totalAmount = invoice.getTotalAmount();
//        int deliveryInfoId = deliveryRepo.saveDeliveryInfo(invoice.getOrder().getDeliveryInfo());
        String query = "insert into invoice(orderId, totalAmount) values(?, ?)";
        String queryLastId = "select id from invoice order by id desc limit 1";
        try{
            ppStatement = mysqlConnection.prepareStatement(query);
            ppStatement.setInt(1, orderId);
            ppStatement.setInt(2, totalAmount);
            ppStatement.executeUpdate();

            ppStatement = mysqlConnection.prepareStatement(queryLastId);
            resultSet = ppStatement.executeQuery();
            resultSet.next();
            resInvoiceId = resultSet.getInt("id");
        } catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return resInvoiceId;
    }

    public static Order findOrderById(int orderId){
        Order order = null;
        String query = "select * from orderInfo where id = ?";
        try {
            ppStatement = mysqlConnection.prepareStatement(query);
            ppStatement.setInt(1, orderId);
            resultSet = ppStatement.executeQuery();
            while(resultSet.next()){
                order = new Order();
                order.setId(resultSet.getInt("id"));
                order.setShippingFees(resultSet.getInt("shippingFees"));
                order.setDeliveryInfo(DeliveryRepo.findDeliveryInfoById(resultSet.getInt("deliveryInfoId")));
            }
            // get orderItems
            if (order != null) {
                query = "select * from orderMedia where orderId = ?";
                ppStatement = mysqlConnection.prepareStatement(query);
                ppStatement.setInt(1, orderId);
                resultSet = ppStatement.executeQuery();
                while (resultSet.next()) {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setMediaId(resultSet.getInt("mediaId"));
                    orderItem.setQuantity(resultSet.getInt("quantity"));
                    orderItem.setSubPrice(resultSet.getInt("price"));
                    order.getOrderItems().add(orderItem);
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return order;
    }
    public static Invoice findInvoiceOfOrderId(int orderId){
        Invoice invoice = null;
        String queryInvoice = "select * from invoice where orderId = ?";
        try{
            ppStatement = mysqlConnection.prepareStatement(queryInvoice);
            ppStatement.setInt(1, orderId);
            resultSet = ppStatement.executeQuery();
            while(resultSet.next()){
                invoice = new Invoice();
                invoice.setTotalAmount(resultSet.getInt("totalAmount"));
                invoice.setOrder(findOrderById(orderId));
            }
        } catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return invoice;
    }

}
