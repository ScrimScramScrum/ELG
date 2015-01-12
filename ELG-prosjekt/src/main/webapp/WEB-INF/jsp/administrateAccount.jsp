<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>




<script>
function checkPasswordMatch() { 
    var password = document.getElementById("txtNewPassword").value;
    var passwordLength = password.length;
    var confirmPassword = document.getElementById("txtConfirmPassword").value;
    var confirmPasswordLength = confirmPassword.length;
    
    console.log(passwordLength);
    
    if (password===confirmPassword)
        document.getElementById("confirmBox").innerHTML = ""; 
    
    else if (passwordLength>confirmPasswordLength+2){
        document.getElementById("confirmBox").innerHTML = ""; 
        }
    else 
        document.getElementById("confirmBox").innerHTML = "De to er vel ikke helt like? er de det?";  
}
</script>

<h1>Konto Administrasjon</h1>
<br>
<h2> Endre passord: </h2> 



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
            <td> <form:input path="newPw" type="password" id="txtNewPassword"/>
                 <form:errors path="newPw" />
            </td>
        </tr>
        <tr>
            <td> Bekreft Passord: </td>
            <td> <form:input path="confirmPw" type="password" id="txtConfirmPassword" onkeyup="checkPasswordMatch()" />
                 <form:errors path="confirmPw"/> 
                  
                 
            </td>
        
            
        </tr>

        <tr><td colspan="2"><input type="submit" value="Endre passord"</td></tr>
    </table>
</form:form>

<p id="confirmBox"></p>



<h2>${changedPassword}</h2>
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

<h3>${NewClassMessage}</h3> 
<br>

<h2> Registrer deg som administrator-bruker: </h2>


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



<h3>${makeAdminMessage}</h3>
<br>

<c:choose>
    <c:when test="${user.isAdmin()}">
        <h2> Registrer en ny klasse: </h2> 
        
        <form:form action="makeClass" method="post" modelAttribute="makeNewClassAttribute" >
            <table>        
                <tr>  
                    <td> Ny klasse </td>
                    <td> <form:input path="classId" />
                         <form:errors path="classId" />
                    </td> 
                </tr>
                <tr><td colspan="2"><input type="submit" value="make Class"</td></tr>
            </table>
        </form:form>
    </c:when>
</c:choose>




