<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<h2>Login</h2>

<form:form action="login" method="post" modelAttribute="login" >
    <table>        
        <tr>  
            <td> email/brukernavn: </td>
            <td> <form:input path="email" value="test@gmail.com" />
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



<%--Added to test--%>

<script>
    function confirmComplete() {
        if (confirm('Sikker på at du ønsker å resette passordet? OK=ja')) {
            window.location.href='<c:url value="/newPassword"/>';
        } else {
            
        }
}
</script>

<h2>Generer et nytt passord: </h2>
<button onclick="return confirmComplete()">Gjenopprett passord</button>

<br>

<h2>${changedPassword}</h2>
<br>
<h2>${regeneratedPassword}</h2>





