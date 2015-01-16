<%-- 
    Document   : resemblegame
    Created on : Jan 7, 2015, 10:53:32 AM
    Author     : borgarlie
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <title>Startside</title>
        <link rel = "stylesheet" type = "text/css" href = "<c:url value='/resources/css/style.css'/>">

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
        </script>
    </head>
    <body>
        <script>
            window.onload = function(){
                    var editableCodeMirror = CodeMirror.fromTextArea(document.getElementById('cssViewSolution'), {
                    lineWrapping: true,
                    mode: "css",
                    theme: "default",
                    lineNumbers: true,
                    indentUnit: 4, 
                    extraKeys: {
                        "Ctrl-Enter": function(cm) {
                            testFunc(); 
                            testFunc(); 
                        },
                        "Cmd-Enter": function(cm) {
                            testFunc(); 
                            testFunc(); 
                        }
                    }
                });
                
                var editableCodeMirror2 = CodeMirror.fromTextArea(document.getElementById('htmlViewSolution'), {
                    lineWrapping: true,        
                    mode: "xml",
                    theme: "default",
                    lineNumbers: true,
                    indentUnit: 4,
                    extraKeys: {
                        "Ctrl-Enter": function(cm) {
                            testFunc();
                            testFunc(); 
                        },
                        "Cmd-Enter": function(cm) {
                            testFunc();
                            testFunc(); 
                        }
                    }
                });

                var editableCodeMirror3 = CodeMirror.fromTextArea(document.getElementById('cssViewStarting'), {
                    lineWrapping: true,
                    mode: "css",
                    theme: "default",
                    lineNumbers: true,
                    indentUnit: 4, 
                    extraKeys: {
                        "Ctrl-Enter": function(cm) {
                            testFunc2(); 
                            testFunc2(); 
                        },
                        "Cmd-Enter": function(cm) {
                            testFunc2(); 
                            testFunc2(); 
                        }
                    }
                });
                
                var editableCodeMirror4 = CodeMirror.fromTextArea(document.getElementById('htmlViewStarting'), {
                    lineWrapping: true,        
                    mode: "xml",
                    theme: "default",
                    lineNumbers: true,
                    indentUnit: 4,
                    extraKeys: {
                        "Ctrl-Enter": function(cm) {
                            testFunc2();
                            testFunc2(); 
                        },
                        "Cmd-Enter": function(cm) {
                            testFunc2();
                            testFunc2(); 
                        }
                    }
                });
                var width = "100%"; 
                var height = 300; 
                editableCodeMirror.setSize(width, height); 
                editableCodeMirror2.setSize(width, height); 
                editableCodeMirror3.setSize(width, height); 
                editableCodeMirror4.setSize(width, height); 
             };


            function testFunc(){
                var editor = $('.CodeMirror')[0].CodeMirror;
                var cssText = editor.getValue(); 
                var editor = $('.CodeMirror')[1].CodeMirror;
                var htmlText = editor.getValue(); 
                setRenderedResult($("#solutionFrame"), htmlText, cssText);
             }


             function testFunc2(){
                alert("HEI");
                var editor = $('.CodeMirror')[2].CodeMirror;
                var cssText = editor.getValue(); 
                var editor = $('.CodeMirror')[3].CodeMirror;
                var htmlText = editor.getValue(); 
                setRenderedResult($("#resultFrame"), htmlText, cssText);
                document.getElementById('startingCSS').value = cssText; 
                alert(document.getElementById('startingCSS').value);
             }


        </script>
        <div id="resemblegamewrapper">  

            <div id = "resembleTop">
                <div id ="tasktextCreate">
                    Oppgavetekst: <br>
                    <textarea class="cssView" id="taskTextCreate" style="height:100px; resize:none;"></textarea>
                </div>

                <div id="solutionDivCreate">
                    <center>
                        Fasit<br>
                        <iframe class="renderedFrame" id = "solutionFrame" src="about:blank"></iframe>
                    </center>
                </div>   
                <div div id = "resultDivCreate">
                    <center>
                        Starting:
                        <iframe class="renderedFrame" id ="resultFrame" src="about:blank"></iframe>
                    </center>
                </div>
            </div>

            <div id = "resembleBottom">
                <div id ="bottomLeftCreate">      
                    <center>CSS solution</center>
                    <textarea class="cssView" id="cssViewSolution" name ="cssViewSolution"></textarea>
                </div>
                <div id="bottomRightCreate">
                    <center>HTML solution</center>
                    <textarea class="cssView" id="htmlViewSolution"></textarea>
                </div>
            </div>
            <div id = "resembleBelowBottomCreate">
                <div id ="bottomLeftCreate">      
                    <center>CSS starting</center>
                    <textarea class="cssView" id="cssViewStarting" name ="cssView"></textarea>
                </div>
                <div id="bottomRightCreate">
                    <center>HTML starting</center>
                    <textarea class="cssView" id="htmlViewStarting"></textarea>
                </div>
               
            </div>
            <form:form action="createresembletask" method="post" modelAttribute="createResembleTask" >
                <form:input path="taskText" id ="taskText" value="" type="hidden"/>
                <form:input path="solutionHTML" id ="solutionHTML"  type="hidden"/>
                <form:input path="solutionCSS" id ="solutionCSS" type="hidden"/>
                <form:input path="startingHTML" id ="startingHTML" type="hidden"/>
                <form:input path="startingCSS" id ="startingCSS" type="hidden"/>
                <form:input path="width" id ="width" type="hidden"/>
                <form:input path="height" id ="height" type="hidden"/>
                <input type ="submit" value ="Registrer deloppgave"></input>
            </form:form>
        </div>
    </body>
</html>
