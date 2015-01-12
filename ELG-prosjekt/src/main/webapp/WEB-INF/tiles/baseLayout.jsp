<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body bgcolor="gray" >
        <table border="0" cellpadding="0" cellspacing="0" width="100%" align="center">
            <tr>
                
                <td width="100%" height="100" valign="top">
                    <tiles:insertAttribute name="menu" />
                    <!--<hr>-->
                </td>
            </tr>
            <tr>
               <td width="100%" valign="top">
                     <tiles:insertAttribute name="body" />
                </td>
            </tr>
     
            </tr>
        </table>
    </body>
</html>
