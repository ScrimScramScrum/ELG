<%-- 
    Document   : finishpage.jsp
    Created on : Jan 8, 2015, 10:55:29 AM
    Author     : Jorgen
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Du greide følgende poengsummer: </h1>
            
        <c:forEach items="${resembleGame.taskNumbers}" var="taskNumber" varStatus="status">
            <li>
                Oppgave nr: <c:out value ="${status.index+1}"/> , Poengsum: <c:out value ="${resembleGame.taskScores[status.index]}"/>
            </li>
        </c:forEach>
        
        <ul>
            <li>
                Total poengsum: <c:out value="${resembleGame.totalScore}"/>
            </li>
        </ul>
    </body>
</html>
