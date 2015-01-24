<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style type="text/css">
            #mainbar {
                width: 75%;
                float: left; 

            }
            #rightbar {
                width: 25%;
                float: right; 

            }
        </style>
    </head>
    <body>
        <center>
            <div id="mainbar">
                <br>
                <c:forEach items ="${eachresult}" var="counter" varStatus ="teller">
                    <c:if test="${counter}">
                        <h3>Spørsmål ${teller.count}: Korrekt</h3><br>
                    </c:if>
                    <c:if test="${!counter}">
                        <h3>Spørsmål ${teller.count}: Feil</h3><br>
                    </c:if>
                </c:forEach><br><br>
                <h1><spring:message code="resultMC"/><c:out value="${result}"/></h1><br>
                <h1>Det kreves minst 80 % for å få godkjent øving.</h1>
            </div>
        </center>
        <div id="rightbar"></div>
    </body>
</html>
