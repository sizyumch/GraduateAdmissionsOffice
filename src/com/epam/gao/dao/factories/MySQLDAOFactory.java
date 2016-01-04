package com.epam.gao.dao.factories;

import com.epam.gao.dao.*;
import com.epam.gao.dao.interfaces.*;

public class MySQLDAOFactory implements IDAOFactory {
    @Override
    public IFacultyDAO getFacultyDAO() {
        return new MySQLFacultyDAO();
    }

    @Override
    public IFacultyRequestDAO getFacultyRequestDAO() {
        return new MySQLFacultyRequestDAO();
    }

    @Override
    public IMarkDAO getMarkDAO() {
        return new MySQLMarkDAO();
    }

    @Override
    public ISubjectDAO getSubjectDAO() {
        return new MySQLSubjectDAO();
    }

    @Override
    public IUserInfoDAO getUserInfoDAO() {
        return new MySQLUserInfoDAO();
    }

    @Override
    public IUserTypeDAO getUserTypeDAO() {
        return new MySQLUserTypeDAO();
    }
}
