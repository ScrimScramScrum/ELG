<%-- 
    Document   : createresemblegame
    Created on : Jan 16, 2015, 5:50:00 PM
    Author     : Jorgen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html>
<html>
    <head>
        <script src="<c:url value='/resources/resemble.js' />"></script>
        <script src="<c:url value='/resources/html2canvas.js' />"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

         <script type="text/javascript">
        function setRenderedResult(frame, html, css) {
            frame.contents().find("html").html(html);
            var $head = frame.contents().find("head");                
            $head.append("<style>" + css + "</style>") 
        }
        </script>

<style type="text/css">
        #playbutton{
            width: 150px;
            height: 100%;
            margin-right: auto;
            margin-left: auto;
            cursor: pointer;
            display: inline-block;
            background: #2B8FC4;
            border-radius: 4px;
            text-decoration: none;
            font-size: 1.2em;
            font-weight: lighter;
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
    </style>
    </head>
    <body>

        <div id = "createResembleContainer">
            <center>
                <form:form action = "submitresemblegame" method = "POST" modelAttribute = "createResembleGame">
                    Navn på spillet: <form:input path="resembleGame.gamename" id ="gameName" type="text" />
                    Læringsmål: <form:input path="resembleGame.learningGoal" id ="learningGoal" type="text"/>
                    Informasjon: <form:input path="resembleGame.info" id ="info" type="text"/>
                    Vanskelighetsgrad: <form:input path="resembleGame.difficulty" id ="difficulty" type="text"/><br>
                    <c:choose>
                        <c:when test="${createResembleGame.resembleTasks.size()==0}">
                            <br> Du har foreløpig ikke lagd noen deloppgaver.<br><br>
                             <button id="playbutton" type="submit" name = "button" value="Lag deloppgave">Lag deloppgave</button>
                         </c:when>
                        <c:otherwise>
                            Dette er deloppgavene du har lagt foreløpig: <br><br>  
                            <%
                                int teller = 0;
                            %>
                            <c:forEach items="${createResembleGame.resembleTasks}" var="task">
                                <div id="rowCreateResemble" style = "height:160px;">
                                    <div id = "testid1">
                                        Oppgavetekst: <c:out value = "${task.taskText}"/>
                                    </div>
                                    <div id = "createIframes">
                                    <%
                                        out.println("<iframe class='renderedFrame' id='solutionFrame" + teller + "' src='about:blank'></iframe>");
                                        out.println("<iframe class='renderedFrame' id='resultFrame" + teller + "' src='about:blank'></iframe>");
                                    %>
                                    </div>
                                    <script>
                                        $(document).ready(function() {
                                            var solutionHtml = "${task.solutionHTML}";
                                            var solutionCss = "${task.solutionCSS}";
                                            setRenderedResult($('#solutionFrame<%=teller%>'), solutionHtml, solutionCss);
                                            var startingHTML = "${task.startingHTML}";
                                            var startingCSS = "${task.startingCSS}";
                                            setRenderedResult($('#resultFrame<%=teller%>'), startingHTML, startingCSS);
                                        });
                                    </script>
                                    <%teller++;%>
                                </div>
                            </c:forEach>
                            <div id = "rowCreateResemble" style = "height:65px; width:25%;">
                                <div id = "buttonContainerLeft" style = "width: 100px; height: 50px; display:inline;">
                                    <button id="playbutton" type="submit" name = "button" value="Lagre spill">Lagre spill!</button>
                                    <button id="playbutton" type="submit" name = "button" value="Lag deloppgave">Lag deloppgave!</button>
                               </div>
                           </div>
                        </c:otherwise>
                    </c:choose>

                    <div id ="errorMessage" class="errorMessages" >
                        <form:errors path="resembleGame.gamename"/><br>
                        <form:errors path="resembleGame.learningGoal"/><br>
                        <form:errors path="resembleGame.info" /> <br>
                        <form:errors path="resembleGame.difficulty"/>
                        <c:choose>
                            <c:when test="${empty message}">
                            </c:when>
                            <c:otherwise>
                                <spring:message code="${message}" />
                            </c:otherwise>
                        </c:choose>
                    </div>
                </form:form>
            </center>
    </body>
</html>