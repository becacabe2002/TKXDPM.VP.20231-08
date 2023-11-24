package com.grp08.capstoneprojectg08.repository;

import com.grp08.capstoneprojectg08.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

// query on table paymentTransaction, card
public class PaymentRepo {
    private Connection mysqlConnection = DatabaseConnection.getConnectionMySQL();
    private PreparedStatement ppStatement = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    private DeliveryRepo deliveryRepo = new DeliveryRepo();
    private MediaRepo mediaRepo = new MediaRepo();
    private OrderRepo orderRepo = new OrderRepo();

    private PaymentRepo paymentRepo = new PaymentRepo();
    private UserRepo userRepo = new UserRepo();
}
