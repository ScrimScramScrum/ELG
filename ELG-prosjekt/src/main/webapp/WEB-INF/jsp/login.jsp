<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<h2>Login</h2>

<form:form action="login" method="post" modelAttribute="login" >
    <table>        
        <tr>  
            <td> email/brukernavn: </td>
            <td> <form:input path="email" />
                 <form:errors path="email" />
            </td> 
        </tr>
        <tr>
            <td> Passord: </td>
            <td> <form:input path="password" type="password"/>
                 <form:errors path="password" />
            </td>
        </tr>

        <tr><td colspan="2"><input type="submit" value="LoginForm"</td></tr>
    </table>
</form:form>

<h2> ${wrongPassword} </h2>








