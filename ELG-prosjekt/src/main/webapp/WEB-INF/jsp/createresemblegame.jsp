<%-- 
    Document   : createresemblegame
    Created on : Jan 16, 2015, 5:50:00 PM
    Author     : Jorgen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
    <head>
        <script src="<c:url value='/resources/resemble.js' />"></script>
        <script src="<c:url value='/resources/html2canvas.js' />"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

         <script type="text/javascript">
        function setRenderedResult(frame, html, css) {
            frame.contents().find("html").html(html);
            var $head = frame.contents().find("head");                
            $head.append("<style>" + css + "</style>") 
        }
    </script>
    </head>
    <body>

        <div id = "createResembleContainer">
            <center>
                <form:form action = "submitresemblegame" method = "POST" modelAttribute = "createResembleGame">
                    Navn på spillet: <form:input path="resembleGame.gamename" id ="gameName" type="text" />
                    Læringsmål: <form:input path="resembleGame.learningGoal" id ="learningGoal" type="text"/>
                    Informasjon: <form:input path="resembleGame.info" id ="info" type="text"/>
                    Vanskelighetsgrad: <form:input path="resembleGame.difficulty" id ="difficulty" type="text"/><br>
                    <input type = "submit" value = "Lagre spill!">
                    <a href ="createresembletask">Lag deloppgave!</a><br>
                    <c:choose>
                        <c:when test="${createResembleGame.resembleTasks.size()==0}">
                            Du har foreløpig ikke lagd noen deloppgaver.
                            <a href ="createresembletask">Lag deloppgave!</a><br>
                        </c:when>
                        <c:otherwise>
                            Dette er deloppgavene du har lagt foreløpig: <br>  
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
                                        var solutionHtml = "${task.solutionHTML}";
                                        var solutionCss = "${task.solutionCSS}";
                                        setRenderedResult($('#solutionFrame<%=teller%>'), solutionHtml, solutionCss);
                                        var startingHTML = "${task.startingHTML}";
                                        var startingCSS = "${task.startingCSS}";
                                        setRenderedResult($('#resultFrame<%=teller%>'), startingHTML, startingCSS);
                                    </script>
                                    <%teller++;%>
                                </div>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </form:form>
            </center>
        </div>
    </body>
</html>