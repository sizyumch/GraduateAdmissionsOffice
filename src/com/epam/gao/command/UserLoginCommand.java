package com.epam.gao.command;

import com.epam.gao.command.interfaces.ICommand;
import com.epam.gao.dao.factories.IDAOFactory;
import com.epam.gao.dao.factories.MySQLDAOFactory;
import com.epam.gao.dao.interfaces.IFacultyDAO;
import com.epam.gao.dao.interfaces.ISubjectDAO;
import com.epam.gao.dao.interfaces.IUserInfoDAO;
import com.epam.gao.entity.Faculty;
import com.epam.gao.entity.Subject;
import com.epam.gao.entity.UserInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class UserLoginCommand implements ICommand {
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
            Faculty faculty = facultyDAO.findFaculty(Integer.parseInt(request.getParameter("selectedFacultyId")));
            session.removeAttribute("selectedFaculty");
            session.setAttribute("selectedFaculty", faculty);
            String login = request.getParameter(LOGIN);
            String password = request.getParameter(PASSWORD);
            UserInfo userInfo = userInfoDAO.findUserInfo(login, faculty.getId());
            if (userInfo != null) {
                if (userInfo.getPassword().equals(password)) {
                    List<UserInfo> enrolled = userInfoDAO.findRecognizedUsersInfo(faculty.getId(), faculty.getPlan());
                    session.removeAttribute("isEnrolled");
                    session.setAttribute("isEnrolled", enrolled.contains(userInfo));
                    session.removeAttribute("userInfo");
                    session.setAttribute("userInfo", userInfo);
                    page = "/jsp/userStatus.jsp";
                } else {
                    page = "/jsp/userLogin.jsp";
                }
            } else {
                userInfo = new UserInfo();
                userInfo.setLogin(login);
                userInfo.setPassword(password);
                userInfo.setUserTypeId(1);
                userInfo.setFacultyId(faculty.getId());
                userInfo.setRecognized(false);
                ISubjectDAO subjectDAO = daoFactory.getSubjectDAO();
                List<Subject> subjects = subjectDAO.findSubjects(faculty.getId());
                session.removeAttribute("subjects");
                session.setAttribute("subjects", subjects);
                session.removeAttribute("userInfo");
                session.setAttribute("userInfo", userInfo);

                page = "/jsp/userMarks.jsp";
            }
        }
        return page;
    }
}
