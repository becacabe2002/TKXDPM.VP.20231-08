package com.grp08.capstoneprojectg08.repository;

import com.grp08.capstoneprojectg08.entity.payment.VNPayTransaction;
import com.grp08.capstoneprojectg08.util.DatabaseConnection;
import com.grp08.capstoneprojectg08.util.UserSession;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// query on table paymentTransaction, card
public class PaymentRepo {
    private VNPayTransaction vnpayTransaction = new VNPayTransaction();
//    private Connection mysqlConnection = DatabaseConnection.getConnectionMySQL();
//    private PreparedStatement ppStatement = null;
//    private Statement statement = null;
//    private ResultSet resultSet = null;
//    private OrderRepo orderRepo = new OrderRepo();
//    private UserRepo userRepo = new UserRepo();

    public PaymentRepo() {
    }
    // save payment transaction
    public void savePaymentTransaction(URL url){
        Document doc = vnpayTransaction.fromUrlToMongoDocument(url);
        if (doc == null){
            System.err.println("PaymentRepo: Couldn't parse url to mongo document");
            return;
        }
        String currentUserUID = UserSession.getInstance().getCurrentUserUID().toString();
        doc.append("userUID", currentUserUID);
        MongoClient mongoClient = DatabaseConnection.getMongoClient();
        assert mongoClient != null;
        MongoDatabase aimsDB = mongoClient.getDatabase("aims_2023");
        try{
            aimsDB.getCollection("transactions").insertOne(doc);
        } catch (Exception e){
            System.err.println("PaymentRepo: " + e.getMessage());
        }
    }

    // TODO: get payment transaction by userUID

}
