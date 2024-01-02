package com.grp08.capstoneprojectg08.repository;

import com.grp08.capstoneprojectg08.util.DatabaseConnection;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import io.github.cdimascio.dotenv.Dotenv;
import org.json.JSONObject;
import org.bson.Document;

public class TransactionRepoImplement implements TransactionRepo{
    private MongoClient mongoClient = DatabaseConnection.getMongoClient();
    private static Dotenv dotenv = Dotenv.load();
    @Override
    public boolean saveTransaction(JSONObject transactionObject) {
        MongoDatabase database = mongoClient.getDatabase(dotenv.get("MONGO_DB"));
        MongoCollection<Document> collection = database.getCollection("transactions");
        Document document = Document.parse(transactionObject.toString());
        try{
            collection.insertOne(document);
            mongoClient.close();
            return true;
        } catch (Exception e){
            System.err.println("Error while saving transaction to mongodb: " + e.getMessage());
            mongoClient.close();
            return false;
        }
    }
}
