<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <p><spring:message code="melding" /></p>
        
        <form action="<c:url value="/administrateAccount"/>" >
            <input type="submit" value="administrere">
        </form>

        <form action="<c:url value="/kOdesLostTags"/>" >
            <input type="submit" value="K.Odes Lost Tags">
        </form>
        <h2><c:out value="${registeredOK}"/></h2>
        <h2> <c:out value="${sessionScope.user}"/></h2>
    </body>
</html>
