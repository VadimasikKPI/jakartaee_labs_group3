<%@ page import="com.mycompany.mavenproject1.model.Game" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Teams page</title>
</head>
<link href="style.css" rel="stylesheet" type="text/css">
<body>
<c:choose>
    <c:when test="${not empty gameRecords}">
        <h1>Games list</h1>
        <table>
            <thead>
            <tr>
                <th>Date</th>
                <th>Team1</th>
                <th>Team2</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${gameRecords}" var="game">
                <tr>
                    <td>${game.getDate()}</td>
                    <c:if test="${not empty game.getTeams()}">
                        <c:forEach items="${game.getTeams()}" var="team">
                            <td>${team.getTeamName()}</td>
                        </c:forEach>
                    </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:when>
    <c:otherwise>
        <h1>No game data</h1>
    </c:otherwise>
</c:choose>
</body>
</html>
