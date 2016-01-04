<%--
  Created by IntelliJ IDEA.
  User: Sasha
  Date: 25-Dec-15
  Time: 01:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8" />
    <title>User status page</title>
</head>
    <body>
        <form name="userStatusForm" method="POST" action="gao">

            <input type="hidden" name="command" value ="logout"/>
            Status:
            <br/>
            <br/>
            <c:if test = "${sessionScope.userInfo.recognized == false}">
                Waiting for recognizing
            </c:if>
            <c:if test = "${sessionScope.userInfo.recognized != false}">
                <c:if test = "${sessionScope.isEnrolled == true}">
                    You are enrolled!
                </c:if>
                <c:if test = "${sessionScope.isEnrolled != true}">
                    You are not enrolled :(
                </c:if>
            </c:if>
            <br/>
            <br/>
            <input type ="submit" value="Logout">
        </form>
    </body>
</html>
