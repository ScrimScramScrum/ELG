<%-- 
    Document   : createresemblegame
    Created on : Jan 16, 2015, 5:50:00 PM
    Author     : Jorgen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <a href ="createresembletask">Lag et spill! :D </a><br>
        Dette er spillene du har lagt foreløpig: <br>
        <c:forEach items="${createResembleGame.resembleTasks}" var="task">
            <c:out value = "Solution HTML: ${task.solutionHTML}"/>
            <c:out value = "Solution CSS: ${task.solutionCSS}"/>
            <c:out value = "Starting HTML ${task.startingHTML}"/> 
            <c:out value = "Starting CSS: ${task.startingCSS}"/>
            <br>
        </c:forEach>

        <c:choose>
            <c:when test="${createResembleGame.resembleTasks.size()==0}">
                Du har foreløpig ikke lagd noen deloppgaver.
            </c:when>
            <c:otherwise>
            <form:form action = "submitresemblegame" method = "POST" modelAttribute = "createResembleGame">
                <form:input path="resembleGame.gamename" id ="gameName" type="text"/>
                <input type = "submit" value = "Lagre spill!">
            </form:form>
            </c:otherwise>
        </c:choose>
    </body>
</html>