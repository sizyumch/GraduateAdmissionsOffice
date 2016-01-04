package com.epam.gao.command;

import com.epam.gao.command.interfaces.ICommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class BackToAdminPageCommand  implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse responce) throws ServletException, IOException {
        String page;
        HttpSession session = request.getSession(false);
        if(session == null){
            page = "/index.jsp";
        }else{
            page = "/jsp/admin.jsp";
        }
        return page;
    }
}
