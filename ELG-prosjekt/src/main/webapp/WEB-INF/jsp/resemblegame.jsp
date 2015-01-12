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
        
        <link rel="stylesheet" href="http://codemirror.net/lib/codemirror.css">
        <script src="http://codemirror.net/lib/codemirror.js"></script>
        <script src="http://codemirror.net/addon/edit/matchbrackets.js"></script>
        <script src="http://codemirror.net/addon/edit/continuecomment.js"></script>
        
        <script src="http://codemirror.net/mode/css/css.js"></script>
        <script src="http://codemirror.net/mode/xml/xml.js"></script>
        
        <script src="<c:url value='/resources/resemble.js' />"></script>
        <script src="<c:url value='/resources/html2canvas.js' />"></script>
        <style>
            .renderedFrame, .codeBox {
                <c:out value="width: ${resembleTask.width}px; height: ${resembleTask.height}px" />
            }

            #tasktext {

            }

            #tasktext input {
                width: 32%;
            }

            #scoreText{

            }

            #left_1, #left_2 {
                float: left;
                width: 45%;
                background-color: #ddd;
                margin: 5px;
                padding: 5px;
            }

            #right_1, #right_2 {
                float: right;
                width: 45%;
                background-color: #ddd;
                margin: 5px;
                padding: 5px;
            }

            #scorePost input {
                width: 100%;
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

                $("#getSolution").click(function() {
                    $("#htmlView").val(solutionHtml); 
                    $("#cssView").val(solutionCss);
                    var editor = $('.CodeMirror')[0].CodeMirror;
                    editor.setValue(solutionCss); 
                    var editor2 = $('.CodeMirror')[1].CodeMirror;
                    editor2.setValue(solutionHtml); 
                });
            });
            function setRenderedResult(frame, html, css) {
                frame.contents().find("html").html(html);
                var $head = frame.contents().find("head");                
                $head.append("<style>" + css + "</style>") 
            }
            function resembleIfBothLoaded(resultUrl, solutionUrl, score) { 
                var points; 
                if(resultUrl && solutionUrl) {
                    resemble(resultUrl).compareTo(solutionUrl).onComplete(function(data){
                        score = 100-data.misMatchPercentage; 
                        points = 100-data.misMatchPercentage; 
                        test = score; 
                        resembleIsPressed = true;
                    });            
                    return points; 
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
        <script>
            window.onload = function(){
                    var editableCodeMirror = CodeMirror.fromTextArea(document.getElementById('cssView'), {
                    lineWrapping: true,
                    mode: "css",
                    theme: "default",
                    lineNumbers: true
                });
                var editableCodeMirror2 = CodeMirror.fromTextArea(document.getElementById('htmlView'), {
                    lineWrapping: true,        
                    mode: "xml",
                    theme: "default",
                    lineNumbers: true
                });

                editableCodeMirror.on("changes", function(cm, change){
                    testFunc();
                    testFunc();
                });

                editableCodeMirror2.on("changes", function(cm, change){
                    testFunc();
                    testFunc();

                });
             };

             function testFunc(){
                var editor = $('.CodeMirror')[0].CodeMirror;
                var cssText = editor.getValue(); 
                var editor = $('.CodeMirror')[1].CodeMirror;
                var htmlText = editor.getValue(); 
                setRenderedResult($("#resultFrame"), htmlText, cssText);
                var resultUrl = null;
                var solutionUrl = null;
                var test; 
                html2canvas($("#resultFrame").contents().find("body"), {
                    onrendered: function (canvas) {
                        resultUrl = canvas.toDataURL('image/png'); 
                        test = resembleIfBothLoaded(resultUrl, solutionUrl, score);
                        if(test!=null){
                            document.getElementById('scoreText').innerHTML = test; 
                            document.getElementById("score").value = test;
                        }
                    }
                });

                html2canvas($("#solutionFrame").contents().find("body"), {
                    onrendered: function (canvas) {
                        solutionUrl = canvas.toDataURL('image/png'); 
                        test = resembleIfBothLoaded(resultUrl, solutionUrl, score);
                        if(test!=null){
                            document.getElementById('scoreText').innerHTML = test; 
                            document.getElementById("score").value = test;
                        }
                    }
                });
             }

        </script>
        <div id="wrapper">  
            <div id="tasktext">    
                <p>Oppgave</p>
                <p>${resembleTask.taskText}</p>
                <center>
                    <input type="button" value="Hent løsning" id="getSolution">
                </center>
            </div>
            <div id="left_1"> 
                <div>      
                    <p>CSS</p>
                    <textarea class="cssView" id="cssView" name ="cssView"></textarea>
                </div>
                <div>
                    <p>HTML</p>
                    <textarea class="cssView" id="htmlView"></textarea>
                </div>
            </div>
            <div id="right_1">      
                <p>Fasit</p>
                <div>
                    <div id="solutionDiv">
                        <iframe class="renderedFrame" id="solutionFrame" src="about:blank"></iframe>
                    </div>
                </div>
                <div>
                    <p>Ditt resultat</p>
                    <div id="resultDiv">
                        <iframe class="renderedFrame" id="resultFrame" src="about:blank"></iframe>
                    </div>
                </div>
                <div>
                    <div id = "scoreText"></div>
                </div>
            </div>
            <div id="button">
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
            </div>
        </div>
    </body>
</html>
