<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="springmvc.domain.User"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<head>
    <script src="<c:url value='/resources/js/jquery.min.js' />"></script>
    <script src="<c:url value='/resources/resemble.js' />"></script>
    <script src="<c:url value='/resources/html2canvas.js' />"></script>

    <style type="text/css">
        
        #leftcolumn {
             width: 75%;
             float: left;
        }
        #rightcolumn {
            width: 25%;
            float: right;
            /*float: left;*/
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
        
        #playbutton{
            width: 100px;
            height: 50px;
            position: relative;
            cursor: pointer;
    		display: inline-block;
    		background: #2B8FC4;
    		border-radius: 4px;
    		text-decoration: none;
    		font-size: 1.2em;
    		font-weight: 120;
    		color: #FFF !important;
    		-moz-transition: color 0.35s ease-in-out, background-color 0.35s ease-in-out;
    		-webkit-transition: color 0.35s ease-in-out, background-color 0.35s ease-in-out;
    		-o-transition: color 0.35s ease-in-out, background-color 0.35s ease-in-out;
    		-ms-transition: color 0.35s ease-in-out, background-color 0.35s ease-in-out;
    		transition: color 0.35s ease-in-out, background-color 0.35s ease-in-out;
    		text-align: center;
        }
        #playbutton:hover {
                background: #065B88;
            }
        #welcome{
            width: 75%;
            float: left;
            text-align: center;
        }
        #welcome_space {
            float: right;
            width: 25%;
        }
        
        .renderedFrame {
            /*Add different size for the iframes?*/
            /*<c:out value="width: ${resembleTask.width}px; height: ${resembleTask.height}px" />*/
            width: 80%;
        }

        #link_ul {
            list-style-type: none;
        }

        #link_li {

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
    <div id="wrapperGameInfoaaa">
        <div id="leftcolumn">
            <h2> <c:out value="${resembleInfo.gamename}" /></h2>
            <br>
            <h4>Vanskelighetsgrad</h4>
            <c:out value="${resembleInfo.difficulty}" /> / 3
            <br>
            <h4>Informasjon</h4>
            <c:out value="${resembleInfo.info}" />
            <br>
            <h4>Læringsmål</h4>
            <c:out value="${resembleInfo.learningGoal}" />
            <br>
            <h4>Laget av</h4>
            <c:out value="${resembleInfo.creatorId}" />
            <br><br>
            <form action="resemblegame" method="post" style="float: right">
                <input type="hidden" name="gameid" id="gameid" value="${gamenr}" />
                <input type = "hidden" name="othergame" value = ""/>
                <button id="playbutton" type="submit" value="play">
                        <spring:message code="play"/>
            </button>
            </form>
            <%
                User user = (User)session.getAttribute("user");
                if(user.isAdmin()){%>
                    <form action="movegame" id = "gameform" name = "gameform" method="post" style="float: right; margin-right: 8px">
                            <input type="hidden" name="gameid" id="gameid" value="${gamenr}" />
                            <input type = "hidden" name="gametype" value = "resemble">
                            <button id="playbutton" type="submit" name = "button" value="removeexercise">Fjern</button>
                    </form>                
                <%}
             %> 
        </div>  
        <div id="rightcolumn">
            <center>
                <!-- preview frames here -->
                <%
                    int teller = 0;
                %>
                <c:forEach items="${tasks}" var="task">
                    <section id="content">
                        <section class="block">
                            <div id="solutionDivPreview">
                                <%
                                    out.println("<iframe class='renderedFrame' id='solutionFrame" + teller + "' src='about:blank'></iframe>");
                                %>
                            </div>
                        </section>
                    </section>
                    <br>
                    <script>
                        $(document).ready(function() {
                            var solutionHtml = "${task.solutionHTML}";
                            var solutionCss = "${task.solutionCSS}";
                            setRenderedResult($('#solutionFrame<%=teller%>'), solutionHtml, solutionCss);
                        });
                    </script>
                    <%
                        teller++;
                    %>
                </c:forEach>
                <!-- end preview frames -->
            </center>
        </div>
    </div>
</c:when>
<c:when test="${gametype == 2}">
    <div id="wrapperGameInfo">
        <div id="leftcolumn">
            <h2><c:out value="${gamenr}" /></h2>
            <br>
            <h4>Vanskelighetsgrad</h4>
            <c:out value="${multiChoiceInfo.difficulty}" /> / 3
            <br>
            <h4>Informasjon</h4>
            <c:out value="${multiChoiceInfo.info}" />
            <br>
            <h4>Læringsmål</h4>
            <c:out value="${multiChoiceInfo.learningGoal}" />
            <br>
            <h4>Laget av</h4>
            <c:out value="${multiChoiceInfo.creatorId}" />
            <br><br>
            <form action="multi" method="post" id="playbutton" style="float: right">
                <input type="hidden" name="gamename" id="gamename" value="${gamenr}" />
                <input type = "hidden" name="othergame" value = ""/>
                <button id="playbutton" type="submit" value="play">
                        <spring:message code="play"/>
            </button>
            </form>
            <%
                User user = (User)session.getAttribute("user");
                if(user.isAdmin()){%>
                    <form action="movegame" id = "gameform" name = "gameform" method="post" style="float: right; margin-right: 8px">
                        <input type="hidden" name="gameid" id="gameid" value="${gamenr}" />
                        <input type = "hidden" name="gametype" value = "multichoice">
                        <button id="playbutton" type="submit" name = "button" value="removeexercise">Fjern</button>
                    </form>                
                <%}
             %> 
        </div>
        <div id="rightcolumn">
            <!-- Add stuff to be on the right side -->
        </div>
    </div>
    </c:when>
    <c:otherwise>
        <div id="welcome_wrapper">
            <div id="welcome">
                <h1><spring:message code="gamePage1"/></h1>
                <!-- Links -->
                <br>
                <ul id="link_ul">
                    <li id="link_li">Her er pensum for Øvingen i PDF format</li>
                    <li id="link_li"><a href="<c:out value='/files/intro-html.pdf' />" target="_blank">Intro HTML PDF</a></li>
                    <li id="link_li"><a href="<c:out value='/files/leksjon3_ppi_css.pdf' />" target="_blank">Intro CSS PDF</a></li>
                </ul>
                <br>
                <ul id="link_ul">
                    <li id="link_li">Her er noen gode linker for å lære seg HTML og CSS</li>
                    <li id="link_li"><a href="https://docs.webplatform.org/wiki/Main_Page" target="_blank">WebPlatform</a></li>
                    <li id="link_li"><a href="https://developer.mozilla.org/en-US/docs/Web" target="_blank">MDN</a></li>
                    <li id="link_li"><a href="http://www.w3schools.com" target="_blank">W3Schools</a></li>
                    <li id="link_li"><a href="https://github.com/dypsilon/frontend-dev-bookmarks" target="_blank">Frontend-dev-bookmarks</a></li>
                    <li id="link_li"><a href="http://learnlayout.com" target="_blank">LearnLayout</a></li>
                </ul>
            </div>
            <div id="welcome_space">
                <center>
                    <a href="<c:url value="/kOdesLostTags"/>"><img src="<c:url value='/resources/kOdesLostTags/kOdesLostTagsJS/ProfessorK-OdeHode.png'/>" width="50%" height="auto"></a>
                    <br>
                    Psst.. Hjelp!
                </center>
            </div>
        </div>
    </c:otherwise>
</c:choose>
