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
        <h1>Hello World!</h1>@
        <p>
            <script>
                <spring:message code="melding" />
            </script>
        </p>

        <%--
             
        <form action="<c:url value="/login"/>">
        <input type="submit" value="Log inn">
        </form>
        
        <form action="<c:url value="/newPerson"/>">
        <input type="submit" value="Ny person">
        </form> --%>

        <form action="<c:url value="/administrateAccount"/>" >
            <input type="submit" value="administrere">
        </form>

        <form action="<c:url value="/kOdesLostTags"/>" >
            <input type="submit" value="K.Odes Lost Tags">
        </form>


        <h2>${registeredOK}</h2>


        <h2> <c:out value="${sessionScope.user}"/>



    </body>
</html>
