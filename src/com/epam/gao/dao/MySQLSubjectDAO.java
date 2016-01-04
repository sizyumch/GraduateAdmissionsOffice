package com.epam.gao.dao;

import com.epam.gao.dao.interfaces.ISubjectDAO;
import com.epam.gao.datasource.FakeConnectionPool;
import com.epam.gao.datasource.IConnectionPool;
import com.epam.gao.entity.Subject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLSubjectDAO implements ISubjectDAO{
    private final static String SQL_SUBJECT_BY_ID = "SELECT * FROM subject WHERE id = ? LIMIT 1";
    private final static String SQL_SUBJECT_BY_FACULTY_ID = "SELECT subject.* FROM subject INNER JOIN facultyRequest " +
            "ON subject.id = facultyRequest.subjectId WHERE facultyId = ?";

    @Override
    public Subject findSubject(int id) {
        IConnectionPool connectionPool = new FakeConnectionPool();
        Subject subject = null;
        try {
            Connection connection = connectionPool.getConnection();
            try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_SUBJECT_BY_ID)) {
                preparedStatement.setInt(1, id);
                try(ResultSet resultSet = preparedStatement.executeQuery()) {
                    if(resultSet.next()){
                        subject = parseSubjectFromResultSet(resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subject;
    }

    @Override
    public List<Subject> findSubjects(int facultyId) {
        IConnectionPool connectionPool = new FakeConnectionPool();
        List<Subject> subjects = null;
        try {
            Connection connection = connectionPool.getConnection();
            try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_SUBJECT_BY_FACULTY_ID)) {
                preparedStatement.setInt(1, facultyId);
                try(ResultSet resultSet = preparedStatement.executeQuery()) {
                    subjects = new ArrayList<>();
                    while(resultSet.next()){
                        subjects.add(parseSubjectFromResultSet(resultSet));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subjects;
    }

    private Subject parseSubjectFromResultSet(ResultSet resultSet) throws SQLException {
        Subject subject = new Subject();
        subject.setId(resultSet.getInt("id"));
        subject.setName(resultSet.getString("name"));
        return subject;
    }
}
