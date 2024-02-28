<%@ page import="com.mycompany.mavenproject1.model.Team" %>
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
    <c:when test="${not empty teamRecords}">
        <h1>Teams list</h1>
        <table>
            <tbody>
            <c:forEach items="${teamRecords}" var="team">
                <tr>
                    <th scope="row">Team Name</th>
                    <td>${team.getTeamName()}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:when>
    <c:otherwise>
        <h1>No team data</h1>
    </c:otherwise>
</c:choose>
</body>
</html>
