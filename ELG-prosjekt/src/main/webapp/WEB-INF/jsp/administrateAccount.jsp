<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>



<h1>Konto Administrasjon</h1>

<%--
<script>
    function confirmComplete() {
        if (confirm('Sikker på at du ønsker å resette passordet? OK=ja')) {
            window.location.href='<c:url value="/newPassword"/>';
        } else {
            
        }
}
</script>

--%>
<form:form action="changePassword" method="post" modelAttribute="newPassword">
    <table>        
        <tr>  
            <td> Gammelt passord: </td>
            <td> <form:input path="oldPw"  />
                 <form:errors path="oldPw" />
            </td> 
        </tr>
        <tr>
            <td> Nytt Passord: </td>
            <td> <form:input path="newPw" type="password"/>
                 <form:errors path="newPw" />
            </td>
        </tr>
        <tr>
            <td> Bekreft Passord: </td>
            <td> <form:input path="confirmPw" type="password"/>
                 <form:errors path="confirmPw" />
            </td>
        </tr>

        <tr><td colspan="2"><input type="submit" value="Endre passord"</td></tr>
    </table>
</form:form>

<br> <br>


<%--
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

<h2>${regeneratedPassword}</h2>


--%>


<h2>${changedPassword}</h2>
<br>

<br>

<h2> Legg til klasse: </h2>


<form:form action="addClassId" method="post" modelAttribute="addNewClassIdAttribute">
    <table>        
        <tr>  
            <td> Add new class: </td>
            <td> <form:input path="classId"  />
                 <form:errors path="classId" />
            </td> 
        </tr>

        <tr><td colspan="2"><input type="submit" value="ChangeClassId"</td></tr>
    </table>
</form:form>


<form:form action="makeNewAdmin" method="post" modelAttribute="makeAdminAttribute">
    <table>        
        <tr>  
            <td> Type admin-password: </td>
            <td> <form:input path="makeAdminPw" type="password" /> 
                 <form:errors path="makeAdminPw" />
            </td> 
        </tr>

        <tr><td colspan="2"><input type="submit" value="changeAdminRights"</td></tr>
    </table>
</form:form> 


<h3>${NewClassMessage}</h3> 
<h3>${makeAdminMessage}</h3>
