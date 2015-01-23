<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel = "stylesheet" type = "text/css" href = "<c:url value='/resources/css2/styleGamemenu.css'/>">

        <style type="text/css">
            #gamebutton {
                width: 150px;
                height: 60px;
            }
            
            #mainbar {
                width: 75%;
                float: left; 

            }
            #rightbar {
                width: 25%;
                float: right; 

            }
        </style>
    </head>
    <body>
        <center> 
            <div id="mainbar">
                <h1>Du greide følgende poengsummer: </h1>
                <br></br>
                <c:forEach items="${resembleGame.taskNumbers}" var="taskNumber" varStatus="status">
                    <li>
                        Oppgave nr: <c:out value ="${status.index+1}"/> , Poengsum: <c:out value ="${resembleGame.taskScores[status.index]}"/>
                    </li>
                </c:forEach>
                    Total poengsum: <c:out value="${resembleGame.totalScore}"/>
                    <br><br>
                    Du må ha minst 90% for å få godkjent
            </div>
        </center>
        <div id="rightbar"></div> 
    </body>
</html>
