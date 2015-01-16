<%-- 
    Document   : completionlist
    Created on : 13.jan.2015, 15:38:47
    Author     : eiriksandberg
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Godkjenningsliste</title>
        <script src="<c:url value='/resources/Chart.js' />"></script>
        <style type='text/css'>
            ul
            {
                list-style-type: none;
            }
            
            #wrappercompletion{
                width: 100%;
            }
            
            #completionleft{
                width: 75%;
                float: left;
            }
            #completionright{
                width: 25%;
                float: right;
            }
            #listtable {
                width: 20%;
            }
            #listtableleft {
                width: 50%;
            }
            #listtableright {
                width: 50%;
                text-align: right;
            }
            #completionlistrighttable {
                width: 60%;
            }
            #completionlistrighttableleft {
                width: 50%;
            }
            #completionlistrighttableright {
                width: 50%;
                text-align: right;
            }
            #canvas-holder{
                width:30%;
            }
        </style>
    </head>
    <body>
        <div id ="wrappercompletion">
            <div id ="completionleft">
                <center><h1>Godkjenningsliste</h1>
                    <br>
                    <h2><c:out value="${nopass}"/></h2>
                    <table id="listtable">
                        <c:forEach items="${list}" var="names">
                            <tr>
                                <td id="listtableleft">
                                    <c:out value="${names.fname}"/>
                                </td>
                                <td id="listtableright">
                                    <c:out value="${names.lname}"/>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                    <br>
                    <br>
                    <div id="canvas-holder">
                        <canvas id="chart-area" width="260" height="260"/>
                    </div>
                </center>
            </div>
            <div id="completionright">
                <center>
                    <h2>Oppsummering</h2>
                    <br>
                    <table id="completionlistrighttable">
                        <tr>
                            <td id="completionlistrighttableleft">
                                Godkjent:
                            </td>
                            <td id="completionlistrighttableright">
                                <c:out value="${list.size()}"/>
                            </td>
                        </tr>
                        <tr>
                            <td id="completionlistrighttableleft">
                                Ikke godkjent:
                            </td>
                            <td id="completionlistrighttableright">
                                <c:out value="${notcompleted}"/>
                            </td>
                        </tr>
                    </table>
                    <br><br>
                    <h2>Change Graph</h2>
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
                        value: ${list.size()},
                        color:"#66CD00",
                        highlight: "#76EE00",
                        label: "Godkjent"
                    },
                    {
                        value: ${notcompleted},
                        color: "#F7464A",
                        highlight: "#FF5A5E",
                        label: "Ikke Godkjent"
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
