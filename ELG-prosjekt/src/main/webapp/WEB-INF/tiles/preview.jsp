
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<head>
    <style type="text/css">
        #leftcolumn, #rightcolumn {
            /*border: 0px solid white;*/
            float: left;
        }
        #leftcolumn {
             width: 60%;
        }
        #rightcolumn {
             width: 40%;
        }
    </style>
</head>

<h2>This is info about the game :))))</h2>
<c:choose>
<c:when test="${gametype == 1}">

    <div id="wrapper">
        <div id="leftcolumn">
            Left..
            :::Difficulty:::
            <br>Vanskelighetsgrad: ${resembleInfo.difficulty}
            <br><br>
            :::Info::: 
            <br>${resembleInfo.info}
            <br><br>
            :::Learning Goals:::
            <br>${resembleInfo.learningGoal}
            <br><br>
            <form action="resemblegame" method="post">
                <input type="hidden" name="gameid" id="gameid" value="${gamenr}" />
                <input type="submit" value="Play" />
                game nr: <c:out value='${gamenr}' />
            </form>
        </div>  
        <div id="rightcolumn">
            Right
            <br>
            shit here..
            <br>
            <br>
            hai..
            <c:forEach items="${tasks}" var="task">
                <br>
                ${task.solutionHTML}
                <br>
            </c:forEach>
        </div>
        </div>
</c:when>
<c:when test="${gametype == 2}">
    :::Difficulty:::
    <br>Vanskelighetsgrad: ${multiChoiceInfo.difficulty}
    <br><br>
    :::Info::: 
    <br>${multiChoiceInfo.info}
    <br><br>
    :::Learning Goals:::
    <br>${multiChoiceInfo.learningGoal}
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
