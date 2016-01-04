package com.epam.gao.command;

import com.epam.gao.command.interfaces.ICommand;
import com.epam.gao.dao.factories.IDAOFactory;
import com.epam.gao.dao.factories.MySQLDAOFactory;
import com.epam.gao.dao.interfaces.IFacultyDAO;
import com.epam.gao.dao.interfaces.IUserInfoDAO;
import com.epam.gao.entity.Faculty;
import com.epam.gao.entity.UserInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class AdminLoginCommand implements ICommand{

    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse responce) throws ServletException, IOException {
        String page;
        HttpSession session = request.getSession(false);
        if(session == null){
            page = "/index.jsp";
        }else {
            IDAOFactory daoFactory = new MySQLDAOFactory();
            IUserInfoDAO userInfoDAO = daoFactory.getUserInfoDAO();
            IFacultyDAO facultyDAO = daoFactory.getFacultyDAO();
            String login = request.getParameter(LOGIN);
            String password = request.getParameter(PASSWORD);
            UserInfo userInfo = userInfoDAO.findAdminInfo(login);
            if (userInfo != null && userInfo.getPassword().equals(password)) {
                List<Faculty> faculties = facultyDAO.findAll();
                session.removeAttribute("faculties");
                session.setAttribute("faculties", faculties);
                if(!faculties.isEmpty()){
                    int facultyId = faculties.get(0).getId();
                    List<UserInfo> unrecognizedStudents = userInfoDAO.findUnrecognizedUsersInfo(facultyId);
                    session.removeAttribute("unrecognized");
                    session.setAttribute("unrecognized", unrecognizedStudents);
                    session.removeAttribute("selectedFaculty");
                    session.setAttribute("selectedFaculty", faculties.get(0));
                }
                page = "/jsp/admin.jsp";
            } else {
                page = "/jsp/adminLogin.jsp";
            }
        }
        return page;
    }
}
