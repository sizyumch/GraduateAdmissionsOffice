package com.epam.gao.datasource;

import java.sql.*;

public class FakeConnectionPool implements IConnectionPool{

    static{
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() throws SQLException{
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/graduate_admissions_office", "root", "root");
    }

    public static void main(String[] args) {
        try {
            Connection connection = new FakeConnectionPool().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM userinfo");
            ResultSetMetaData metaData = resultSet.getMetaData();
            System.out.println(metaData.getColumnCount());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
