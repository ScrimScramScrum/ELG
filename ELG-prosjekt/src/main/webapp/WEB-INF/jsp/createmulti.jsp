<%-- 
    Document   : createmulti
    Created on : 18.jan.2015, 18:02:43
    Author     : eiriksandberg
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script>
            function myFunction() {
                document.getElementById("createEx").reset();
            }
        </script>
    </head>
    <body>
        <h1>Lag ditt eget multiple choice spill!</h1> 
        <h2>Spørsmål</h2><br><br>
        <form:form action="createExercise" modelAttribute="createExercise" method = "POST" id ="createEx">
            Spørsmål nummer : <c:out value="${teller}"/><br>
            Skriv inn oppgaven her: <form:input path = "taskText" type ="text" id="task"/><br>
            Skriv fasiten her: <form:input path = "answer" type ="text" id="answer"/><br>
            <br>
            Alternativ 1: <form:input path = "alt1" type ="text" id="alt1"/><br>
            Alternativ 2: <form:input path = "alt2" type ="text" id="alt2"/><br>           
            Alternativ 3: <form:input path = "alt3" type ="text" id="alt3"/><br>   
            Alternativ 4: <form:input path = "alt4" type ="text" id="alt4"/><br>
            <input type ="submit" value="Lagre spørsmål"><br>
        </form:form>


        <form:form action ="submitgame" modelAttribute="MultiGame" method = "POST">
            <h2>Generell info: </h2><br><br>
            Navn på spillet: <form:input path = "name" type ="text" id ="gamename"/><br>
            Informasjon om spillet: <form:input path = "info" type ="text" id ="info"/><br>
                Vanskelighetsgrad: <form:select path ="difficulty" id="difficulty">
                <form:option value="1" label ="1"/>
                <form:option value="2" label ="2"/>
                <form:option value="3" label ="3"/>
            </form:select>
                <br>
            Læringsmål: <form:input path ="learningGoals" type ="text" id ="learning_goals"/><br><br>
            <input type ="submit" value="Lagre spill">
        </form:form>
    </body>
</html>
