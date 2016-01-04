package com.epam.gao.controller;

import com.epam.gao.command.*;
import com.epam.gao.command.interfaces.ICommand;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public class ControllerHelper {
    private static ControllerHelper instance = null;

    private HashMap<String, ICommand> commands;

    public ControllerHelper() {
        commands = new HashMap<>(7);
        commands.put("backToAdminPage", new BackToAdminPageCommand());
        commands.put("showStudentInfo", new ShowStudentInfoCommand());
        commands.put("adminLogin", new AdminLoginCommand());
        commands.put("deleteStudent", new DeleteStudentCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("prepareAdminLogin", new PrepareAdminLoginCommand());
        commands.put("prepareUserLogin", new PrepareUserLoginCommand());
        commands.put("recognizeStudent", new RecognizeStudentCommand());
        commands.put("selectFaculty", new SelectFacultyCommand());
        commands.put("submitMarks", new SubmitMarksCommand());
        commands.put("userLogin", new UserLoginCommand());
    }

    public ICommand getCommand(HttpServletRequest request) {
        String str = request.getParameter("command");
        ICommand command = commands.get(str);
        if (command == null) {
            command = new CommandMissing();
        }
        return command;
    }

    public static ControllerHelper getInstance() {
        if (instance == null) {
            instance = new ControllerHelper();
        }
        return instance;
    }
}
