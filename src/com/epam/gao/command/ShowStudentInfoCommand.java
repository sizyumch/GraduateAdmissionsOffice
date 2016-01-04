package com.epam.gao.command;

import com.epam.gao.command.interfaces.ICommand;
import com.epam.gao.dao.factories.IDAOFactory;
import com.epam.gao.dao.factories.MySQLDAOFactory;
import com.epam.gao.dao.interfaces.IMarkDAO;
import com.epam.gao.dao.interfaces.ISubjectDAO;
import com.epam.gao.entity.Mark;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShowStudentInfoCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse responce) throws ServletException, IOException {
        String page;
        HttpSession session = request.getSession(false);
        if(session == null){
            page = "/index.jsp";
        }else {
            IDAOFactory daoFactory = new MySQLDAOFactory();
            ISubjectDAO subjectDAO = daoFactory.getSubjectDAO();
            IMarkDAO markDAO = daoFactory.getMarkDAO();
            int studentId = Integer.parseInt(request.getParameter("studentId"));
            List<Mark> marks = markDAO.findMarks(studentId);
            List<String> result = new ArrayList<>(marks.size());
            for (Mark mark : marks) {
                result.add(subjectDAO.findSubject(mark.getSubjectId()).getName() + "\t:\t" + mark.getMarkValue());
            }
            session.removeAttribute("subjectsAndMarks");
            session.setAttribute("subjectsAndMarks", result);
            page = "/jsp/viewMarks.jsp";
        }
        return page;
    }
}
