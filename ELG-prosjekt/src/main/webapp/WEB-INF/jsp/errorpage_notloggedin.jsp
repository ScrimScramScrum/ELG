<%-- 
    Document   : errorpage_notloggedin
    Created on : 14.jan.2015, 17:02:39
    Author     : eiriksandberg
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>
            <script>
                <spring:message code = "notLoggedIn" />
            </script>
        </h1>
    </body>
</html>
