
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<h2>This is info about the game :))))</h2>
<c:choose>
<c:when test="${gametype == 1}">
    :::Info::: 
    <br>${info}
    <br><br>
    <form action="resemblegame" method="post">
        <input type="hidden" name="gameid" id="gameid" value="${gamenr}" />
        <input type="submit" value="Play" />
        game nr: <c:out value='${gamenr}' />
    </form>
</c:when>
<c:when test="${gametype == 2}">
    :::Info::: 
    <br>${info}
    <br><br>
    <form action="multi" method="post">
        <input type="hidden" name="gamename" id="gamename" value="${gamenr}" />
        <input type="submit" value="Play" />
        game name: <c:out value='${gamenr}' />
    </form>
</c:when>
<c:otherwise>
    On the left side you can select a game you want to play.
    <br>
    On this side you will get more info about the selected game
    <br>
    When you have decided, you may click play :)
</c:otherwise>
</c:choose>
