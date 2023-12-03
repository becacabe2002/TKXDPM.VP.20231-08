package com.grp08.capstoneprojectg08.util;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import io.github.cdimascio.dotenv.Dotenv;

public class DatabaseConnection {
    private static Dotenv dotenv = Dotenv.load();
    public static Connection getConnectionMySQL(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
             return DriverManager.getConnection("jdbc:mysql://" + dotenv.get("MYSQL_DATABASE_HOST"), dotenv.get("MYSQL_DATABASE_USER"), dotenv.get("MYSQL_DATABASE_PASSWORD"));
        } catch(SQLException | ClassNotFoundException e){
            System.err.println("ConnectionUtil: " + e.getMessage());
            return null;
        }
    }

    public static MongoClient getMongoClient(){
        try{
            return MongoClients.create("mongodb://" + dotenv.get("MONGODB_URL"));
        } catch (Exception e){
            System.err.println("ConnectionUtil: " + e.getMessage());
            return null;
        }
    }
}
