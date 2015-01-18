<%-- 
    Document   : createmulti
    Created on : 18.jan.2015, 18:02:43
    Author     : eiriksandberg
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Lag ditt eget multiple choice spill!</h1>
        <form:form action="number"> 
            Velg antall spørsmål <select id="number" name = "number">
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
                <option value="6">6</option>
                <option value="7">7</option>
                <option value="8">8</option>
                <option value="9">9</option>
                <option value="10">10</option>
            </select>   
            <input type="submit" value = "Velg">
        </form:form>

            <c:set var="check" value="${numberOfTasks}"/>
            <c:if test="${check > 0}">     
                <form:form>
                    <c:forEach begin = "1" end = "${numberOfTasks}" var = "teller">
                        Spørsmål nummer : <c:out value="${teller}"/><br>
                        Skriv inn oppgaven her: <input type ="text"><br>
                        Skriv fasiten her: <input type ="text"><br>
                        <br>
                        Alternativ 1: <input type ="text"><br>
                        Alternativ 2: <input type ="text"><br>
                        Alternativ 3: <input type ="text"><br>
                        Alternativ 4: <input type ="text"><br>
                        <br><br>
                    </c:forEach>
                    <input type ="submit" value="Lagre">
                </form:form>
                    </c:if>
            </body>
        </html>
