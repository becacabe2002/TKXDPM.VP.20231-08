package com.grp08.capstoneprojectg08.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnection {
    public static Connection getConnection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
             return DriverManager.getConnection("jdbc:mysql://localhost:3306/aims", "root", "29123498Mysql");
        } catch(SQLException | ClassNotFoundException e){
            System.err.println("ConnectionUtil: " + e.getMessage());
            return null;
        }
    }
}
