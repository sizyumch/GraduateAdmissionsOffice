package com.epam.gao.command;

import com.epam.gao.command.interfaces.ICommand;
import com.epam.gao.dao.factories.IDAOFactory;
import com.epam.gao.dao.factories.MySQLDAOFactory;
import com.epam.gao.dao.interfaces.IFacultyRequestDAO;
import com.epam.gao.dao.interfaces.IMarkDAO;
import com.epam.gao.dao.interfaces.IUserInfoDAO;
import com.epam.gao.entity.Faculty;
import com.epam.gao.entity.Mark;
import com.epam.gao.entity.UserInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SubmitMarksCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse responce) throws ServletException, IOException {
        String page;
        HttpSession session = request.getSession(false);
        if(session == null){
            page = "/index.jsp";
        }else {
            IDAOFactory daoFactory = new MySQLDAOFactory();
            IMarkDAO markDAO = daoFactory.getMarkDAO();
            IUserInfoDAO userInfoDAO = daoFactory.getUserInfoDAO();
            IFacultyRequestDAO facultyRequestDAO = daoFactory.getFacultyRequestDAO();

            UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
            Faculty faculty = (Faculty) session.getAttribute("selectedFaculty");

            userInfoDAO.create(userInfo);
            userInfo = userInfoDAO.findUserInfo(userInfo.getLogin(), userInfo.getFacultyId());

            List<Mark> marks = new ArrayList<>();
            {
                Mark mark;
                List<Integer> subjectsId = facultyRequestDAO.findSubjectsId(faculty.getId());
                for(int subjectId : subjectsId){
                    mark = new Mark();
                    mark.setSubjectId(subjectId);
                    mark.setMarkValue(Integer.parseInt(request.getParameter(String.valueOf(subjectId))));
                    mark.setUserInfoId(userInfo.getId());
                    marks.add(mark);
                }
            }

            for(Mark mark : marks){
                markDAO.create(mark);
            }
            List<UserInfo> enrolled = userInfoDAO.findRecognizedUsersInfo(faculty.getId(), faculty.getPlan());
            session.removeAttribute("isEnrolled");
            session.setAttribute("isEnrolled", enrolled.contains(userInfo));
            page = "/jsp/userStatus.jsp";
        }
        return page;
    }
}
