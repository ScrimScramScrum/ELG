
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@page import="springmvc.domain.User"%>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><tiles:insertAttribute name="title" ignore="true" /></title>
        <link href="<c:url value='/resources/assets/chatstyle.css'/>" rel="stylesheet" type="text/css" />
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    </head>
    <body bgcolor="gray" >
        <table border="0" cellpadding="0" cellspacing="0" width="100%" align="center">
            <tr>
                
                <td width="100%" height="100" valign="top">
                    <tiles:insertAttribute name="menu" />
                    <!--<hr>-->
                </td>
            </tr>
            <tr>
               <td width="100%" valign="top">
                    <br>
                    <tiles:insertAttribute name="body" />
                </td>
            </tr>
        </table>

        <%
            User user = (User)session.getAttribute("user");
            if(user.isInLogged() && !user.getEmail().equals("GUEST")) {
        %>

        <!-- CHAT APP -->
        <div ng-app="chatApp" id="chat_app" ng-controller="ChatCtrl">
            <div id="chat_window" ng-show="!user.hidden">
                <!-- VIEW PEOPLE WINDOW -->
                <div id="people">
                    <div id="choose_chat_top">
                        Velg Chat
                    </div>
                    <div id="choose_chat_mid">
                        <div ng-repeat="(person, value) in user.subs | orderObjectBy:'isOnline':'unread':true | filter:q" class="animate-repeat" >
                            <div ng-click="user.change_chat(value.name)" id="sub" ng-class="{unread: value.unread, offline: !value.isOnline}">
                                <div id="person_name">
                                    {{emailfilter(value.name)}}
                                </div>
                                <div ng-if="value.isOnline == true " id="presence">
                                    Online -
                                    Mute<input type="checkbox" ng-model="value.muted" ng-click="$event.stopPropagation();">
                                </div>
                                <div ng-if="value.isOnline == false " id="presence">
                                    Offline -
                                    Mute<input type="checkbox" ng-model="value.muted" ng-click="$event.stopPropagation();">
                                </div>

                            </div>
                        </div>
                    </div>
                    <div id="chat_inputs">
                        <div id="searchbox_box">
                                Søk: 
                                <input type="search" ng-model="q" placeholder="Filtrer..." id="searchbox" />
                        </div>
                    </div>
                </div>
                <!-- CHAT_WINDOW -->
                <div id="main_chat">
                    <div id="main_chat_top">
                        {{user.receiver}}
                        <div id="hide" ng-click="user.hide()">
                            -
                        </div>
                        <!-- <button id="hide">Hide</button> -->
                    </div>
                    <div id="main_chat_mid" scroll-glue>
                        <!-- <p ng-repeat="message in user.messages2 | orderBy:'time':true" class="message"> -->
                        <p ng-repeat="message in user.messages2" class="message">
                            <time>{{message.time | date:'HH:mm'}}</time>
                            <span id="sender" ng-class="{self: message.self}">
                                <span ng-if="message.self == true">Meg : </span> 
                                {{message.message}}
                            </span>
                        </p>
                    </div>
                    <div id="chat_inputs">

                        <div id="message_box">
                            <form ng-submit="user.addMessage()" name="messageForm">
                                <input type="text" placeholder="Ny melding..." ng-model="user.message" id="message" ng-disabled="user.subs[user.receiver].muted || !user.subs[user.receiver].isOnline"/>
                            </form>
                        </div>
                    </div>
                </div>
                <!-- Scripts -->
                <!-- // <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.0-beta.1/angular-animate.js"></script> -->
                <script src="<c:url value='/resources/assets/sockjs.min.js'/>" type="text/javascript"></script>
                <script src="<c:url value='/resources/assets/stomp.min.js'/>" type="text/javascript"></script>
                <script src="<c:url value='/resources/assets/angular.min.js'/>" type="text/javascript"></script>
                <script src="https://cdnjs.cloudflare.com/ajax/libs/lodash.js/2.4.1/lodash.min.js"></script>
                <script src="https://cdn.rawgit.com/Luegg/angularjs-scroll-glue/v2.0.2/src/scrollglue.js"></script>
                <script src="<c:url value='/resources/app/app.js'/>" type="text/javascript"></script>
                <script src="<c:url value='/resources/app/controllers.js'/>" type="text/javascript"></script>
                <script src="<c:url value='/resources/app/services.js'/>" type="text/javascript"></script>
            </div>
            <div id="show" ng-show="user.hidden" ng-click="user.hide()" ng-class="{unread: user.unread}">
                Vis Chat
                <!-- <button id="show">Show</button> -->
            </div>
        </div>
        <%
            }
        %>
    </body>
</html>
