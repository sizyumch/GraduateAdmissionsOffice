package com.epam.gao.dao.factories;

import com.epam.gao.dao.interfaces.*;

public interface IDAOFactory {
    IFacultyDAO getFacultyDAO();
    IFacultyRequestDAO getFacultyRequestDAO();
    IMarkDAO getMarkDAO();
    ISubjectDAO getSubjectDAO();
    IUserInfoDAO getUserInfoDAO();
    IUserTypeDAO getUserTypeDAO();
}
