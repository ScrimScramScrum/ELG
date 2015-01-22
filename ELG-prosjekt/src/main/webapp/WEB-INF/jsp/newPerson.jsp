<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script>
    function confirmComplete() {
        if (confirm('Sikker på at du ønsker å resette passordet? OK=ja')) {
            window.location.href='<c:url value="/sendNewPassword"/>';
        } else {
            
        }
    }
    
    function submitFormWithValue(){
        console.log("Kjører");
        document.forms["person"].submit();
    }
    
    
    function sendToLogin(){
        window.location.href='<c:url value="/firstLogin"/>';
    }
    
</script>



<div>

    <section class="login">
        <div class="titulo">Education Learning Game</div>
         <form:form action="newPersonFromLogin" method="post" modelAttribute="person" enctype="application/x-www-form-urlencoded" >
            <form:input path="email" type="text"  placeholder="Epost" data-icon="U" />
            <form:input path="fname" type="text"  placeholder="Fornavn" data-icon="U" />
            <form:input path="lname" type="text"  placeholder="Etternavn" data-icon="U" />
            
            
            <br><br>
            
            <a href="javascript:submitFormWithValue()" name="submitCommand"class="enviar">Registrer</a> 
            
            <div class="olvido">
                    <div class="col"><a href="javascript:sendToLogin()" title="Back">back</a></div>
            
                    <%--<c:choose>
                        <c:when test="${newPersonError != null}">
                                <div id ="errorMessage" class="errorMessages" >
                                    
                                </div>
                        </c:when>
                    </c:choose> --%>
                    
                    <div id ="errorMessage" class="errorMessages" >
                        <form:errors path="email" htmlEscape="false" />
                        <form:errors path="fname" htmlEscape="false" /> 
                        <form:errors path="lname" htmlEscape="false" /> 
                        ${newPersonError}
                    </div>
            </div>
            

        </form:form>
                            
    </section>
        
</div>



<style>

body {
    background-size: 1000px 1000px, 410px 410px, 610px 610px, 530px 530px, 730px 730px, 1000px 1000px;
    background-color: #58B86F;
    background: #58B86F;


    
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
    padding-top: 16px;
    padding-bottom: 16px;
    
}

.login {
    width: 300px;
    height: auto;
    overflow: hidden;
    background: #1e1e1e;
    border-radius: 6px;
    margin: 50px auto;
    box-shadow: 0px 0px 50px rgba(0,0,0,.8);
}

.login .titulo {
    width: 298px;
    height: 14px;
    padding-top: 13px;
    padding-bottom: 13px;
    font-size: 14px;
    text-align: center;
    color: #bfbfbf;
    font-weight: bold;
    background: #121212;
    border: #2d2d2d solid 1px;
    margin-bottom: 30px;
    border-top-right-radius: 6px;
    border-top-left-radius: 6px;
    font-family: Arial;
}

.login form {
    width: 240px;
    height: auto;
    overflow: hidden;
    margin-left: auto;
    margin-right: auto;
}

.login form input[type=text], .login form input[type=password] {
    width: 200px;
    font-size: 12px;
    padding-top: 14px;
    padding-bottom: 14px;
    padding-left: 40px;
    border: none;
    color: #bfbfbf;
    background: #141414;
    outline: none;
    margin: 0;
}

.login form input[type=text] {
    border-top-left-radius: 6px;
    border-top-right-radius: 6px;
    border-top: #0b0b0b solid 1px;
    background: #141414 url(http://dev.dhenriquez.com/test-img/icons/111-user.png) 10px center no-repeat;
}

.login form input[type=password] {
    border-bottom-left-radius: 6px;
    border-bottom-right-radius: 6px;
    border-top: #0b0b0b 1px solid;
    border-bottom: #353535 1px solid;
    background: #141414 url(http://dev.dhenriquez.com/test-img/icons/54-lock.png) 10px center no-repeat;
}

.login form .enviar {
    width: 240px;
    height: 12px;
    display: block;
    padding-top: 14px;
    padding-bottom: 14px;
    border-radius: 6px;
    border: none;
    border-top: #4eb2a8 1px solid;
    border-bottom: #161616 1px solid;
    background: #3290C2;
    text-align: center;
    text-decoration: none;
    font-size: 12px;
    font-weight: bold;
    color: #FFF;
    text-shadow: 0 -1px #1d7464, 0 1px #7bb8b3;
    font-family: Arial;
}

.login .olvido {
    width: 240px;
    height: auto;
    overflow: hidden;
    padding-top: auto;
    padding-bottom: auto;
    font-size: 10px;
    text-align: center;
}

.login .space {
    width: 240px;
    height: auto;
    overflow: hidden;
    padding-top: auto;
    padding-bottom: auto;
    font-size: 10px;
    text-align: center;
}

.login .olvido .col {
    width: 50%;
    height: auto;
    margin-left: auto;
    margin-right: auto;
    padding-top: 10px;
    padding-bottom: 10px;
}

.login .olvido .col a {
    color: #fff;
    text-decoration: none;
    font: 12px Arial;
}

/* http://meyerweb.com/eric/tools/css/reset/ 
   v2.0 | 20110126
   License: none (public domain)
*/

html, body, div, span, applet, object, iframe, h1, h2, h3, h4, h5, h6, p, blockquote, pre, a, abbr, acronym, address, big, cite, code, del, dfn, em, img, ins, kbd, q, s, samp, small, strike, strong, sub, sup, tt, var, b, u, i, center, dl, dt, dd, ol, ul, li, fieldset, form, label, legend, table, caption, tbody, tfoot, thead, tr, th, td, article, aside, canvas, details, embed, figure, figcaption, footer, header, hgroup, menu, nav, output, ruby, section, summary, time, mark, audio, video {
    margin: 0;
    padding: 0;
    border: 0;
    font-size: 100%;
    font: inherit;
    vertical-align: baseline;
}

/* HTML5 display-role reset for older browsers */

article, aside, details, figcaption, figure, footer, header, hgroup, menu, nav, section { display: block }

body { line-height: 1 }

ol, ul { list-style: none }

blockquote, q { quotes: none }

blockquote:before, blockquote:after, q:before, q:after {
    content: '';
    content: none;
}

table {
    border-collapse: collapse;
    border-spacing: 0;
}
    
    
</style>





