package com.grp08.capstoneprojectg08.repository;

import com.grp08.capstoneprojectg08.entity.media.Media;
import com.grp08.capstoneprojectg08.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// Use for query on tables media, book, cd, dvd
public class MediaRepo extends BaseRepo{
    public Media findMediaById(int id){
        String script = "Select * from Media where id = ? limit 1;";
        try{
            ppStatement = dbConnection.prepareStatement(script);
            ppStatement.setInt(1, id);
            resultSet = ppStatement.executeQuery();
            if(resultSet.next()){
                return new Media(
                        resultSet.getInt("id"),
                        resultSet.getString("category"),
                        resultSet.getInt("price"),
                        resultSet.getInt("quantity"),
                        resultSet.getString("title"),
                        resultSet.getInt("value"),
                        resultSet.getString("imageUrl"),
                        resultSet.getBoolean("fastShipping")
                );
            } else return null;
        } catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return null;
    }
}
