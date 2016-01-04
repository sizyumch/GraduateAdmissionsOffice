package com.epam.gao.command;

import com.epam.gao.command.interfaces.ICommand;
import com.epam.gao.dao.factories.IDAOFactory;
import com.epam.gao.dao.factories.MySQLDAOFactory;
import com.epam.gao.dao.interfaces.IFacultyDAO;
import com.epam.gao.entity.Faculty;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class PrepareUserLoginCommand implements ICommand{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse responce) throws ServletException, IOException {
        String page;
        HttpSession session = request.getSession(false);
        if(session == null){
            page = "/index.jsp";
        }else {
            page = "/jsp/userLogin.jsp";
            IDAOFactory daoFactory = new MySQLDAOFactory();
            IFacultyDAO facultyDAO = daoFactory.getFacultyDAO();
            List<Faculty> faculties = facultyDAO.findAll();
            session.removeAttribute("faculties");
            session.setAttribute("faculties", faculties);
        }
        return page;
    }
}
