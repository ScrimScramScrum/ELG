<%-- 
    Document   : completionlist_gamelist
    Created on : 13.jan.2015, 15:55:35
    Author     : eiriksandberg
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
                <script type="text/javascript">
            //<![CDATA[
            function get_form( element )
            {
                while( element )
                {
                    element = element.parentNode
                    if( element.tagName.toLowerCase() == "form" )
                    {
                        //alert( element ) //debug/test
                        return element
                    }
                }
                return 0; //error: no form found in ancestors
            }
            //]]>
        </script>
    </head>
    <body>
                <div id="sidebar">
            <center>
            <h2>Velg en klasse</h2>
            <h4>Klasser:</h4>

                <c:forEach items="${allClasses}" var="classChosen">
                        <form action="choosegameCompletionlist" method="post">
                            <input type="hidden" name="classid" id="classid" value="${classChosen}" />
                            <div class = "gamelink"><a href ="completionlist" id="gameLinkA" onclick =" get_form(this).submit(); return false"><c:out value = "${classChosen}"/></a></div>
                        </form>
                </c:forEach>
            </center>
        </div>
    </body>
</html>
