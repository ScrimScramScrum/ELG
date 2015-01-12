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
                font-size: 20px;
                height: 500px;
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
            }
            #button1 {
                height: 100%;
                width: 100%;
            }
        </style>
    </head>
    <body>
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
