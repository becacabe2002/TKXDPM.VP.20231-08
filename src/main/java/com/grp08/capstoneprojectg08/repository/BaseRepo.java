package com.grp08.capstoneprojectg08.repository;

import com.grp08.capstoneprojectg08.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public abstract class BaseRepo {
    protected Connection dbConnection = DatabaseConnection.getConnection();
    protected PreparedStatement ppStatement = null;

    protected ResultSet resultSet = null;
}
