<%@ page language="java" contentType="text/html"%>
<%@ page import = "java.io.*,java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<c:set var="req" value="${pageContext.request}" />
<c:set var="baseURL" value="${req.scheme}://${req.serverName}:${req.serverPort}${req.contextPath}" />

<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="${baseURL}/resources/css/style.css">
        <title>Create birdspotting | ${location.getName()}</title>
    </head>
    <body>
        <h1>Create new bird spotting</h1>
        <form:form method="POST" action="${s:mvcUrl('BSC#add').arg(0, location.getName()).build()}" modelAttribute="birdSpecie">
            <div>
                <form:label path="name">Name:</form:label>
                <form:input path="name"/>
                <form:errors path="name" cssClass="error"/>
            </div>

            <div>
                <form:label path="yearOfDiscovery">Year of discovery:</form:label>
                <form:input path="yearOfDiscovery"/>
                <form:errors path="yearOfDiscovery" cssClass="error"/>
            </div>

            <div>
                <form:label path="code">Book of birds code:</form:label>
                <form:input path="code"/>
                <form:errors path="code" cssClass="error"/>
            </div>

            <input type="submit" value="Spot new bird"/>
        </form:form>
    </body>
</html>