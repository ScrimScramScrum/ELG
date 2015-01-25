<%@page import="springmvc.domain.User"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
    <head>
        <link rel = "stylesheet" type = "text/css" href = "<c:url value='/resources/css/style.css'/>">
        <link rel = "stylesheet" type = "text/css" href = "<c:url value='/resources/css/style-1000px.css'/>">
        <link rel = "stylesheet" type = "text/css" href = "<c:url value='/resources/css/style-desktop.css'/>">
        <script src="<c:url value='/resources/js/jquery.min.js' />"></script>
        <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800" rel="stylesheet" type="text/css" />
    </head>
    <body class="homepage">
        <!-- Header -->
        <div id="header-wrapper">
            <div id="header" class="container">
                <div id="logo"><h1><a href="<c:url value="/about" />">ELG</a></h1></div>
                <nav id="nav">
                    <ul>
                        <li><a href="<c:url value="/choosegame" />" name = 'choosegame' id="chooseGame">  Øving</a></li>
                        <li><a href="<c:url value="/chooseothergames" />" name = 'chooseothergames' id="chooseOtherGames">  Andre spill</a></li>
                        <li><a href="<c:url value="/highscore" />" name = 'highscore' id="highscore">  High Score</a></li>
                        <%
                            User user = (User)session.getAttribute("user");
                            if(user.isAdmin()){%>
                        <li><a href="<c:url value="/completionlist" />" name ="completionlist" id="completion">Godkjenningsliste</a></li>
                         <%}%> 
                        <li><a href="<c:url value="/about" />" id="about" name =' about'>Om ELG</a></li>
                        <%
                            if(!(user.getEmail().equals("GUEST"))){%>
                                <li><a href="<c:url value="/creategame" />" name = 'creategame' id="creategame">Lag spill</a></li>
                                <li><a href="<c:url value="/administrateAccount" />" name ="settings" id="settings">Innstillinger</a></li>
                         <%}%> 
                        <li><a href="<c:url value="/logout" />" name ="logout" id="logout">Logg ut</a></li>
                    </ul>
                </nav>
            </div>
        </div>
    </body>
</html>



