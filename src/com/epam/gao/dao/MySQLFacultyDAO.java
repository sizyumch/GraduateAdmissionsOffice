package com.epam.gao.dao;

import com.epam.gao.dao.interfaces.IFacultyDAO;
import com.epam.gao.dao.interfaces.IFacultyRequestDAO;
import com.epam.gao.dao.interfaces.ISubjectDAO;
import com.epam.gao.datasource.FakeConnectionPool;
import com.epam.gao.datasource.IConnectionPool;
import com.epam.gao.entity.Faculty;
import com.epam.gao.entity.Subject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLFacultyDAO implements IFacultyDAO{
    private final static String SQL_FIND_ALL = "SELECT * FROM faculty";
    private final static String SQL_FACULTY_BY_ID = "SELECT * FROM faculty WHERE id = ? LIMIT 1";

    @Override
    public Faculty findFaculty(int id) {
        IConnectionPool connectionPool = new FakeConnectionPool();
        Faculty faculty = null;
        try {
            Connection connection = connectionPool.getConnection();
            try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_FACULTY_BY_ID)) {
                preparedStatement.setInt(1, id);
                try(ResultSet resultSet = preparedStatement.executeQuery()) {
                    if(resultSet.next())
                        faculty = parseFacultyFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return faculty;
    }

    @Override
    public List<Faculty> findAll(){
        IConnectionPool connectionPool = new FakeConnectionPool();
        List<Faculty> faculties = null;
        try {
            Connection connection = connectionPool.getConnection();
            try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL);
                ResultSet resultSet = preparedStatement.executeQuery()) {
                faculties = new ArrayList<>();
                while (resultSet.next()){
                    faculties.add(parseFacultyFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return faculties;
    }

    private Faculty parseFacultyFromResultSet(ResultSet resultSet) throws SQLException {
        Faculty faculty = new Faculty();
        faculty.setId(resultSet.getInt("id"));
        faculty.setName(resultSet.getString("name"));
        faculty.setPlan(resultSet.getInt("plan"));
        return faculty;
    }

    public static void main(String[] args) {
        IFacultyDAO facultyDAO = new MySQLFacultyDAO();
        for(Faculty faculty : facultyDAO.findAll()){
            System.out.println(faculty);
            IFacultyRequestDAO facultyRequestDAO = new MySQLFacultyRequestDAO();
            for(Integer subjectId : facultyRequestDAO.findSubjectsId(faculty.getId())){
                ISubjectDAO subjectDAO = new MySQLSubjectDAO();
                Subject subject = subjectDAO.findSubject(subjectId);
                System.out.println(subject);
            }
        }
    }
}
