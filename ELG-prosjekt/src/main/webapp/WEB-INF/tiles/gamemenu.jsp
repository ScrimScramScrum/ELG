<%-- 
    Document   : gamemenu
    Created on : Jan 7, 2015, 4:22:43 PM
    Author     : borgarlie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>



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
            <h2>Velg spill</h2>
            <h4>Resemble Games</h4>

                <c:forEach items="${resembleGames}" var="game">
                        <form action="choosegame" method="post">
                            <input type="hidden" name="gameid" id="gameid" value="${game.gameId}" />
                            <div class = "gamelink"><a href ="choosegame" id="gameLinkA" onclick =" get_form(this).submit(); return false"><c:out value = "${game.getGamename()}"/></a></div>
                        </form>
                </c:forEach>
            <h4>MultiChoice Games</h4>
            <c:forEach items="${multiChoiceGames}" var="game">
                    <form action="choosegame" method="post">
                        <input type="hidden" name="gameid" id="gameid" value="${game.name}" />
                        <div class = "gamelink"><a href ="choosegame" id="gameLinkA" onclick =" get_form(this).submit(); return false"> <c:out value = "${game.name}"/></a></div>
                    </form>
            </c:forEach>
            </center>
        </div>
    </body>
</html>
