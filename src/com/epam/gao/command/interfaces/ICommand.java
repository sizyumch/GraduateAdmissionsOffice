package com.epam.gao.command.interfaces;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public interface ICommand {
    String execute(HttpServletRequest request, HttpServletResponse responce) throws ServletException, IOException;
}