<%-- 
    Document   : nyBruker
    Created on : 07.jan.2015, 09:31:01
    Author     : Lars
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <table>
		<tr>
                    <td> Epost: </td>
			<td><form:input path="firstname" /></td>
                        <td><form:errors path="firstname" /></td>
		</tr>
		<tr>
			<td><spring:message code="lastname.message"/></td>
			<td><form:input path="lastname" /></td>
                        <td><form:errors path="lastname" /></td>
		</tr>
                <tr>
			<td><spring:message code="age.message"/></td>
			<td><form:input path="age" /></td>
                        <td><form:errors path="age" /></td>
		</tr>
                 <tr>
                    <td><input type='submit' value="SEND"></td>
                </tr>
            </table>
        </form:form>
    </body>
</html>
