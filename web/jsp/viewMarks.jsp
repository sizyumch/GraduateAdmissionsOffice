<%--
  Created by IntelliJ IDEA.
  User: Sasha
  Date: 25-Dec-15
  Time: 14:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Marks</title>
    </head>
    <body>
        <form name="enterMarksForm" method="POST" action="gao">
            Marks:<br/>
            <input type="hidden" name="command" value ="backToAdminPage"/>
                <c:forEach var="subjectAndMark" items="${sessionScope.subjectsAndMarks}">
                    ${subjectAndMark}
                    <br/>
                </c:forEach>
            <br/>
            <br/>
            <input type ="submit" value="Back">
        </form>
    </body>
</html>
