<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title><tiles:insertAttribute name="title" ignore="true" /></title>
    </head>
    <body bgcolor="gray" >
        <table border="1" cellpadding="2" cellspacing="2" width="800" align="center">
            <tr>
                
                <td width="590" height="600" valign="top">
                    <tiles:insertAttribute name="body" />
                </td>
            </tr>
     
            </tr>
        </table>
    </body>
</html>
