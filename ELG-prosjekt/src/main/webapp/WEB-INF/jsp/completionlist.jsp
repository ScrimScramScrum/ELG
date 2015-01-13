<%-- 
    Document   : completionlist
    Created on : 13.jan.2015, 15:38:47
    Author     : eiriksandberg
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       <c:forEach items="${list}" var="names">
           <c:out value="${names}"/>
            </c:forEach>
    </body>
</html>
