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
public interface PaymentRepo extends BaseRepo{

    // save payment transaction
    public void savePaymentTransaction(URL url);

    // get payment transaction by userUID
    public Document getPaymentTransactionByUserUID(UUID userUID);
}
