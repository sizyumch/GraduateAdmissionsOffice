package com.epam.gao.command;

import com.epam.gao.command.interfaces.ICommand;
import com.epam.gao.dao.factories.IDAOFactory;
import com.epam.gao.dao.factories.MySQLDAOFactory;
import com.epam.gao.dao.interfaces.IUserInfoDAO;
import com.epam.gao.entity.UserInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class RecognizeStudentCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse responce) throws ServletException, IOException {
        String page;
        HttpSession session = request.getSession(false);
        if(session == null){
            page = "/index.jsp";
        }else {
            IDAOFactory daoFactory = new MySQLDAOFactory();
            IUserInfoDAO userInfoDAO = daoFactory.getUserInfoDAO();
            int selectedStudentId = Integer.parseInt(request.getParameter("studentId"));
            userInfoDAO.updateRecognizing(true, selectedStudentId);
            List<UserInfo> unrecognizedStudents = (List<UserInfo>) session.getAttribute("unrecognized");
            int facultyId = unrecognizedStudents.get(0).getFacultyId();
            unrecognizedStudents = userInfoDAO.findUnrecognizedUsersInfo(facultyId);
            session.removeAttribute("unrecognized");
            session.setAttribute("unrecognized", unrecognizedStudents);
            page = "/jsp/admin.jsp";
        }
        return page;
    }
}
