<%@page import="springmvc.domain.User"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
    <head>
        <link rel = "stylesheet" type = "text/css" href = "<c:url value='/resources/css/style.css'/>">
        <link rel = "stylesheet" type = "text/css" href = "<c:url value='/resources/css/style-1000px.css'/>">
        <link rel = "stylesheet" type = "text/css" href = "<c:url value='/resources/css/style-desktop.css'/>">
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>

        <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800" rel="stylesheet" type="text/css" />


        <script>
            function changeId(var link) {
                document.getElementById('selected').id = 'notSelected';
                link.id = 'selected';
                document.getElementsByName(link)[0].id = 'selected';
            }
        </script>

    </head>
    <body class="homepage">
        <!-- Header -->
        <div id="header-wrapper">

            <div id="header" class="container">
                <div id="logo"><h1><a href="#">ELG</a></h1></div>
                <nav id="nav">
                    <ul>
                        <li><a href="<c:url value="/choosegame" />" name = 'choosegame' id="games" onClick ="changeId(this)">Spill</a></li>
                        <li><a href="<c:url value="/highscore" />" name = 'highscore' id="highscore" onClick ="changeId(this)">High Score</a></li>
                        <%
                            User user = (User)session.getAttribute("user");
                            if(user.isAdmin()){%>
                        <li><a href="<c:url value="/completionlist" />" name ="completionlist" id="completion" onClick ="changeId(this) ">Godkjenningsliste</a></li>
                         <%}%>       
                        <li><a href="<c:url value="/about" />" id="about" name =' about' onClick ="changeId(this)">Om ELG</a></li>
                        <%
                            if(!(user.getEmail().equals("GUEST"))){%>
                        <li class="current_page_item"><a href="<c:url value="/administrateAccount" />" name ="settings" id="settings" onClick ="changeId(this)">Innstillinger</a></li>
                         <%}%> 
                        <li><a href="<c:url value="/logout" />" name ="logout" id="logout" onClick ="changeId(this) ">Logg ut</a></li>
                    </ul>
                </nav>

            </div>



        </div>
        <!-- Header Ends Here -->





        <!--
    <div id="topmenu">
    <div id="bar">
        <ul style="list-style-type:none;">
            <li><a href="<c:url value="/index" />" name ="index" id="index" onClick ="changeId(this) ">Index</a></li>
            <li><a href="<c:url value="/choosegame" />" name = 'choosegame' id="games" onClick ="changeId(this)">Games</a></li>
            <li><a href="<c:url value="/highscore" />" name = 'highscore' id="highscore" onClick ="changeId(this)">High Score</a></li>
            <li><a href="<c:url value="/about" />" id="about" name =' about' onClick ="changeId(this)">About</a></li>
            <div id="wheel"><li><a><img src="resources/images/teethwheel.jpg" height="40px" vspace="10px"></a>
                    <ul><li>
                        heiheihei
                        </li></ul>
                </li></div>
            <img src="resources/images/amoosing.jpg" height="60px" align="right" style="margin-right:26px"; />
        </ul>
    </div> <!-- end bar div 
    </div> <!-- end topmenu div -->
    </body>
</html>



