<%-- 
    Document   : newPerson
    Created on : Jan 7, 2015, 9:35:31 AM
    Author     : Hoxmark
--%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<h2>Registrer ny person</h2>




<form:form action="newPerson" method="post" modelAttribute="person" >
    <table>
        <tr><td> Epost: </td>
            <td>  <form:input path="email" value="test@gmail.com"/> 
                  <form:errors path="email" />
            </td>
        </tr>
        <%--<tr><td> bekreft epost: </td>
            <td>  <form:input path="VerifyEmail" /> 
                  <form:errors path="VerifyEmail" />
            </td>
        </tr>
        --%>
        <tr>  
            <td> Fornavn: </td>
            <td> <form:input path="fname" value="testForname" />
                 <form:errors path="fname" />
            </td> 
        </tr>
        <tr>
            <td> Etternavn: </td>
            <td> <form:input path="lname" value="testEtternavn"/>
                 <form:errors path="lname" />
            </td>
        </tr>

        <tr><td colspan="2"><input type="submit" value="Registrer person"</td></tr>
    </table>
</form:form>


<h3> ${melding} </h3>




