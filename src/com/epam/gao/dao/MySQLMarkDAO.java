package com.epam.gao.dao;

import com.epam.gao.dao.interfaces.IMarkDAO;
import com.epam.gao.datasource.FakeConnectionPool;
import com.epam.gao.datasource.IConnectionPool;
import com.epam.gao.entity.Mark;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLMarkDAO implements IMarkDAO{
    private final static String SQL_CREATE_MARK = "INSERT INTO mark (userInfoId, subjectId, markValue) VALUES (?, ?, ?)";
    private final static String SQL_GET_LAST_INSERT_ID = "SELECT LAST_INSERT_ID()";
    private final static String SQL_DELETE_MARK = "DELETE FROM mark WHERE id = ?";
    private final static String SQL_FIND_MARKS_BY_USER_INFO_ID = "SELECT * FROM mark WHERE userInfoId = ?";
    private final static String SQL_FIND_SUM_OF_MARKS_BY_USER_INFO_ID = "SELECT SUM(markValue) FROM mark WHERE userInfoId = ?";

    @Override
    public boolean create(Mark mark) {
        IConnectionPool connectionPool = new FakeConnectionPool();
        try {
            Connection connection = connectionPool.getConnection();
            try(PreparedStatement createMarkStatement = connection.prepareStatement(SQL_CREATE_MARK)) {
                createMarkStatement.setInt(1, mark.getUserInfoId());
                createMarkStatement.setInt(2, mark.getSubjectId());
                createMarkStatement.setInt(3, mark.getMarkValue());
                createMarkStatement.execute();
                return true;
            }
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean delete(int markId) {
        IConnectionPool connectionPool = new FakeConnectionPool();
        try {
            Connection connection = connectionPool.getConnection();
            try(PreparedStatement deleteMarkStatement = connection.prepareStatement(SQL_DELETE_MARK)) {
                deleteMarkStatement.setInt(1, markId);
                deleteMarkStatement.execute();
                return true;
            }
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public List<Mark> findMarks(int userInfoId) {
        IConnectionPool connectionPool = new FakeConnectionPool();
        List<Mark> marks = null;
        try {
            Connection connection = connectionPool.getConnection();
            try(PreparedStatement findMarkStatement = connection.prepareStatement(SQL_FIND_MARKS_BY_USER_INFO_ID)) {
                findMarkStatement.setInt(1, userInfoId);
                try(ResultSet resultSet = findMarkStatement.executeQuery()) {
                    marks = new ArrayList<>();
                    Mark mark;
                    while(resultSet.next()){
                        mark = new Mark();
                        mark.setId(resultSet.getInt("id"));
                        mark.setUserInfoId(resultSet.getInt("userInfoId"));
                        mark.setSubjectId(resultSet.getInt("subjectId"));
                        mark.setMarkValue(resultSet.getInt("markValue"));
                        marks.add(mark);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return marks;
    }

    @Override
    public int findSumOfMarks(int userInfoId) {
        IConnectionPool connectionPool = new FakeConnectionPool();
        int sum = 0;
        try {
            Connection connection = connectionPool.getConnection();
            try(PreparedStatement findMarkStatement = connection.prepareStatement(SQL_FIND_SUM_OF_MARKS_BY_USER_INFO_ID)) {
                findMarkStatement.setInt(1, userInfoId);
                try(ResultSet resultSet = findMarkStatement.executeQuery()) {
                    if(resultSet.next()){
                        sum = resultSet.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sum;
    }

    public static void main(String[] args) {
        IMarkDAO markDAO = new MySQLMarkDAO();
        {
            Mark mark = new Mark();
            mark.setSubjectId(1);
            mark.setUserInfoId(1);
            mark.setMarkValue(200);
            System.out.println(markDAO.create(mark));
            System.out.println(markDAO.delete(3));
        }
        for(Mark mark : markDAO.findMarks(1)){
            System.out.println(mark);
        }
        System.out.println(markDAO.findSumOfMarks(1));
    }
}
