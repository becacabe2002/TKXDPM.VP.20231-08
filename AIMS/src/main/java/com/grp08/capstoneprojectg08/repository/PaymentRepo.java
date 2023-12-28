package com.grp08.capstoneprojectg08.repository;

import com.grp08.capstoneprojectg08.entity.payment.VNPayTransaction;
import com.grp08.capstoneprojectg08.util.DatabaseConnection;
import com.grp08.capstoneprojectg08.util.UserSession;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.json.JSONObject;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author <a href="https://github.com/becacabe2002">becacabe2002</a>
 * <br> Query on table paymentTransaction, card
 */
public interface PaymentRepo extends BaseRepo{

    // save payment transaction
    public void savePaymentTransaction(JSONObject jsonObject);

    // get payment transaction by userUID
    public Document getPaymentTransactionByUserUID(UUID userUID);
}
