<%-- 
    Document   : resemblegame
    Created on : Jan 7, 2015, 10:53:32 AM
    Author     : borgarlie
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <title>Start Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script src="<c:url value='/resources/resemble.js' />"></script>
        <script src="<c:url value='/resources/html2canvas.js' />"></script>

        <style>
            .block {
                float: left;
                margin: 5px;
                padding: 5px;
                background-color: #dddddd;
            }
            
            .renderedFrame, .codeBox {
                <c:out value="width: ${resembleTask.width}px; height: ${resembleTask.height}px" />
            }
        </style>
        
        <script>
            var score = 0; 
            var resembleIsPressed = false; 
            $(document).ready(function() {
                
                var solutionHtml = "${resembleTask.solutionHTML}";
                var solutionCss = "${resembleTask.solutionCSS}";
                
                var startingHtml = "${resembleTask.startingHTML}";
                var startingCss = "${resembleTask.startingCSS}";
                
                setRenderedResult($("#solutionFrame"), solutionHtml, solutionCss);

                $("#htmlView").val(startingHtml); 
                $("#cssView").val(startingCss); 
                               
                $("#viewResult").click(function() {
                    setRenderedResult($("#resultFrame"), $("#htmlView").val(), $("#cssView").val());
                });
                
                $("#getSolution").click(function() {
                    $("#htmlView").val(solutionHtml); 
                    $("#cssView").val(solutionCss); 
                });
                
                $("#compare").click(function() { 
                    var resultUrl = null;
                    var solutionUrl = null;
                    html2canvas($("#resultFrame").contents().find("body"), {
                        onrendered: function (canvas) {
                            resultUrl = canvas.toDataURL('image/png'); 
                            resembleIfBothLoaded(resultUrl, solutionUrl, score);
                        }
                    });
                    html2canvas($("#solutionFrame").contents().find("body"), {
                        onrendered: function (canvas) {
                            solutionUrl = canvas.toDataURL('image/png'); 
                            resembleIfBothLoaded(resultUrl, solutionUrl, score);
                        }
                    });
                });            
            });
            function setRenderedResult(frame, html, css) {
                frame.contents().find("html").html(html);
                var $head = frame.contents().find("head");                
                $head.append("<style>" + css + "</style>") 
            }

            function resembleIfBothLoaded(resultUrl, solutionUrl, score) { 
                if(resultUrl && solutionUrl) {
                    resemble(resultUrl).compareTo(solutionUrl).onComplete(function(data){
                        alert("Likhet: " + (100 - data.misMatchPercentage) + "%");
                        score = 100-data.misMatchPercentage; 
                        document.getElementById("score").value = score;
                        resembleIsPressed = true;
                    });            
                }
            }

            function validateForm() {
                var x = document.forms["scorePost"]["score"].value;
                if (x == 0 || x == ""||x==null) {
                    alert("Du må trykke på sammenlign");
                    return false;
                }
            }
        </script>
    </head>
        
    <body>

    <section id="content">
        <section class="block">        
            <p>Oppgave</p>
            <p>${resembleTask.taskText}</p>
            <input type="button" value="Se resultat" id="viewResult">
            <input type="button" value="Sammenlign" id="compare">
            <input type="button" value="Hent løsning" id="getSolution">
        </section>

        <section class="block">        
            <p>Fasit</p>
            <div id="solutionDiv">
                <iframe class="renderedFrame" id="solutionFrame" src="about:blank"></iframe>
            </div>
        </section>

        <section class="block">        
            <p>CSS</p>
            <textarea class="codeBox" id="cssView"></textarea>
        </section>

        <section class="block">        
            <p>HTML</p>
            <textarea class="codeBox" id="htmlView"></textarea>
        </section>

        <section class="block">        
            <p>Ditt resultat</p>
            <div id="resultDiv">
                <iframe class="renderedFrame" id="resultFrame" src="about:blank"></iframe>
            </div>
        </section>
    </section>


    <c:choose>
        <c:when test="${resembleGame.isCurrentTaskLast()}">
            <form action = "finishgame" name = "scorePost" id="scorePost" onsubmit="return validateForm()" method="post" >
                <input type = "hidden" value = "" id = "score" name = "score"/>
                <input type = "submit" value = "Finish" />
            </form>
            
        </c:when>
        <c:otherwise>
             <form action = "nextresembletask" name = "scorePost" id="scorePost" onsubmit="return validateForm()" method="post">
                <input type = "hidden" value = "" id = "score" name = "score"/>
                <input type = "submit" value = "next" />
            </form>
            
        </c:otherwise>
    </c:choose>
</html>
