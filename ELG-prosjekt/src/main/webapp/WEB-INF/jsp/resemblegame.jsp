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

        <!-- beautifier -->
        <script src="<c:url value='/resources/jsbeautifier/lib/beautify.js' />"></script>
        <script src="<c:url value='/resources/jsbeautifier/lib/beautify-css.js' />"></script>
        <script src="<c:url value='/resources/jsbeautifier/lib/beautify-html.js' />"></script>

        <script>
            var the = {
                use_codemirror: (!window.location.href.match(/without-codemirror/)),
                beautify_in_progress: false,
                editor: null // codemirror editor
            };

            function beautify(nr) {
                if (the.beautify_in_progress) return;

                the.beautify_in_progress = true;

                if (the.use_codemirror && typeof CodeMirror !== 'undefined') {
                    the.editor = $('.CodeMirror')[nr].CodeMirror;
                }

                var source = the.editor ? the.editor.getValue() : $('#htmlView').val(),
                    output,
                    opts = {};

                opts.indent_size = $('#tabsize').val();
                opts.indent_char = opts.indent_size == 1 ? '\t' : ' ';
                opts.max_preserve_newlines = $('#max-preserve-newlines').val();
                opts.preserve_newlines = opts.max_preserve_newlines !== "-1";
                opts.keep_array_indentation = $('#keep-array-indentation').prop('checked');
                opts.break_chained_methods = $('#break-chained-methods').prop('checked');
                opts.indent_scripts = $('#indent-scripts').val();
                opts.brace_style = $('#brace-style').val();
                opts.space_before_conditional = $('#space-before-conditional').prop('checked');
                opts.unescape_strings = $('#unescape-strings').prop('checked');
                opts.jslint_happy = $('#jslint-happy').prop('checked');
                opts.end_with_newline = $('#end-with-newline').prop('checked');
                opts.wrap_line_length = $('#wrap-line-length').val();

                if (looks_like_html(source)) {
                    output = html_beautify(source, opts);
                } else {
                    output = css_beautify(source, opts);
                }
                if (the.editor) {
                    the.editor.setValue(output);
                } else {
                    $('#htmlView').val(output);
                }

                the.beautify_in_progress = false;
            }

            function looks_like_html(source) {
                var trimmed = source.replace(/^[ \t\n\r]+/, '');
                var comment_mark = '<' + '!-' + '-';
                return (trimmed && (trimmed.substring(0, 1) === '<' && trimmed.substring(0, 4) !== comment_mark));
            }
        </script>
        <!-- end beautifier -->
        
        <style>
            .renderedFrame, .codeBox {
                <c:out value="width: ${resembleTask.width}px; height: ${resembleTask.height}px" />
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
                    beautify(0); 
                    beautify(1);
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
                    lineNumbers: true,
                    indentUnit: 4
                });
                    editableCodeMirror.setSize("100%", 300); 
                var editableCodeMirror2 = CodeMirror.fromTextArea(document.getElementById('htmlView'), {
                    lineWrapping: true,        
                    mode: "xml",
                    theme: "default",
                    lineNumbers: true,
                    indentUnit: 4
                });

                beautify(0); 
                beautify(1);

                editableCodeMirror2.setSize("100%", 300); 

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
                            document.getElementById('scoreText').innerHTML = "Prosent riktig: " +test; 
                            document.getElementById("score").value = test;
                        }
                    }
                });

                html2canvas($("#solutionFrame").contents().find("body"), {
                    onrendered: function (canvas) {
                        solutionUrl = canvas.toDataURL('image/png'); 
                        test = resembleIfBothLoaded(resultUrl, solutionUrl, score);
                        if(test!=null){
                            document.getElementById('scoreText').innerHTML = "Prosent riktig: " + test; 
                            document.getElementById("score").value = test;
                        }
                    }
                });
             }

        </script>
        <div id="resemblegamewrapper">  

            <div id = "resembleTop">
                <div id ="tasktext">
                    Oppgave
                      <input type="button" value="Hent løsning" id="getSolution">
                      <br>
                    ${resembleTask.taskText}
                </div>

                
                <div id="solutionDiv">
                    <center>
                    Fasit<br>
                    <!-- <div id = "iframediv"> -->
                    
                        <iframe class="renderedFrame" id = "solutionFrame" src="about:blank"></iframe>
                    <!-- </div> -->
                </center>
                </div>

                
                <div id="resultDiv">
                    <center>
                    Ditt resultat<br>
                    <!-- <div id = "iframediv"> -->
                        
                        <iframe class="renderedFrame" id ="resultFrame" src="about:blank"></iframe>
                    </center>
                    <!-- </div> -->
                </div>
            </div>

            <div id = "resembleBottom">
                <div id ="bottomLeft">      
                    <center>CSS</center>
                    <textarea class="cssView" id="cssView" name ="cssView"></textarea>
                </div>
                <div id="bottomRight">
                    <center>HTML</center>
                    <textarea class="cssView" id="htmlView"></textarea>
                </div>
            </div>
            <div id = "resembleBeloWbottom">
                <div id = "scoreText"></div>

                <div id = "resemblebutton">
                    <c:choose>
                        <c:when test="${resembleGame.isCurrentTaskLast()}">
                            <form action = "finishgame" name = "scorePost" id="scorePost" onsubmit="return validateForm()" method="post" >
                                <input type = "hidden" value = "" id = "score" name = "score"/>

                                <input type = "submit" id ="resembleSubmit" value = "Finish" />
                            </form>
                        </c:when>
                        <c:otherwise>
                            <form action = "nextresembletask" name = "scorePost" id="scorePost" onsubmit="return validateForm()" method="post">
                                <input type = "hidden" value = "" id = "score" name = "score"/>
                                <input type = "submit" value = "next" id ="resembleSubmit" />
                            </form>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </body>
</html>
