
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
            margin-left: 5%;
            width: 35%;
        }
        #leftcolumn input {
            width: 100px;
        }
        #leftcolumn h4 {
            padding: 0px;
            margin-right: 0px;
            margin-left: 0px;
            margin-bottom: 5px;
            margin-top: 5px;
        }
        #leftcolumn input{
             width: 99%;
        }
    </style>
</head>

<c:choose>
<c:when test="${gametype == 1}">

    <div id="wrapper">
        <div id="leftcolumn">
            <h2>Resemble ${gamenr}</h2>
            <h4>Difficulty</h4>
            Vanskelighetsgrad: ${resembleInfo.difficulty}
            <br>
            <h4>Info</h4>
            ${resembleInfo.info}
            <br>
            <h4>Learning Goals</h4>
            ${resembleInfo.learningGoal}
            <br><br>
            <form action="resemblegame" method="post">
                <input type="hidden" name="gameid" id="gameid" value="${gamenr}" />
                <input type="submit" value="Play" />
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
