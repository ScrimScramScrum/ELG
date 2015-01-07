<%-- 
    Document   : resemblegame
    Created on : Jan 7, 2015, 10:53:32 AM
    Author     : borgarlie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <title>Start Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script src="resemble.js"></script>
        <script src="html2canvas.js"></script>

        <style>
            .block {
                float: left;
                margin: 5px;
                padding: 5px;
                background-color: #dddddd;
            }
            
            .renderedFrame, .codeBox {
                width: 100px; height: 80px
            }
        </style>
        
        <script>
            $(document).ready(function() {
                
                var solutionHtml = "<!DOCTYPE html><html><body>Hei!<br><table border='1'><tr><td>hei</td><td>hei</td></tr></table></body></html>";
                var solutionCss = "body {background-color: #b0c4de;} table {background-color: red}";
                
                var startingHtml = "<!DOCTYPE html><html><body>Hei!</body></html>";
                var startingCss = "";
                
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
                            resembleIfBothLoaded(resultUrl, solutionUrl);
                        }
                    });
                    html2canvas($("#solutionFrame").contents().find("body"), {
                        onrendered: function (canvas) {
                            solutionUrl = canvas.toDataURL('image/png'); 
                            resembleIfBothLoaded(resultUrl, solutionUrl);
                        }
                    });
                });            
            });
            
            function setRenderedResult(frame, html, css) {
                frame.contents().find("html").html(html);
                var $head = frame.contents().find("head");                
                $head.append("<style>" + css + "</style>") 
            }

            function resembleIfBothLoaded(resultUrl, solutionUrl) { 
                if(resultUrl && solutionUrl) {
                    resemble(resultUrl).compareTo(solutionUrl).onComplete(function(data){
                        alert("Likhet: " + (100 - data.misMatchPercentage) + "%");
                    });            
                }
            }
        </script>
    </head>
        
    <body>

        ${resembleTask.startingHTML}
        <br>
        ${resembleTask.solutionHTML}
        <br>hehehe

    <section id="content">
        <section class="block">        
            <p>Oppgave</p>
            <p>Du skal sette bakgrunnsfargen på siden til fargen #b0c4de.</p>
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
</html>
