package com.epam.gao.dao;

import com.epam.gao.dao.interfaces.IUserInfoDAO;
import com.epam.gao.dao.interfaces.IUserTypeDAO;
import com.epam.gao.datasource.FakeConnectionPool;
import com.epam.gao.datasource.IConnectionPool;
import com.epam.gao.entity.UserInfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLUserInfoDAO implements IUserInfoDAO {
    private final static String SQL_FIND_ADMIN_INFO = "SELECT * FROM userInfo WHERE login = ? AND " +
            "userTypeId = 2 LIMIT 1";
    private final static String SQL_FIND_USER_INFO = "SELECT * FROM userInfo WHERE login = ? AND " +
            "facultyId = ? LIMIT 1";
    private final static String SQL_FIND_UNRECOGNIZED_USERS_INFO_BY_FACULTY_ID = "SELECT * FROM userInfo " +
            "WHERE recognized = FALSE AND facultyId = ?";
    private final static String SQL_FIND_BEST_MARKED_RECOGNIZED_USERS_INFO_BY_FACULTY_ID =
            "SELECT userInfo.* FROM userInfo INNER JOIN mark ON userInfo.id = mark.userInfoid " +
                    "WHERE userInfo.facultyid = ? AND recognized = true GROUP BY userInfo.login " +
                    "ORDER BY sum(markvalue) DESC LIMIT ?";
    private final static String SQL_UPDATE_RECOGNIZING = "UPDATE userInfo SET recognized = ? WHERE id = ?";
    private final static String SQL_CREATE_USER_INFO = "INSERT INTO userInfo " +
            "(userTypeId, facultyId, recognized, login, password) VALUES (?, ?, ?, ?, ?)";
    private final static String SQL_DELETE_USER_INFO = "DELETE FROM userInfo WHERE id = ?";

    @Override
    public UserInfo findAdminInfo(String login) {
        IConnectionPool connectionPool = new FakeConnectionPool();
        UserInfo userInfo = null;
        try {
            Connection connection = connectionPool.getConnection();
            try(PreparedStatement findAdminStatement = connection.prepareStatement(SQL_FIND_ADMIN_INFO)) {
                findAdminStatement.setString(1, login);
                try(ResultSet resultSet = findAdminStatement.executeQuery()) {
                    if(resultSet.next()){
                        userInfo = parseUserFromResultSet(resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userInfo;
    }

    @Override
    public UserInfo findUserInfo(String login, int facultyId) {
        IConnectionPool connectionPool = new FakeConnectionPool();
        UserInfo userInfo = null;
        try {
            Connection connection = connectionPool.getConnection();
            try(PreparedStatement findUsersStatement = connection.prepareStatement(SQL_FIND_USER_INFO)) {
                findUsersStatement.setString(1, login);
                findUsersStatement.setInt(2, facultyId);
                try(ResultSet resultSet = findUsersStatement.executeQuery()) {
                    if(resultSet.next()){
                        userInfo = parseUserFromResultSet(resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userInfo;
    }

    @Override
    public List<UserInfo> findUnrecognizedUsersInfo(int facultyId) {
        IConnectionPool connectionPool = new FakeConnectionPool();
        List<UserInfo> usersInfo = null;
        try {
            Connection connection = connectionPool.getConnection();
            try(PreparedStatement findUserStatement = connection.prepareStatement(SQL_FIND_UNRECOGNIZED_USERS_INFO_BY_FACULTY_ID)) {
                findUserStatement.setInt(1, facultyId);
                try(ResultSet resultSet = findUserStatement.executeQuery()) {
                    usersInfo = new ArrayList<>();
                    while(resultSet.next()){
                        usersInfo.add(parseUserFromResultSet(resultSet));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersInfo;
    }

    @Override
    public List<UserInfo> findRecognizedUsersInfo(int facultyId, int plan) {
        IConnectionPool connectionPool = new FakeConnectionPool();
        List<UserInfo> usersInfo = null;
        try {
            Connection connection = connectionPool.getConnection();
            try(PreparedStatement findUserStatement = connection.prepareStatement(SQL_FIND_BEST_MARKED_RECOGNIZED_USERS_INFO_BY_FACULTY_ID)) {
                findUserStatement.setInt(1, facultyId);
                findUserStatement.setInt(2, plan);
                try(ResultSet resultSet = findUserStatement.executeQuery()) {
                    usersInfo = new ArrayList<>(plan);
                    while(resultSet.next()){
                        usersInfo.add(parseUserFromResultSet(resultSet));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersInfo;
    }

    private UserInfo parseUserFromResultSet(ResultSet resultSet) throws SQLException {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(resultSet.getInt("id"));
        userInfo.setUserTypeId(resultSet.getInt("userTypeId"));
        userInfo.setFacultyId(resultSet.getInt("facultyId"));
        userInfo.setRecognized(resultSet.getBoolean("recognized"));
        userInfo.setLogin(resultSet.getString("login"));
        userInfo.setPassword(resultSet.getString("password"));
        return userInfo;
    }

    @Override
    public boolean updateRecognizing(boolean recognized, int userInfoId) {
        IConnectionPool connectionPool = new FakeConnectionPool();
        try {
            Connection connection = connectionPool.getConnection();
            try(PreparedStatement createMarkStatement = connection.prepareStatement(SQL_UPDATE_RECOGNIZING)) {
                createMarkStatement.setBoolean(1, recognized);
                createMarkStatement.setInt(2, userInfoId);
                createMarkStatement.execute();
                return true;
            }
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean create(UserInfo userInfo) {
        IConnectionPool connectionPool = new FakeConnectionPool();
        try {
            Connection connection = connectionPool.getConnection();
            try(PreparedStatement createMarkStatement = connection.prepareStatement(SQL_CREATE_USER_INFO)) {
                createMarkStatement.setInt(1, userInfo.getUserTypeId());
                createMarkStatement.setInt(2, userInfo.getFacultyId());
                createMarkStatement.setBoolean(3, userInfo.isRecognized());
                createMarkStatement.setString(4, userInfo.getLogin());
                createMarkStatement.setString(5, userInfo.getPassword());
                createMarkStatement.execute();
                return true;
            }
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean delete(int userInfoId) {
        IConnectionPool connectionPool = new FakeConnectionPool();
        try {
            Connection connection = connectionPool.getConnection();
            try(PreparedStatement deleteMarkStatement = connection.prepareStatement(SQL_DELETE_USER_INFO)) {
                deleteMarkStatement.setInt(1, userInfoId);
                deleteMarkStatement.execute();
                return true;
            }
        } catch (SQLException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        IUserInfoDAO userInfoDAO = new MySQLUserInfoDAO();
        for(UserInfo userInfo : userInfoDAO.findRecognizedUsersInfo(1, 1)){
            System.out.println(userInfo);
            int userTypeId = userInfo.getUserTypeId();
            IUserTypeDAO userTypeDAO = new MySQLUserTypeDAO();
            System.out.println(userTypeDAO.findUserType(userTypeId));
        }

    }
}
