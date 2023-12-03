package com.grp08.capstoneprojectg08.repository;

import com.grp08.capstoneprojectg08.entity.user.OrderHistory;
import com.grp08.capstoneprojectg08.entity.user.User;
import com.grp08.capstoneprojectg08.entity.user.UserRole;
import com.grp08.capstoneprojectg08.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserRepoImplement implements UserRepo{
    private static Connection mysqlConnection = DatabaseConnection.getConnectionMySQL();
    private static PreparedStatement ppStatement = null;
    private static Statement statement = null;
    private static ResultSet resultSet = null;

    @Override
    public List<OrderHistory> findAllOrderHistoryByUsername(String uname) {
        List<OrderHistory> orderHistories = new ArrayList<>();
        String queryUser = "select externalUID from users where uname = ? limit 1";
        String queryOrderHistory = "select * from orderHistory where uid = ?";
        // get user's UUID
        try{
            ppStatement = mysqlConnection.prepareStatement(queryUser);
            ppStatement.setString(1, uname);
            resultSet = ppStatement.executeQuery();
            resultSet.next();
            UUID uid = UUID.fromString(resultSet.getString("externalUID"));

            // get order history
            ppStatement = mysqlConnection.prepareStatement(queryOrderHistory);
            ppStatement.setString(1, uid.toString());
            resultSet = ppStatement.executeQuery();
            while(resultSet.next()){
                OrderHistory temp = new OrderHistory();
                temp.setPaid(resultSet.getBoolean("paid"));
                temp.setExternalUID(uid);
                temp.setOrder(orderRepo.findOrderById(resultSet.getInt("orderId")));
                orderHistories.add(temp);
            }

        } catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return orderHistories;
    }

    @Override
    public List<User> findAllUserBaseOnRole(UserRole role){ // UserRole.admin
        String query = "select * from users where role = ?;";
        List<User> listUser = new ArrayList<>();
        try{
            ppStatement = mysqlConnection.prepareStatement(query);
            ppStatement.setString(1, role.toString());
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                listUser.add(new User(
                        resultSet.getString("uname"),
                        UUID.fromString(resultSet.getString("uid")),
                        resultSet.getString("role")
                ));
            }
        } catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return listUser;
    }

    @Override
    public User findUserByUsername(String uname){
        User user = null;
        String query = "select * from users where uname = ?";
        try{
            ppStatement = mysqlConnection.prepareStatement(query);
            ppStatement.setString(1, uname);
            resultSet = ppStatement.executeQuery();
            if(resultSet.next()){
                user = new User(
                        resultSet.getString("uname"),
                        UUID.fromString(resultSet.getString("uid")),
                        resultSet.getString("role")
                );
            }
            if(user != null){
                user.setOrderHistories(findAllOrderHistoryByUsername(uname));
            }
        } catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return user;
    }
}
