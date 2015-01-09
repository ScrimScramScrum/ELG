<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <link rel = "stylesheet" type = "text/css" href = "<c:url value='/resources/css2/styleTopmenu.css'/>">
    
    
    <script>
        function changeId(var link){
            document.getElementById('selected').id='notSelected';
            link.id='selected';
            document.getElementsByName(link)[0].id ='selected';
        }
    </script>
        
</head>
<body>
    ELG-LOGO
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
</div> <!-- end bar div -->
</div> <!-- end topmenu div -->
</body>
</html>



