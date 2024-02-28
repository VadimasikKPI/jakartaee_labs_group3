<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Team Page</title>
</head>
<link href="style.css" rel="stylesheet" type="text/css">
<body>
<h1>Admin Team Page</h1>
    <c:choose>
    <c:when test="${not empty teamRecords}">
    <h1>Teams list</h1>
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${teamRecords}" var="team">
            <tr>
                <td>${team.getId()}</td>
                <td>${team.getTeamName()}</td>
                <td>
                    <form action="admin_team" method="post">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="id" value="${team.getId()}">
                        <button type="submit">Delete</button>
                    </form>

                    <!-- Update button -->
                    <form action="admin_team" method="post">
                        <input type="hidden" name="action" value="update">
                        <input type="hidden" name="id" value="${team.getId()}">
                        <input type="text" name="newName" placeholder="New Name">
                        <button type="submit">Update</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    </c:when>
    <c:otherwise>
    <h1>No team data</h1>
    </c:otherwise>
    </c:choose>
    <form action="admin_team" method="post">
        <label for="teamName">Enter Team Name:</label>
        <input type="text" id="teamName" name="teamName">
        <input type="submit" value="Add Team">
    </form>
</body>
</html>
