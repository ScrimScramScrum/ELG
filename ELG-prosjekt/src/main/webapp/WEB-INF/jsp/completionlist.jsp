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
        <title>Godkjenningsliste</title>
        <style type='text/css'>
            ul
            {
                list-style-type: none;
            }
            
            #wrappercompletion{
                width: 100%;
            }
            
            #completionleft{
                width: 75%;
                float: left;
            }
            #completionright{
                width: 25%;
                float: right;
            }
        </style>
    </head>
    <body>
        <div id ="wrappercompletion">
            <div id ="completionleft">
    <center><h1>Godkjenningsliste</h1>
        <br>
        <h2><c:out value="${nopass}"/></h2>
        <ul>
            <c:forEach items="${list}" var="names">
                <li><c:out value="${names.getFname()} ${names.getLname()}"/></li>
                </c:forEach>
        </ul>
    </center></div><div id="completionright"></div></div></body>
</html>
