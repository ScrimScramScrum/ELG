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
       
       <c:out value="${spillet.getNextExercise()}"></c:out>
       <c:forEach items="${spillet.getNextExercise().getAlternatives()}" var="current">
        
          <c:out value="${current}" />
        
      </c:forEach>
        
        <center>${exerciseText}</center>
        <table align="center">
            <tr>
                <td><button>${alt1}</button></td>
              <td><button>${alt2}</button></td>
            </tr>
            <tr>
              <td><button>${alt3}</button></td>
              <td><button>${alt4}</button></td>
            </tr>
          </table>
    </body>
</html>
