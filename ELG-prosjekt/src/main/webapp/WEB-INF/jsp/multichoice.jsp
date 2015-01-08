<%@page import="springmvc.domain.MultiChoice"%>
<%@page import="springmvc.domain.Exercise"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <!--
        Exercise ex = exercises.get(0);
        String [] alts = ex.getAlternatives();
        for (int i = 0; i < alts.length; i++){
            model.addAttribute("alt"+(i+1), alts[i]);
        }
        model.addAttribute("exerciseText", ex.getTaskText());%>
        
       -->
       
       <c:out value="${spillet.getCurrent().getTaskText()}"></c:out>        
        <table align="center">
            <tr>
                <c:forEach items="${spillet.getCurrent().getAlternatives()}" var="teller" begin = "0" end = "1">
                    <td><button onclick="window.location.href='/ELG-prosjekt/nextTask'" value="${teller}" name ="button" ><c:out value="${teller}" /></button></td>
                </c:forEach>
                <tr>
            </tr>
            <tr>
                <c:forEach items="${spillet.getCurrent().getAlternatives()}" var="teller" begin = "2" end = "3">
                <td><button onclick="window.location.href='/ELG-prosjekt/nextTask'" value="${teller}" name ="button"><c:out value="${teller}" /></button></td>
                </c:forEach>
                <tr>
            </tr>
          </table>
        
        <form:form action="nextTask" modelAttribute="spillet" method="POST">
                <table align="center">
            <tr>
                <c:forEach items="${spillet.getCurrent().getAlternatives()}" var="teller" begin = "0" end = "1">
                    <td><button type="submit" value="${teller}" name ="button" ><c:out value="${teller}" /></button></td>
                </c:forEach>
                <tr>
            </tr>
            <tr>
                <c:forEach items="${spillet.getCurrent().getAlternatives()}" var="teller" begin = "2" end = "3">
                <td><button type="submit" value="${teller}" name ="button"><c:out value="${teller}" /></button></td>
                </c:forEach>
                <tr>
            </tr>
          </table>
        </form>
    </body>
</html>
