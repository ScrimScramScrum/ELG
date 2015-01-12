<%-- 
    Document   : highscorelist
    Created on : 12.jan.2015, 10:58:20
    Author     : eiriksandberg
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
                        <table align="center">
                <c:forEach items="${highscore}" var="teller">
                    <tr><td><c:out value="${teller}" /></td></tr>
                </c:forEach>
                        </table>
    </body>
</html>
