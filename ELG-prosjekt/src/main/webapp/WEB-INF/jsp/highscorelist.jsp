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
                    <table id="hs_table">
                        <tr>
                            <td colspan="6" id="td_hs_h1">
                                <h1>Top 10 - </h1>
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
                    <br>
                    <br>
                    <div id="canvas-holder">
                        <canvas id="chart-area" width="500" height="500"/>
                    </div>

                </center>
            </div>
            <div id="right_hs">
                <!-- ADS
                <br>
                FOR
                <br>
                MONEY
                <br>
                GOES
                <br>
                HERE -->
            </div>
        </div>
        <script>

        var doughnutData = [
                {
                    // value: 300,
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

            window.onload = function(){
                var ctx = document.getElementById("chart-area").getContext("2d");
                window.myDoughnut = new Chart(ctx).Doughnut(doughnutData, {responsive : true});
            };



    </script>
    </body>
</html>
