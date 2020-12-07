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
        <title>Birdspotting | ${location.getName()}</title>
    </head>
    <body>

        <h1>Overview of spotted birds in ${location.getName()}</h1>

        <p>
            <a href="${s:mvcUrl('BSC#create').arg(0, location.getName()).build()}">New Spotting</a> - <a href="${s:mvcUrl('BSC#index').build()}">Spotting locations overview</a>
        </p>

        <table>
            <thead>
                <tr>
                    <th>Index</th>
                    <th>Specie</th>
                    <th>Year of discovery</th>
                    <th>Code index in the book of birds</th>
                    <th>Spottings</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="spottedBird" items="${location.getSpottedBirds()}" varStatus="spottedBirdLoop">
                    <c:set var="specie" value="${spottedBird.getBirdSpecie()}" />
                    <tr>
                        <td>${spottedBirdLoop.index+1}</td>
                        <td>${specie.getName()}</td>
                        <td>${specie.getYearOfDiscovery()}</td>
                        <td>${specie.getCode()}</td>
                        <td>${spottedBird.getCount()}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>