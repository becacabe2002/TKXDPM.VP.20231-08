package com.grp08.capstoneprojectg08.repository;

import com.grp08.capstoneprojectg08.entity.user.OrderHistory;
import com.grp08.capstoneprojectg08.entity.user.User;
import com.grp08.capstoneprojectg08.entity.user.UserRole;
import com.grp08.capstoneprojectg08.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// Used on table User, orderHistory
public interface UserRepo extends BaseRepo{

    //get all Order History of a user -> next, use OrderRepo.findOrderById() to get Order
    public List<OrderHistory> findAllOrderHistoryByUsername(String uname);

    public List<User> findAllUserBaseOnRole(UserRole role);

    public User findUserByUsername(String uname);
}
