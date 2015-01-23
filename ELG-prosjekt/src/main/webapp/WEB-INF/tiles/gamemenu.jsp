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
            function get_form( element ){
                while( element ){
                    element = element.parentNode
                    if( element.tagName.toLowerCase() == "form" ) {
                        //alert( element ) //debug/test
                        return element
                    }
                }
                return 0; //error: no form found in ancestors
            }
        </script>
    </head>
    <body>
        <div id="sidebar">
            <center>
            <h2>Øving</h2>
            <h4>Likhetsspill</h4>
                <c:forEach items="${resembleGames}" var="game">
                    <form action="choosegame" method="post">
                        <div class = "gamelink" onclick =" get_form(this).submit(); return false">
                            <input type = "hidden" name="gametype" value = "resemble">                          
                            <input type="hidden" name="gameid" id="gameid" value="${game.gameId}" />
                            <table>
                                <tr>  
                                    <td>
                                        ${game.getGamename()}
                                    </td>
                                    <c:choose> 
                                        <c:when test="${game.approved == 1}">                                    
                                                <td>
                                                    <img src="<c:url value="/resources/images/check.png"/>" >
                                                </td>
                                        </c:when>                         
                                    </c:choose>                                            
                                </tr>
                            </table>   
                        </div>
                    </form>
                </c:forEach>
            <h4>Flervalgsspill</h4>
            <c:forEach items="${multiChoiceGames}" var="game">
                <form action="choosegame" method="post">
                    <div class = "gamelink" onclick =" get_form(this).submit(); return false"> 
                    <input type="hidden" name="gameid" id="gameid" value="${game.name}" />
                    <input type = "hidden" name="gametype" value = "multichoice">
                        <table>        
                            <tr>
                                <td>${game.name}</td>
                                <c:choose> 
                                    <c:when test="${game.approved == 1}">                                    
                                        <td>
                                            <img src="<c:url value="/resources/images/check.png"/>" >
                                        </td>
                                    </c:when>                         
                                </c:choose>
                            </tr>
                        </table> 
                    </div>                        
                </form>
            </c:forEach>
            <br></br>
            <h2>Annet </h2>
            <h4>Likhetsspill</h4>
            <c:forEach items="${resembleGamesExtra}" var="game">
                    <form action="choosegame" method="post">
                        <input type = "hidden" name="gametype" value = "resemble">
                        <input type="hidden" name="gameid" id="gameid" value="${game.gameId}" />
                        <div class = "gamelink" onclick =" get_form(this).submit(); return false">
                            <table>        
                                <tr>
                                    <td>${game.gamename}</td>
                                    <c:choose> 
                                        <c:when test="${game.approved == 1}">                                    
                                            <td>
                                                <img src="<c:url value="/resources/images/check.png"/>" >
                                            </td>
                                        </c:when>                         
                                    </c:choose>
                                </tr>
                            </table> 
                        </div>
                    </form>
            </c:forEach>
            <h4>Flervalgsspill</h4>
            <c:forEach items="${multiChoiceGamesExtra}" var="game">
                    <form action="choosegame" method="post">
                        <input type = "hidden" name="gametype" value = "multichoice">
                        <input type="hidden" name="gameid" id="gameid" value="${game.name}" />
                        <div class = "gamelink" onclick =" get_form(this).submit(); return false"> 
                            <table>        
                                <tr>  
                                    <td>
                                        ${game.name}
                                    </td>                        
                                    <c:choose> 
                                        <c:when test="${game.approved == 1}">                                    
                                            <td>
                                                <img src="<c:url value="/resources/images/check.png"/>" >
                                            </td>
                                        </c:when>                         
                                    </c:choose>
                                </tr>
                            </table> 
                        </div>                        
                    </form>
            </c:forEach>
            </center>
        </div>
    </body>
</html>
