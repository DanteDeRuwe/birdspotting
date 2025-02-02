<%@ page language="java" contentType="text/html"%>
<%@ page import = "java.io.*,java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<c:set var="req" value="${pageContext.request}" />
<c:set var="baseURL" value="${req.scheme}://${req.serverName}:${req.serverPort}${req.contextPath}" />

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="${baseURL}/resources/css/style.css">
    <title>Birdspotting</title>
</head>
<body>
    <h1>Overview of bird spotting locations:</h1>

    <p>Please select your location to add a spotting:</p>

    <table>
        <thead>
            <tr>
                <th>Location</th>
                <th>Birds Spotted</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="location" items="${locationData.entrySet()}">
                <tr>
                    <td>
                        <a href="${s:mvcUrl('BSC#location').arg(0, location.getKey()).build()}">${location.getKey()}</a>
                    </td>
                    <td>${location.getValue()}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <br>
    <form action="/logout">
        <input type="submit" value="Stop Spotting"/>
    </form>
</body>
</html>