<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel = "stylesheet" type = "text/css" href = "<c:url value='/resources/css2/styleGamemenu.css'/>">
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style type="text/css">
            #gamebutton {
                width: 150px;
                height: 60px;
            }
        </style>
    </head>
    <body>
        <h1>Her skal vi liste hverandres seksuelle high scores</h1>
                <div id="sidebar">
        <center>
        <h2>Select a game</h2>
        <h4>Resemble Games</h4>
        <c:forEach items="${resembleGames}" var="game">
            <!-- <li> -->
                <form action="choosegameHighscore" method="post">
                    <input type="hidden" name="gameid" id="gameid" value="${game.gameId}" />
                    <input type="submit" id="gamebutton" value="Resemble ${game.gameId}" />
                </form>
            <!-- </li> -->
        </c:forEach>
        <h4>MultiChoice Games</h4>
        <c:forEach items="${multiChoiceGames}" var="game">
            <!-- <li> -->
                <form action="choosegameHighscore" method="post">
                    <input type="hidden" name="gameid" id="gameid" value="${game.name}" />
                    <input type="submit" id="gamebutton" value="Multi ${game.name}" />
                </form>
            <!-- </li> -->
        </c:forEach>
        </center>
        </div>
    </body>
</html>
