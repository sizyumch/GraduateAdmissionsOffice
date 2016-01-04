package com.epam.gao.dao;

import com.epam.gao.dao.interfaces.IUserTypeDAO;
import com.epam.gao.datasource.FakeConnectionPool;
import com.epam.gao.datasource.IConnectionPool;
import com.epam.gao.entity.UserType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLUserTypeDAO implements IUserTypeDAO{
    private final static String SQL_USER_TYPE_BY_ID = "SELECT * FROM userType WHERE id = ? LIMIT 1";

    @Override
    public UserType findUserType(int id) {
        IConnectionPool connectionPool = new FakeConnectionPool();
        UserType userType = null;
        try {
            Connection connection = connectionPool.getConnection();
            try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_USER_TYPE_BY_ID)) {
                preparedStatement.setInt(1, id);
                try(ResultSet resultSet = preparedStatement.executeQuery()) {
                    resultSet.next();
                    userType = new UserType();
                    userType.setId(resultSet.getInt("id"));
                    userType.setName(resultSet.getString("name"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userType;
    }
}
