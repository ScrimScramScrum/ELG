<%-- 
    Document   : about
    Created on : Jan 8, 2015, 3:01:20 PM
    Author     : Matias
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <!-- <link href="../../resources/css3/bootstrap.css" rel="stylesheet" type="text/css"/> -->
        <style type="text/css">
            #moose {
                /*1280 × 860*/
                width: 320px;
                height: 215px;
            }
            #aboutmid {
                height: 300px;
            }
        </style>
    </head>
    <body>
        <div id="aboutmid">
            <center>
                <h1>Educational Learning Game</h1>
                <br>
                <p>
                Dette er et spill laget i forbindelse med et skoleprosjekt av Team 4. Hensikten med dette spillet er å
                lære bort CSS og HTML til nybegynnere.
                </p>
                
                Test
                <form action="<c:url value="/kOdesLostTags"/>" >
                    <input type="submit" value="K.Odes Lost Tags">
                </form>
            </center>
        </div>
        <div>
            <center>
                <!-- License: CC0 Public Domain -->
                <!-- http://pixabay.com/en/moose-deer-animal-mammal-wild-145926/ -->
                <img src="<c:url value='/resources/images/moose-145926_1280.png'/>" id="moose">
            </center>
        </div>     
    </body>
</html>
