package com.grp08.capstoneprojectg08.util;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DatabaseConnection {
    public static Connection getConnectionMySQL(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
             return DriverManager.getConnection("jdbc:mysql://localhost:3306/aims", "root", "29123498Mysql");
        } catch(SQLException | ClassNotFoundException e){
            System.err.println("ConnectionUtil: " + e.getMessage());
            return null;
        }
    }

    public static MongoDatabase getMongoDatabase(String dbName){
        try{
            MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
            return mongoClient.getDatabase(dbName);
        } catch (Exception e){
            System.err.println("ConnectionUtil: " + e.getMessage());
            return null;
        }
    }
}
