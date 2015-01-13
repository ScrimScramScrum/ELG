<%-- 
    Document   : highscorelist
    Created on : 12.jan.2015, 10:58:20
    Author     : eiriksandberg
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel = "stylesheet" type = "text/css" href = "<c:url value='/resources/css2/styleGamemenu.css'/>">
        <script src="<c:url value='/resources/Chart.js' />"></script>
        <style type="text/css">
            #mid_hs {
                float: left;
                width: 75%;
                font-family: 'Open Sans', sans-serif;
                font-size: 10pt;
                color: #686868;
            }
            #right_hs {
                text-align: center;
                float: right;
                width: 25%;
            }
            #hs_table {
                width: 100%;
            }
            #td_hs_h1 {
                height: 60px;
                text-align: center;
            }
            #td_hs_left {
                width: 20%;
            }
            #td_hs_nr {
                width: 15%;
            }
            #td_hs_1 {
                width: 15%;
            }
            #td_hs_2 {
                width: 15%;
            }
            #td_hs_3 {
                width: 15%;
                text-align: right;
            }
            #td_hs_right {
                width: 20%;
            }
            #canvas-holder{
                width:30%;
            }
        </style>
    </head>
    <body>
        <div id="whole_hs">
            <div id="mid_hs">
                <center>
                    <c:choose>
                        <c:when test="${gametype == 0}">
                            <h1>
                                Highscore - top 10
                            </h1>
                            <br>
                            This is a top-10 view of the game of your choice
                            <br>
                            You choose your game on the left
                            <br>
                            While on the right you can choose how you want the graph to be displayed
                        </c:when>
                        <c:otherwise>
                            <table id="hs_table">
                                <tr>
                                    <td colspan="6" id="td_hs_h1">
                                        <h1>Top 10 - ${gamenr}</h1>
                                    </td>
                                </tr>
                                <%
                                    int i = 0;
                                %>
                                <c:forEach items="${highscore}" var="teller">
                                    <%
                                        i++;
                                    %>
                                    <tr>
                                        <td id="td_hs_left"></td>
                                        <td id="td_hs_nr">
                                            Nr. <%=i%>
                                        </td>
                                        <td id="td_hs_1">
                                            <c:out value="${teller.fname}" />
                                        </td>
                                        <td id="td_hs_2">
                                            <c:out value="${teller.lname}" />
                                        </td>
                                        <td id="td_hs_3">
                                            <c:out value="${teller.score}" />
                                        </td>
                                         <td id="td_hs_right"></td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </c:otherwise>
                    </c:choose>
                    <br>
                    <br>
                    <div id="canvas-holder">
                        <canvas id="chart-area" width="260" height="260"/>
                    </div>

                </center>
            </div>
            <div id="right_hs">
                <h2>Change Graph</h2>
                <center>
                    <br>
                    <div class = "gamelink">
                        <a id="gameLinkA" onclick ='setChart("nr1")'>
                            Pie-chart
                        </a>
                    </div>
                    <div class = "gamelink">
                        <a id="gameLinkA" onclick ='setChart("nr2")'>
                            Polar-area-chart
                        </a>
                    </div>
                    <div class = "gamelink">
                        <a id="gameLinkA" onclick ='setChart("nr3")'>
                            Doughnut-chart
                        </a>
                    </div>
                </center>
            </div>
        </div>
        <script>
        var doughnutData = [
                {
                    value: ${sortedScores[0]},
                    color:"#F7464A",
                    highlight: "#FF5A5E",
                    label: "0-20%"
                },
                {
                    value: ${sortedScores[1]},
                    color: "#46BFBD",
                    highlight: "#5AD3D1",
                    label: "20-40%"
                },
                {
                    value: ${sortedScores[2]},
                    color: "#FDB45C",
                    highlight: "#FFC870",
                    label: "40-60%"
                },
                {
                    value: ${sortedScores[3]},
                    color: "#949FB1",
                    highlight: "#A8B3C5",
                    label: "60-80%"
                },
                {
                    value: ${sortedScores[4]},
                    color: "#4D5360",
                    highlight: "#616774",
                    label: "80-100%"
                }

            ];

            var chart;
            var ctx = document.getElementById("chart-area").getContext("2d");
            chart = new Chart(ctx).Pie(doughnutData);

            function setChart(chart_name) {
                var d = new Date();
                var exdays = 30;
                d.setTime(d.getTime() + (exdays*24*60*60*1000));
                var expires = "expires="+d.toUTCString();
                document.cookie = "chart" + "=" + chart_name + "; " + expires;
                changeChart(chart_name);
            }

            function changeChart(chart_name) {
                chart.destroy();
                var canvas = document.getElementById('chart-area');
                if (chart_name == "nr1") {
                    // pie
                    var ctx = document.getElementById("chart-area").getContext("2d");
                    ctx.clearRect ( 0 , 0 , canvas.width, canvas.height );
                    // window.myPie = new Chart(ctx).Pie(doughnutData);
                    chart = new Chart(ctx).Pie(doughnutData);
                    window.myPie = chart;
                }
                else if (chart_name == "nr2") {
                    var ctx = document.getElementById("chart-area").getContext("2d");
                    ctx.clearRect ( 0 , 0 , canvas.width, canvas.height );
                    // window.myPolarArea = new Chart(ctx).PolarArea(doughnutData, {responsive:true});
                    chart = new Chart(ctx).PolarArea(doughnutData, {responsive:true});
                    window.myPolarArea = chart;
                }
                else {
                    // doughnut
                    var ctx = document.getElementById("chart-area").getContext("2d");
                    ctx.clearRect ( 0 , 0 , canvas.width, canvas.height );
                    // window.myDoughnut = new Chart(ctx).Doughnut(doughnutData, {responsive : true});
                    chart = new Chart(ctx).Doughnut(doughnutData, {responsive : true});
                    window.myDoughnut = chart;
                }
            }

            function getCookie(c_name)
            {
                var i,x,y,ARRcookies=document.cookie.split(";");

                for (i=0;i<ARRcookies.length;i++)
                {
                    x=ARRcookies[i].substr(0,ARRcookies[i].indexOf("="));
                    y=ARRcookies[i].substr(ARRcookies[i].indexOf("=")+1);
                    x=x.replace(/^\s+|\s+$/g,"");
                    if (x==c_name)
                    {
                        return unescape(y);
                    }
                 }
            }

            window.onload = function(){
                var chart_name = getCookie("chart");
                changeChart(chart_name);
            };
    </script>
    </body>
</html>
