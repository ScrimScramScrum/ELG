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
        <style type='text/css'>
            #wrapper2 {
                color: black;
                text-align: center;
                font-size: 30px;
                height: 500px;
                font-weight: 300;
                width: 60%;
            }
            #table1 {
                height: 100%;
                width: 100%;
            }
            #td1 {
                text-align: center;
                height: 40%;
                width: 50%;
                color: #2B8FC4;
            }
            #button1 {
                height: 100%;
                width: 100%;
                font-family: 'Open Sans', sans-serif;
                color: #686868;
                display: inline-block;
                background: #2B8FC4;
                border-radius: 15px;
                text-decoration: none;
                font-size: 1.0em;
                font-weight: 300;
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
        </style>
    </head>
    <body>
        <h3 style="float: right; margin-right: 5%" ><spring:message code="question"/>
            <c:out value="${spillet.current()+1}"></c:out>
            <c:out value="/"></c:out>
            <c:out value="${spillet.getNumberOfQuestions()}"></c:out></h3>
        <center>     
            <div id='wrapper2'>
            <form:form action="nextTask" modelAttribute="spillet" method="POST" style="height: 100%">
                <table id="table1">
                    <tr>
                        <td style="height: 20%" colspan="2">
                            <c:out value="${spillet.getCurrent().getTaskText()}"></c:out>
                            </td>
                        </tr>
                        <tr>
                        <c:forEach items="${spillet.getCurrent().getAlternatives()}" var="teller" begin = "0" end = "1">
                            <td id="td1">
                                <button id="button1" type="submit" value="${teller}" name ="button" >
                                    <c:out value="${teller}" />
                                </button
                            </td>
                        </c:forEach>
                    </tr>
                    <tr>
                        <c:forEach items="${spillet.getCurrent().getAlternatives()}" var="teller" begin = "2" end = "3">
                            <td id="td1">
                                <button id="button1" type="submit" value="${teller}" name ="button">
                                    <c:out value="${teller}" />
                                </button>
                            </td>
                        </c:forEach>
                    </tr>
                </table>
            </form:form>
        </div>
    </center>
</body>
</html>
