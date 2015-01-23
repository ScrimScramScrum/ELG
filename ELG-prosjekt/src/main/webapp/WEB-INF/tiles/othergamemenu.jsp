<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

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

            .gamelink{
                width:80%;
            }

            .tableList{
                display: table;
                table-layout: fixed;
                width: 80%;
            }

            #votespan {
                width: 24px;
                float: right;
            }
            
            #star {
                width: 18px;
                float: right;
                vertical-align: middle;
            }
            
            #spannet {
                margin-left: 42px;
            }

            .gameclass{
                text-align: center;
            }
        </style>

        <script type="text/javascript">
            //<![CDATA[
            function get_form(element)
            {
                while (element)
                {
                    element = element.parentNode
                    if (element.tagName.toLowerCase() == "form")
                    {
                        //alert( element ) //debug/test
                        return element
                    }
                }
                return 0; //error: no form found in ancestors
            }
            //]]>
        </script>
    </head>
    <body>
        <div id="sidebar">
            <center>
                <h2>Andre spill</h2>
                <h4>Likhetsspill</h4>
                <c:forEach items="${resembleGames}" var="game">
                    <form action="chooseothergames" method="post">
                        <input type="hidden" name="gametype" value = "resemble">
                        <input type="hidden" name="gameid" id="gameid" value="${game.gameId}" />

                        <div class = "gamelink" onclick =" get_form(this).submit();

                                return false">
                            <span id = "spannet">
                                <c:out value = "${game.getGamename()}"/>
                            </span>
                            <c:choose> 
                                <c:when test="${game.approved == 1}">                                    
                                    <img src="<c:url value="/resources/images/check.png"/>" >
                                    <span id="votespan"><c:out value = "${game.votes}"/></span>
                                    <img src="<c:url value="/resources/images/star.png"/>" id="star">
                                </c:when>
                                <c:otherwise>
                                    <span id="votespan"><c:out value = "${game.votes}"/></span>
                                    <img src="<c:url value="/resources/images/star.png"/>" id="star">
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </form>
                </c:forEach>
                <h4>Flervalgsspill</h4>
                <c:forEach items="${multiChoiceGames}" var="game">
                    <form action="chooseothergames" method="post">
                        <input type = "hidden" name="gametype" value = "multichoice">
                        <input type="hidden" name="gameid" id="gameid" value="${game.name}" />
                        <div class = "gamelink" onclick =" get_form(this).submit();
                                return false">
                            <span id="spannet">
                                <c:out value = "${game.name}"/>
                            </span>
                            <c:choose> 
                                <c:when test="${game.approved == 1}">                                    
                                    <img src="<c:url value="/resources/images/check.png"/>" >
                                    <span id="votespan"><c:out value = "${game.votes}"/></span>
                                    <img src="<c:url value="/resources/images/star.png"/>" id="star">
                                </c:when>
                                <c:otherwise>
                                    <span id="votespan"><c:out value = "${game.votes}"/></span>
                                    <img src="<c:url value="/resources/images/star.png"/>" id="star">
                                </c:otherwise>
                            </c:choose>
                        </div>                        
                    </form>
                </c:forEach>
            </center>
        </div>
    </body>
</html>
