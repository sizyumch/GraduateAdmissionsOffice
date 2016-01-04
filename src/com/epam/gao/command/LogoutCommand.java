package com.epam.gao.command;

import com.epam.gao.command.interfaces.ICommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse responce) throws ServletException, IOException {
        request.getSession().invalidate();
        return "/index.jsp";
    }
}
