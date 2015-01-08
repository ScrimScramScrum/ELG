<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title><tiles:insertAttribute name="title" ignore="true" /></title>
    </head>
    <body bgcolor="gray" >
        <table border="0" cellpadding="2" cellspacing="2" width="800" align="center">
            <tr>
                <td colspan = "2" height="80">
                    <tiles:insertAttribute name="menu" />
                    <hr>
                </td>
            </tr>
            <tr>
               <td width="200">
                     <tiles:insertAttribute name="body" />
                </td>
                <td width="600" style="background-color: red">
                    <tiles:insertAttribute name="gamedisplay" />
                </td>
            </tr>
        </table>
    </body>
</html>