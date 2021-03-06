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
        document.getElementById("confirmBox").innerHTML = "Nytt- og bekreft passord samsvarer ikke";  
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
            
            #button{
            margin-right: 3px;
            float: left;
            width: 100%;
            height: 100%;
            position: relative;
            cursor: pointer;
            display: inline-block;
            background: #2B8FC4;
            border-radius: 4px;
            text-decoration: none;
            padding: 0px;
            font-size: 1.0em;
            font-weight:lighter;
            color: #FFF !important;
            -moz-transition: color 0.35s ease-in-out, background-color 0.35s ease-in-out;
            -webkit-transition: color 0.35s ease-in-out, background-color 0.35s ease-in-out;
            -o-transition: color 0.35s ease-in-out, background-color 0.35s ease-in-out;
            -ms-transition: color 0.35s ease-in-out, background-color 0.35s ease-in-out;
            transition: color 0.35s ease-in-out, background-color 0.35s ease-in-out;
            text-align: center;
        }
        
            .errorMessages {
            width: auto;
            height: auto;
            font-size: 14px;
            text-align: center;
            color: #ff6437;
            background: #121212;
            border-top-right-radius: 6px;
            border-top-left-radius: 6px;
            border-bottom-right-radius: 6px;
            border-bottom-left-radius: 6px;
    
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
                            <div class = "gamelink"><a href = "chooseId" id="gameLinkA" onClick ="get_form(this).submit(); return false"> <c:out value = "${'Registrer deg i en klasse'}"/> </a></div>
            </form>
            <form action="chooseAdministrateFunction" method="post">
                            <input type="hidden" name="chooseId" id="chooseId" value="3" />
                            <div class = "gamelink"><a href = "chooseId" id="gameLinkA" onClick ="get_form(this).submit(); return false"> <c:out value = "${'F� administrator-rettigheter'}"/> </a></div>
            </form>
            <c:choose>
                <c:when test="${user.isAdmin()}">
                     <form action="chooseAdministrateFunction" method="post">
                        <input type="hidden" name="chooseId" id="chooseId" value="4" />
                        <div class = "gamelink"><a href = "chooseId" id="gameLinkA" onClick ="get_form(this).submit(); return false"> <c:out value = "${'Registrer en ny klasse'}"/> </a></div>
                    </form>
                </c:when>
            </c:choose>
           
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
                                <td> <form:input path="oldPw" type="password" />
                                </td> 
                            </tr>
                            <tr>
                                <td> Nytt Passord: </td>
                                <td> <form:input path="newPw" type="password" id="txtNewPassword"/>
                                </td>
                            </tr>
                            <tr>
                                <td> Bekreft Passord: </td>
                                <td> <form:input path="confirmPw" type="password" id="txtConfirmPassword" onkeyup="checkPasswordMatch()" />
                                </td>
                            </tr>
                            <tr><td colspan="2" style="height: 70%;"><center>
                                <button class ="button" type="submit" value="Endre passord">Endre passord</button></td></tr></center>
                        </table>
                    </form:form>
                        <div id="confirmBox"></div>
                            <font color=red> ${changedPassword}</font>
                       
                </c:when>
                <c:when test="${chooseSite == 2}"> 
                    <form:form action="addClassId" method="post" modelAttribute="addNewClassIdAttribute">
                        <h2>Registrer deg i en klasse</h2><br>
                        <table>        
                            <tr>  
                                <td> Klassenavn: </td>
                                <td> <form:input path="classId"  />
                                     <form:errors path="classId" />
                                </td> 
                            </tr>

                            <tr><td colspan="2"><center><button class ="button" type="submit" value="Registrer">Registrer</button></td></tr></center>
                        </table>
                    </form:form>
                    <font color=red>${NewClassMessage}</font>
                </c:when>
                <c:when test="${chooseSite == 3}"> 
                    <form:form action="makeNewAdmin" method="post" modelAttribute="makeAdmin">
                        <h2>F� administrator-rettigheter</h2><br>
                        <table>        
                            <tr>  
                                <td> Skriv admin-passord: </td>
                                <td> <form:input path="makeAdminPw" type="password" /> 
                                     <form:errors path="makeAdminPw" />
                                </td> 
                            </tr>

                            <tr><td colspan="2"><center><button class ="button" type="submit" value="Bli administrator">Bli administrator</button></td></tr></center>
                        </table>
                    </form:form>
                    <font color=red>${makeAdminMessage} </font>
                </c:when>
                <c:when test="${chooseSite == 4}"> 
                    <form:form action="makeClass" method="post" modelAttribute="makeNewClassAttribute" >
                        <h2>Registrer en ny klasse</h2><br>
                        <table>        
                            <tr>  
                                <td> Ny klasse: </td>
                                <td> <form:input path="classId" />
                                     <form:errors path="classId" />
                                </td> 
                            </tr>
                            <tr><td colspan="2"> <center><button class="button" type="submit" value="Registrer">Registrer</button></td></tr></center>
                        </table>
                    </form:form>
                    <font color=red> ${makeClassMessage}</font>
                </c:when> 
                <c:when test="${chooseSite == 5}"> 
                    <h2 align=center><font color=red> Gjestebrukere har ikke tilgang til denne funksjonen. </font></h2>
                </c:when> 
                <c:otherwise>
                    <h1>Velg fra menyen til venstre. </h2>
                    <br> <h2>Kontaktinformasjon</h3> <br>
                    <table>        
                        <tr>  
                            <tr> <td><c:out value = "Email:"/></td><td><c:out value = " ${user.getEmail()}"/></td> </tr>
                            <tr> <td><c:out value = "Fornavn:"/></td><td><c:out value = " ${user.getFname()}"/></td> </tr>
                            <tr> <td><c:out value = "Etternavn:"/></td><td><c:out value = " ${user.getLname()}"/></td> </tr>
                            <tr> <td><c:out value = "Admin:"/></td><td><c:out value = " ${user.isAdmin()}"/></td> </tr>
                                    <tr> <td><c:out value = "Klasser:"/></td><td>
                                <c:forEach items="${list}" var="names">
                                        <c:out value = "${names},  "/>
                                </c:forEach> 
                               </td> </tr>
                                </td></tr> 
                            <tr> <td></tr> 
                        </tr>
                    </table>
                    <br><font color=green>${makeAdminMessage} ${makeClassMessage} ${NewClassMessage} ${changedPassword}</font></br>
                </c:otherwise> 
            </c:choose>
        </center>
        </div>
            <div id ="rightbar"></div>
    </body>
</html>