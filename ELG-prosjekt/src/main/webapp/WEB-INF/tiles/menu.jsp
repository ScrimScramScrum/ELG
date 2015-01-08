<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <link rel = "stylesheet" type = "text/css" href = "<c:url value='/resources/style.css'/>">
        
</head>
<body>
<div id="topmenu">
    <ul style="list-style-type:none;">
        <li><a href="<c:url value="/index" />" >Index</a></li>
        <li><a href="<c:url value="/choosegame" />" >Games</a></li>
        <li><a href='#'>High Score</a></li>
        <li><a href='#'>About</a></li>
        <img src="resources/amoosing.jpg" height="60px" align="right" hspace="32px">
    </ul>
</div>
</body>
</html>



