package com.grp08.capstoneprojectg08.repository;

import com.grp08.capstoneprojectg08.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public abstract class BaseRepo {
    protected Connection dbConnection = DatabaseConnection.getConnection();
    protected PreparedStatement ppStatement = null;

    protected Statement statement = null;
    protected ResultSet resultSet = null;

    protected DeliveryRepo deliveryRepo = new DeliveryRepo();
    protected MediaRepo mediaRepo = new MediaRepo();
    protected OrderRepo orderRepo = new OrderRepo();

    protected PaymentRepo paymentRepo = new PaymentRepo();
    protected UserRepo userRepo = new UserRepo();
}
