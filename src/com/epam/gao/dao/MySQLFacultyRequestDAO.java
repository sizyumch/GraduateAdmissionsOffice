package com.epam.gao.dao;

import com.epam.gao.dao.interfaces.IFacultyRequestDAO;
import com.epam.gao.datasource.FakeConnectionPool;
import com.epam.gao.datasource.IConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLFacultyRequestDAO implements IFacultyRequestDAO {
    private final static String SQL_SUBJECT_ID_BY_FACULTY_ID = "SELECT subjectId FROM facultyRequest WHERE facultyid = ?";

    @Override
    public List<Integer> findSubjectsId(int facultyId) {
        IConnectionPool connectionPool = new FakeConnectionPool();
        List<Integer> subjectsId = null;
        try {
            Connection connection = connectionPool.getConnection();
            try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_SUBJECT_ID_BY_FACULTY_ID)){
                preparedStatement.setInt(1, facultyId);
                try(ResultSet resultSet = preparedStatement.executeQuery()) {
                    subjectsId = new ArrayList<>();
                    while (resultSet.next()) {
                        subjectsId.add(resultSet.getInt("subjectId"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subjectsId;
    }
}
