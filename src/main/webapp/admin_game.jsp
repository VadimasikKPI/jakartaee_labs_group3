<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Game Page</title>
</head>
<link href="style.css" rel="stylesheet" type="text/css">
<body>
<h1>Admin Game Page</h1>
<c:choose>
    <c:when test="${not empty gameRecords}">
        <h1>Games list</h1>
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>Date</th>
                <th>Team1</th>
                <th>Team2</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${gameRecords}" var="game">
                <tr>
                    <td>${game.getId()}</td>
                    <td>${game.getDate()}</td>
                    <c:forEach items="${game.getTeams()}" var="team">
                        <td>${team.getTeamName()}</td>
                    </c:forEach>
                    <td>
                        <form action="admin_game" method="post">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="id" value="${game.getId()}">
                            <button type="submit">Delete</button>
                        </form>

                        <form action="admin_game" method="post">
                            <input type="hidden" name="action" value="update">
                            <input type="hidden" name="id" value="${game.getId()}">
                            <input type="date" name="newDate" placeholder="New Date">
                            <input type="text" name="team1" placeholder="New Team 1">
                            <input type="text" name="team2" placeholder="New Team 2">
                            <button type="submit">Update</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:when>
    <c:otherwise>
        <h1>No game data</h1>
    </c:otherwise>
</c:choose>
<form action="admin_game" method="post">
    <label for="gameDate">Enter Game Date:</label>
    <input type="date" id="gameDate" name="gameDate">
    <label for="team1">Enter Team 1 id:</label>
    <input type="text" id="team1" name="team1">
    <label for="team2">Enter Team 2 id:</label>
    <input type="text" id="team2" name="team2">
    <input type="submit" value="Add Team">
</form>
</body>
</html>
