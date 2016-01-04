<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>JSP Page</title>
    </head>
    <body>
        <h3>Login</h3>
        <hr/>
        <form name="loginForm" method="POST" action="gao">
            <input type="hidden" name="command" value ="userLogin"/>
            Faculty:
            <br/>
            <br/>
            <select name = "selectedFacultyId">
                <c:forEach var="faculty" items="${sessionScope.faculties}">
                    <option value="${faculty.id}">${faculty.name}</option>
                </c:forEach>
            </select>
            <br/>
            Login:
            <br/>
            <input type="text" name="login" value ="">
            <br/>
            Password:
            <br/>
            <input type="password" name ="password" value="">
            <br/>
            <br/>
            <input type ="submit" value="Enter">
        </form>
        <hr/>
    </body>
</html>
