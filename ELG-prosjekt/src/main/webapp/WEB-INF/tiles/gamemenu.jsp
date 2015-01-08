<%-- 
    Document   : gamemenu
    Created on : Jan 7, 2015, 4:22:43 PM
    Author     : borgarlie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <h1>Hello World!</h1>
        <br><br>
        Resemblegames:
        <c:forEach items="${resembleGames}" var="game">
            <li>
                <form action="choosegame" method="post">
                    <input type="hidden" name="gameid" id="gameid" value="${game.gameId}" />
                    <input type="submit" value="${game.gameId}" />
                    game nr: <c:out value='${game.gameId}' />
                </form>
            </li>
        </c:forEach>
        <br><br>
         <c:forEach items="${multiChoiceGames}" var="game">
            <li>
                <form action="choosegame" method="post">
                    <input type="hidden" name="gameid" id="gameid" value="${game.name}" />
                    <input type="submit" value="${game.name}" />
                    game name: <c:out value='${game.name}' />
                </form>
            </li>
        </c:forEach>
    </body>
</html>
