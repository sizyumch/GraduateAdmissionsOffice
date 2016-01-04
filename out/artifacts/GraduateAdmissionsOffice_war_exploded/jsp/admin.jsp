<%--
  Created by IntelliJ IDEA.
  User: Sasha
  Date: 24-Dec-15
  Time: 19:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Admin Page</title>
    </head>
    <body>
        <h3>Faculty:</h3>
        <form name="selectFacultyForm" method="POST" action="gao">
            <input type="hidden" name="command" value ="selectFaculty"/>
            <select name = "selectedFacultyId">
                <c:forEach var="faculty" items="${sessionScope.faculties}">
                    <option value="${faculty.id}">${faculty.name}</option>
                </c:forEach>
            </select>
            <br/>
            <input type ="submit" value="Enter">
        </form>
        <br/>
        <br/>
        <c:forEach var="student" items="${sessionScope.unrecognized}">
            <form name="firstPartStudent" method="POST" action="gao">
                Student: ${student.login}
                <br/>
                <input type="hidden" name="command" value ="showStudentInfo"/>
                <input type ="submit" value="Show information">
                <input type = hidden name = "studentId" value=${student.id}>
            </form>
            <form name="secondPartStudent" method="POST" action="gao">
                <input type="hidden" name="command" value ="recognizeStudent"/>
                <input type ="submit" value="Recognize">
                <input type = hidden name = "studentId" value=${student.id}>
            </form>
            <form name="thirdPartStudent" method="POST" action="gao">
                <input type="hidden" name="command" value ="deleteStudent"/>
                <input type ="submit" value="Delete">
                <input type = hidden name = "studentId" value=${student.id}>
            </form>
            <br/>
            <br/>
        </c:forEach>
        <form name="exitForm" method="POST" action="gao">
            <input type="hidden" name="command" value ="logout"/>
            <input type ="submit" value="Logout">
        </form>
    </body>
</html>
