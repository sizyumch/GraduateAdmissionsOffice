<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8" />
    <title>Enter marks page</title>
</head>
    <body>
        <form name="enterMarksForm" method="POST" action="gao">
            <input type="hidden" name="command" value ="submitMarks"/>
                <c:forEach var="subject" items="${sessionScope.subjects}">
                    ${subject.name}
                    <br/>
                        <input type="text" name="${subject.id}" value ="">
                    <br/>
                </c:forEach>
            <br/>
            <input type ="submit" value="Enter">
        </form>
    </body>
</html>
