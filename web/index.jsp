<%--
  Created by IntelliJ IDEA.
  User: Sasha
  Date: 14-Dec-15
  Time: 18:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>JSP Page</title>
    </head>
    <body>
        <h3>Welcome to the graduate admissions office!</h3>
        Please sign in:
        <br/><br/>
        <form name = preUserLoginForm action="gao">
            <input type="hidden" name="command" value ="prepareUserLogin"/>
            For users:
            <br/>
            <input type ="submit" value="Login">
        </form>
        <br/>
        <form name = preAdminLoginForm action="gao">
            <input type="hidden" name="command" value ="prepareAdminLogin"/>
            For admins:
            <br/>
            <input type ="submit" value="Login">
        </form>
    </body>
</html>
