<%-- 
    Document   : completionlist_gamelist
    Created on : 13.jan.2015, 15:55:35
    Author     : eiriksandberg
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
                <script type="text/javascript">
            //<![CDATA[
            function get_form( element )
            {
                while( element )
                {
                    element = element.parentNode
                    if( element.tagName.toLowerCase() == "form" )
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
            <h2>Select a game</h2>
            <h4>Resemble Games</h4>

                <c:forEach items="${resembleGames}" var="game">
                        <form action="choosegameCompletionlist" method="post">
                            <input type="hidden" name="gameid" id="gameid" value="${game.gameId}" />
                            <div class = "gamelink"><a href ="choosegame" id="gameLinkA" onclick =" get_form(this).submit(); return false"><c:out value = "${game.getGamename()}"/></a></div>
                        </form>
                </c:forEach>
            <h4>MultiChoice Games</h4>
            <c:forEach items="${multiChoiceGames}" var="game">
                    <form action="choosegameCompletionlist" method="post">
                        <input type="hidden" name="gameid" id="gameid" value="${game.name}" />
                        <div class = "gamelink"><a href ="choosegame" id="gameLinkA" onclick =" get_form(this).submit(); return false"> <c:out value = "${game.name}"/></a></div>
                    </form>
            </c:forEach>
            </center>
        </div>
    </body>
</html>
