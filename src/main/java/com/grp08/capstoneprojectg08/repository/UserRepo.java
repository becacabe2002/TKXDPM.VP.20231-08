package com.grp08.capstoneprojectg08.repository;

import com.grp08.capstoneprojectg08.entity.order.Order;
import com.grp08.capstoneprojectg08.entity.user.OrderHistory;
import com.grp08.capstoneprojectg08.entity.user.User;
import com.grp08.capstoneprojectg08.entity.user.UserRole;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// Used on table User, orderHistory
public class UserRepo extends BaseRepo{
    //get all Order History of a user -> next, use OrderRepo.findOrderById() to get Order
    public List<OrderHistory> findAllOrderHistoryByUsername(String uname){
        List<OrderHistory> orderHistories = new ArrayList<>();
        String queryUser = "select externalUID from users where uname = ? limit 1";
        String queryOrderHistory = "select * from orderHistory where uid = ?";
        // get user's UUID
        try{
            ppStatement = dbConnection.prepareStatement(queryUser);
            ppStatement.setString(1, uname);
            resultSet = ppStatement.executeQuery();
            resultSet.next();
            UUID uid = UUID.fromString(resultSet.getString("externalUID"));

            // get order history
            ppStatement = dbConnection.prepareStatement(queryOrderHistory);
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
    public List<User> findAllUserBaseOnRole(UserRole role){ // UserRole.admin
        String query = "select * from users where role = ?;";
        List<User> listUser = new ArrayList<>();
        try{
            ppStatement = dbConnection.prepareStatement(query);
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

    public User findUserByUsername(String uname){
        User user = null;
        String query = "select * from users where uname = ?";
        try{
            ppStatement = dbConnection.prepareStatement(query);
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
