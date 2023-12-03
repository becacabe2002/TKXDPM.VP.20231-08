package com.grp08.capstoneprojectg08.repository;

import com.grp08.capstoneprojectg08.entity.payment.VNPayTransaction;
import com.grp08.capstoneprojectg08.util.DatabaseConnection;
import com.grp08.capstoneprojectg08.util.UserSession;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.net.URL;
import java.util.UUID;

public class PaymentRepoImplement implements PaymentRepo{

    @Override
    public void savePaymentTransaction(URL url){
        Document doc = VNPayTransaction.fromUrlToMongoDocument(url);
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
        mongoClient.close();
    }

    @Override
    public Document getPaymentTransactionByUserUID(UUID userUID){
        MongoClient mongoClient = DatabaseConnection.getMongoClient();
        assert mongoClient != null;
        MongoDatabase aimsDB = mongoClient.getDatabase("aims_2023");
        Document doc = aimsDB.getCollection("transactions").find(new Document("userUID", userUID.toString())).first();
        mongoClient.close();
        return doc;
    }
}
