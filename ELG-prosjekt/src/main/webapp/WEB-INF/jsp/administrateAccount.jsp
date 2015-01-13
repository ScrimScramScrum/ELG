<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

<html>
    <head>
        <link rel = "stylesheet" type = "text/css" href = "<c:url value='/resources/css2/styleGamemenu.css'/>">
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style type="text/css">
            #chooseButton {
                width: 150px;
                height: 60px;
            }
            #sidebar {
                width: 20%;
                float: left; 
                   
            }
            #mainbar {
                width: 60%;
                float: left; 
                   
            }
            #rightbar {
                width: 20%;
                float: right; 
                   
            }
        </style>
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
            <h2>Velg funksjon</h2>
            <form action="chooseAdministrateFunction" method="post">
                            <input type="hidden" name="chooseId" id="chooseId" value="1" />
                            <div class = "gamelink"><a href = "chooseId" id="gameLinkA" onClick ="get_form(this).submit(); return false"> <c:out value = "${'Endre passord'}"/> </a></div>
            </form>
            <form action="chooseAdministrateFunction" method="post">
                            <input type="hidden" name="chooseId" id="chooseId" value="2" />
                            <div class = "gamelink"><a href = "chooseId" id="gameLinkA" onClick ="get_form(this).submit(); return false"> <c:out value = "${'Registrer brukeren i en klasse'}"/> </a></div>
            </form>
            <form action="chooseAdministrateFunction" method="post">
                            <input type="hidden" name="chooseId" id="chooseId" value="3" />
                            <div class = "gamelink"><a href = "chooseId" id="gameLinkA" onClick ="get_form(this).submit(); return false"> <c:out value = "${'Få administratorrettigheter'}"/> </a></div>
            </form>
            <form action="chooseAdministrateFunction" method="post">
                            <input type="hidden" name="chooseId" id="chooseId" value="4" />
                            <div class = "gamelink"><a href = "chooseId" id="gameLinkA" onClick ="get_form(this).submit(); return false"> <c:out value = "${'Registrer ny klasse'}"/> </a></div>
            </form>
            </center>
        </div>     
        <div id="mainbar">
            <center>
            <c:choose>
                <c:when test="${chooseSite == 1}"> 
                    <form:form action="changePassword" method="post" modelAttribute="newPassword">
                        <h2>Endre passord</h2><br>
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
                            <tr><td colspan="2"><center><input type="submit" value="Endre passord"</td></tr></center>
                        </table>
                    </form:form>
                </c:when>
                <c:when test="${chooseSite == 2}"> 
                    <form:form action="addClassId" method="post" modelAttribute="addNewClassIdAttribute">
                        <h2>Legg til ny klasse</h2><br>
                        <table>        
                            <tr>  
                                <td> Add new class: </td>
                                <td> <form:input path="classId"  />
                                     <form:errors path="classId" />
                                </td> 
                            </tr>

                            <tr><td colspan="2"><center><input type="submit" value="Legg til ny klasse"</td></tr></center>
                        </table>
                    </form:form>
                </c:when>
                <c:when test="${chooseSite == 3}"> 
                    <form:form action="makeNewAdmin" method="post" modelAttribute="makeAdminAttribute">
                        <h2>Få administratorrettigheter</h2><br>
                        <table>        
                            <tr>  
                                <td> Type admin-password: </td>
                                <td> <form:input path="makeAdminPw" type="password" /> 
                                     <form:errors path="makeAdminPw" />
                                </td> 
                            </tr>

                            <tr><td colspan="2"><center><input type="submit" value="Bli administrator"</td></tr></center>
                        </table>
                    </form:form> 
                </c:when>
                <c:when test="${chooseSite == 4}"> 
                    <form:form action="makeClass" method="post" modelAttribute="makeNewClassAttribute" >
                        <h2>Registrer ny klasse</h2><br>
                        <table>        
                            <tr>  
                                <td> Ny klasse: </td>
                                <td> <form:input path="classId" />
                                     <form:errors path="classId" />
                                </td> 
                            </tr>
                           <tr><td colspan="2"> <center><input type="submit" value="Lag ny klasse"</td></tr></center>
                        </table>
                    </form:form>
                </c:when>
                <c:otherwise>
                    <form:form action="changePassword" method="post" modelAttribute="newPassword">
                        <h2>Endre passord</h2><br>
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
                            <tr><td colspan="2"><center><input type="submit" value="Endre passord"</td></tr></center>
                        </table>
                    </form:form>
                </c:otherwise>
            </c:choose>
            </center>
        </div>
            <div id ="rightbar">
            </div>
    </div>
    </body>
</html>

<%--
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
</c:choose> --%>




