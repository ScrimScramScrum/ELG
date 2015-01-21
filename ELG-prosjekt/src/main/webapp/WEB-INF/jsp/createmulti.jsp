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
        <h1 style ="text-align: center">Lag ditt eget multiple choice spill!</h1><br> 
        <%int i = 1;%>
    <center><table>
            <tr>
                <td colspan = 2 style ="text-align: center">
                    <h2>Spørsmål</h2>
                </td>
            </tr>
            <form:form action="createExercise" modelAttribute="createExercise" method = "POST" id ="createEx">
                <tr>
                    <td colspan = "2" style ="text-align: center">
                        Spørsmål registrert : ${Exercises.size()}
                    </td>
                </tr>
                <tr>
                    <td>Skriv inn oppgaven her:</td>
                    <td><form:input path = "taskText" type ="text" id="task"/></td>
                    <td><form:errors path = "taskText" htmlEscape ="false"/></td>
                </tr>
                <tr>
                    <td>Skriv fasiten her:</td><td><form:input path = "answer" type ="text" id="answer"/></td>
                    <td><form:errors path = "answer" htmlEscape ="false"/></td>
                </tr>
                <tr>
                    <td>Alternativ 1:</td><td><form:input path = "alt1" type ="text" id="alt1"/></td>
                    <td><form:errors path = "alt1" htmlEscape ="false"/></td>
                </tr>
                <tr>
                    <td>Alternativ 2:</td><td><form:input path = "alt2" type ="text" id="alt2"/></td>
                    <td><form:errors path = "alt2" htmlEscape ="false"/></td>
                </tr>
                <tr>
                    <td>Alternativ 3:</td><td><form:input path = "alt3" type ="text" id="alt3"/></td>
                    <td><form:errors path = "alt3" htmlEscape ="false"/></td>
                </tr>
                <tr>
                    <td>Alternativ 4:</td><td><form:input path = "alt4" type ="text" id="alt4"/></td>
                    <td><form:errors path = "alt4" htmlEscape="false"/></td>
                </tr>
                <tr>
                    <td colspan ="2" style="text-align: center"><input type ="submit" value="Lagre spørsmål"></td>
                </tr>
            </form:form>
        </table></center>
    <br>

    <center><table>
            <form:form action ="submitgame" modelAttribute="MultiGame" method = "POST">
                <tr>
                    <td colspan = 2 style ="text-align: center">
                        <h2>Generell info</h2>
                    </td>
                </tr>
                <tr>
                    <td>Navn på spillet:</td>
                    <td><form:input path = "name" type ="text" id ="gamename"/></td>
                    <td><form:errors path = "name" htmlEscape="false"/></td>
                </tr>
                <tr>
                    <td>Informasjon om spillet:</td>
                    <td><form:input path = "info" type ="text" id ="info"/></td>
                    <td><form:errors path = "info" htmlEscape="false"/></td>
                </tr>
                <tr>
                    <td>Vanskelighetsgrad:</td>
                    <td><form:select path ="difficulty" id="difficulty">
                            <form:option value="1" label ="1"/>
                            <form:option value="2" label ="2"/>
                            <form:option value="3" label ="3"/>
                        </form:select></td>
                    <td><form:errors path = "difficulty" htmlEscape="false"/></td>
                </tr>
                <tr>
                    <td>Læringsmål:</td>
                    <td><form:input path ="learningGoals" type ="text" id ="learning_goals"/></td>
                    <td><form:errors path = "learningGoals" htmlEscape="false"/></td>
                </tr>
                <tr>
                    <td colspan = 2 style ="text-align: center">
                        <input type ="submit" value="Lagre spill" onclick="return confirm('Har du lagt til alle spørsmålene du ønsker?')">
                    </td>
                </tr>
            </form:form>
        </table>
         ${ErrorMessage}
    </center>
</body>
</html>
