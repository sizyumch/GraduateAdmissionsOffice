<%--
  Created by IntelliJ IDEA.
  User: Sasha
  Date: 25-Dec-15
  Time: 12:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="utf-8" />
        <title>JSP Page</title>
    </head>
    <body>
        <h3>Login</h3>
        <hr/>
        <form name="loginForm" method="POST" action="gao">
            <input type="hidden" name="command" value ="adminLogin"/>
            <br/>
            Login:
            <br/>
            <input type="text" name="login" value ="">
            <br/>
            Password:
            <br/>
            <input type="password" name ="password" value="">
            <br/>
            <input type ="submit" value="Enter">
        </form>
        <hr/>
    </body>
</html>