<%-- 
    Document   : creategame
    Created on : 20.jan.2015, 09:35:23
    Author     : eiriksandberg
--%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head> 
        <style>
            #button1 {

                height: 200%;
                width: 100%;
                font-family: 'Open Sans', sans-serif;
                color: #686868;
                display: inline-block;
                background: #2B8FC4;
                border-radius: 15px;
                text-decoration: none;
                font-size: 1.5em;
                font-weight: 100;
                color: #FFF !important;
                -moz-transition: color 0.35s ease-in-out, background-color 0.35s ease-in-out;
                -webkit-transition: color 0.35s ease-in-out, background-color 0.35s ease-in-out;
                -o-transition: color 0.35s ease-in-out, background-color 0.35s ease-in-out;
                -ms-transition: color 0.35s ease-in-out, background-color 0.35s ease-in-out;
                transition: color 0.35s ease-in-out, background-color 0.35s ease-in-out;
                text-align: center;


            }

            #button1:hover {
                background: #065B88;
            }

            table.center{
                margin-left: auto;
                margin-right: auto;
                margin-top: 5%
            }
            body{
                text-align: center;
            }
            
            td{
                width: 50%;
            }

            h1.center{
                margin-top: 5%;
                font-size: 250%;
            }

            h2.center{
                margin-top: 3%;
                font-size: 150%;
            }
            
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1 class ="center"><spring:message code="createinfo"/></h1>
        <h2 class ="center"><spring:message code="choosegametype"/></h2>
        <table class = "center">
            <tr>
                <td><button id="button1" type="submit" name="button" onclick="location.href = '/createmulti'"><spring:message code ="createmulti"/></button></td>
                <td><button id="button1" type="submit" name="button" onclick="location.href = '/createresemblegame'"/><spring:message code ="createresemble"/></button></td>
            </tr>
        </table>
    </body>
</html>
