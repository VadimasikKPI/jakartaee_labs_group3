<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Result Page</title>
</head>
<link href="style.css" rel="stylesheet" type="text/css">
<body>
<h1>Admin Game Page</h1>
<c:choose>
    <c:when test="${not empty resultsRecords}">
        <h1>Results list</h1>
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>Game Date</th>
                <th>Score</th>
                <th>Winner name</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${resultsRecords}" var="result">
                <tr>
                    <td>${result.getId()}</td>
                    <td>${result.getGame().getDate()}</td>
                    <td>${result.getScore()}</td>
                    <td>${result.getWinnerName().getTeamName()}</td>
                    <td>
                        <form action="admin_results" method="post">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="id" value="${result.getId()}">
                            <button type="submit">Delete</button>
                        </form>

                        <form action="admin_results" method="post">
                            <input type="hidden" name="action" value="update">
                            <input type="hidden" name="id" value="${result.getId()}">
                            <input type="text" name="newGame" placeholder="Game id">
                            <input type="text" name="score" placeholder="Score">
                            <input type="text" name="winnerName" placeholder="Winned id">
                            <button type="submit">Update</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:when>
    <c:otherwise>
        <h1>No results data</h1>
    </c:otherwise>
</c:choose>
<form action="admin_results" method="post">
    <label for="gameId">Enter Game id:</label>
    <input type="text" id="gameId" name="gameId">
    <label for="score">Enter Score:</label>
    <input type="text" id="score" name="score">
    <label for="winnerId">Enter winner id:</label>
    <input type="text" id="winnerId" name="winnerId">
    <input type="submit" value="Add Result">
</form>
</body>
</html>
