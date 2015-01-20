<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body><center><br>
        <c:forEach items ="${eachresult}" var="counter" varStatus ="teller">
            <c:if test="${counter}">
                <h3>Spørsmål ${teller.count}: Korrekt</h3><br>
            </c:if>
            <c:if test="${!counter}">
                <h3>Spørsmål ${teller.count}: Feil</h3><br>
            </c:if>
        </c:forEach><br><br>
        <h1><spring:message code="resultMC"/>${result} %</h1><br>
        <h1>Det kreves minst 80 % for å få godkjent øving.</h1>
    </center></body>
</html>
