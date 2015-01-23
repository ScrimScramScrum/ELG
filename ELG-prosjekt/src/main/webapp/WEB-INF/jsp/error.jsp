<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            #gif{
                margin-left: 5%; 
            }
            
            body {
                background-color: gray; 
            }
        </style>
    </head>
    <body>
        <center>
            <h2>Wups! Her gikk noe galt. </h2>
            <br> <h3>Professor Kristian Ode er satt på saken, og prøver å fikse feilen. </h3>
            <a href="<c:url value="/kOdesLostTags"/>"><img src="<c:url value='/resources/kOdesLostTags/kOdesLostTagsJS/kOdesLostTagsImages/kodeStart/kodeGif.gif'/>" width="auto" height="auto" id="gif"/></a>
            <br> <h5>Trykk på K. Ode for å hjelpe han med feilen. </h5>
        </center> 
    </body>
</html>
