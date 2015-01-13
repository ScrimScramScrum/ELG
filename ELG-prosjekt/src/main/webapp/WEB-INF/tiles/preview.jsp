
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<head>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="<c:url value='/resources/resemble.js' />"></script>
    <script src="<c:url value='/resources/html2canvas.js' />"></script>

    <style type="text/css">
        #leftcolumn, #rightcolumn {
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
        .block {
            float: left;
            margin: 5px;
            padding: 5px;
            background-color: #dddddd;
        }

        #playbutton input{
                position: relative;
                cursor: pointer;
		display: inline-block;
		background: #2B8FC4;
		border-radius: 4px;
		text-decoration: none;
		font-size: 1.2em;
		font-weight: 100;
		color: #FFF !important;
		-moz-transition: color 0.35s ease-in-out, background-color 0.35s ease-in-out;
		-webkit-transition: color 0.35s ease-in-out, background-color 0.35s ease-in-out;
		-o-transition: color 0.35s ease-in-out, background-color 0.35s ease-in-out;
		-ms-transition: color 0.35s ease-in-out, background-color 0.35s ease-in-out;
		transition: color 0.35s ease-in-out, background-color 0.35s ease-in-out;
		text-align: center;
        }

        .renderedFrame {
            /*Add different size for the iframes?*/
            /*<c:out value="width: ${resembleTask.width}px; height: ${resembleTask.height}px" />*/
            width: 100%;
        }
    </style>
    <script type="text/javascript">
        function setRenderedResult(frame, html, css) {
            frame.contents().find("html").html(html);
            var $head = frame.contents().find("head");                
            $head.append("<style>" + css + "</style>") 
        }
    </script>
</head>

<c:choose>
<c:when test="${gametype == 1}">

    <div id="wrapperGameInfo">
        <div id="leftcolumn">
            <h2> ${resembleInfo.gamename}</h2>
            <h4>Difficulty</h4>
            Vanskelighetsgrad: ${resembleInfo.difficulty}
            <br>
            <h4>Info</h4>
            ${resembleInfo.info}
            <br>
            <h4>Learning Goals</h4>
            ${resembleInfo.learningGoal}
            <br><br>
            <div id="playbutton" style="float: right">
            <form action="resemblegame" method="post" id="playbutton">
                <input type="hidden" name="gameid" id="gameid" value="${gamenr}" />
                <input type="submit" value="<spring:message code="play"/>"/>
            </form>
            </div>
        </div>  
        <div id="rightcolumn">
            <!-- preview frames here -->
            <%
                int teller = 0;
            %>
            <c:forEach items="${tasks}" var="task">
                <section id="content">
                    <section class="block">
                        <div id="solutionDiv">
                            <%
                                out.println("<iframe class='renderedFrame' id='solutionFrame" + teller + "' src='about:blank'></iframe>");
                            %>
                        </div>
                    </section>
                </section>
                <br>
                <script>
                    var solutionHtml = "${task.solutionHTML}";
                    var solutionCss = "${task.solutionCSS}";
                    setRenderedResult($('#solutionFrame<%=teller%>'), solutionHtml, solutionCss);
                </script>
                <%
                    teller++;
                %>
            </c:forEach>
            <!-- end preview frames -->
        </div>
        </div>
</c:when>
<c:when test="${gametype == 2}">
    <div id="wrapper">
        <div id="leftcolumn">
            <h2>${gamenr}</h2>
            <h4>Difficulty</h4>
            Vanskelighetsgrad: ${multiChoiceInfo.difficulty}
            <br>
            <h4>Info</h4>
            ${multiChoiceInfo.info}
            <br>
            <h4>Learning Goals</h4>
            ${multiChoiceInfo.learningGoal}
            <br><br>
            <form action="multi" method="post" id="playbutton">
                <input type="hidden" name="gamename" id="gamename" value="${gamenr}" />
                <input type="submit" value="Play"/>
            </form>
        </div>
        <div id="rightcolumn">
            <!-- Add stuff to be on the right side -->
        </div>
    </div>
    </c:when>
    <c:otherwise>
        <spring:message code="gamePage1"/>
    </c:otherwise>
</c:choose>
