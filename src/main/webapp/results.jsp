<%@ page import="com.mycompany.mavenproject1.model.Game" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Results page</title>
</head>
<link href="style.css" rel="stylesheet" type="text/css">
<body>
<c:choose>
    <c:when test="${not empty resultsRecords}">
        <h1>Results list</h1>
        <table>
            <thead>
            <tr>
                <th>Game Date</th>
                <th>Score</th>
                <th>Winner name</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${resultsRecords}" var="result">
                <tr>

                    <td>${result.getGame().getDate()}</td>
                    <td>${result.getScore()}</td>
                    <td>${result.getWinnerName().getTeamName()}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:when>
    <c:otherwise>
        <h1>No results data</h1>
    </c:otherwise>
</c:choose>
</body>
</html>
