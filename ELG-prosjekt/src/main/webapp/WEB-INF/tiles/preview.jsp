<%-- 
    Document   : preview.jsp
    Created on : Jan 8, 2015, 10:10:58 AM
    Author     : borgarlie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <c:choose>
        <c:when test="${gametype == 1}">
            <form action="resemblegame" method="post">
                <input type="hidden" name="gameid" id="gameid" value="${gamenr}" />
                <input type="submit" value="${gamenr}" />
                game nr: <c:out value='${gamenr}' />
            </form>
        </c:when>
        <c:when test="${gametype == 2}">
            <form action="multi" method="post">
                <input type="hidden" name="gamename" id="gamename" value="${gamenr}" />
                <input type="submit" value="${gamenr}" />
                game name: <c:out value='${gamenr}' />
            </form>
        </c:when>
        <c:otherwise>
            shieeet.
        </c:otherwise>
    </c:choose>
    </body>
</html>
