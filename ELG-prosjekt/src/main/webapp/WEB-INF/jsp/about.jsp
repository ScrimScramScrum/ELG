<%-- 
    Document   : about
    Created on : Jan 8, 2015, 3:01:20 PM
    Author     : Matias
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
    <head>
        <!-- <link href="../../resources/css3/bootstrap.css" rel="stylesheet" type="text/css"/> -->
        <style type="text/css">
            #moose {
                /*1280 × 860*/
                padding-top: 5%;
                width: 30%;
                height: auto;
            }

            #wrapperAbout {
                height: auto;
                width: 100%;
            }

            #developers {
                width: 20%;
                float: left;
            }
            #aboutmid {
                width: 60%;
                float: left;
            }
            #rightbar {
                width: 20%;
                float: left;
            }
        </style>
    </head>
    <body>
        <div id="wrapperAbout">
            <div id = "developers">
                <center>
                    <h1>
                        Utviklere
                    </h1>

                    <br>
                    Lars Garberg
                    <br>
                    Bjørn Hoxmark
                    <br>
                    Matias Mahle
                    <br>
                    Borgar Rannem Lie
                    <br>
                    Eirik Sandberg
                    <br>
                    Jørgen Wilhelmsen
                    <br>
                    <br>
                    <!-- License: CC0 Public Domain -->
                    <!-- http://pixabay.com/en/moose-deer-animal-mammal-wild-145926/ -->
                    <img src="<c:url value='/resources/images/moose-145926_1280.png'/>" width = 80%; id = "moose">
                </center>
            </div>
            <div id="aboutmid">
                <center>
                    <h1>Educational Learning Game</h1>
                    <br>
                        <spring:message code="aboutELG" />
                    <br>
                    <br>
                    <h1><strong>BONUS</strong></h1>
                    <p>
                        <spring:message code="profKode" />
                    </p>
                    <a href="<c:url value="/kOdesLostTags"/>"><img src="<c:url value='/resources/kOdesLostTags/kOdesLostTagsJS/ProfessorK-OdeHode.png'/>" width="15%" height="auto"></a>
                    </center>
                </div>
                <div id="rightbar">
                </div>
            </div>
        </body>
    </html>
