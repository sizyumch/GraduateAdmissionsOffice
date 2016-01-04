package com.epam.gao.command;

import com.epam.gao.command.interfaces.ICommand;
import com.epam.gao.dao.factories.IDAOFactory;
import com.epam.gao.dao.factories.MySQLDAOFactory;
import com.epam.gao.entity.Faculty;
import com.epam.gao.entity.UserInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class SelectFacultyCommand implements ICommand {

    private static final int STUDENT_S_USER_TYPE_ID = 1;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse responce) throws ServletException, IOException {
        String page;
        HttpSession session = request.getSession(false);
        if(session == null){
            page = "/index.jsp";
        }else {
            IDAOFactory daoFactory = new MySQLDAOFactory();
            int facultyId = Integer.parseInt(request.getParameter("selectedFacultyId"));
            Faculty faculty = daoFactory.getFacultyDAO().findFaculty(facultyId);
            List<UserInfo> unrecognizedStudents = daoFactory.getUserInfoDAO().findUnrecognizedUsersInfo(facultyId);
            session.removeAttribute("unrecognized");
            session.setAttribute("unrecognized", unrecognizedStudents);
            session.removeAttribute("selectedFaculty");
            session.setAttribute("selectedFaculty", faculty);
            page = "/jsp/admin.jsp";
        }
        return page;
    }
}
