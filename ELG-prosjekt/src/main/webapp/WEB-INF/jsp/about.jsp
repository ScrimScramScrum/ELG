<%-- 
    Document   : about
    Created on : Jan 8, 2015, 3:01:20 PM
    Author     : Matias
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../../resources/css3/bootstrap.css" rel="stylesheet" type="text/css"/>
        
        <title>JSP Page</title>
    </head>
    <body>
    <center>
        <h1>Educational Learning Game</h1>
        <br>
            <p>
            Dette er et spill laget i forbindelse med et skoleprosjekt av Team 4. Hensikten med dette spillet er å
            lære bort CSS og HTML til nybegynnere.
            
            
            Test
            <form action="<c:url value="/kOdesLostTags"/>" >
                <input type="submit" value="K.Odes Lost Tags">
            </form>
            
            
        </p></center>
    
    
           
    </body>
</html>
