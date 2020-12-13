<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Access Denied</title>
</head>
    <body>
    <h1>Sorry, you do not have permission to view this page.</h1>
    <h2>403 Unauthorized</h2>

    Click <a href="<c:url value="/" /> ">here</a> to go back to the Homepage.
    You can also just <a href="<c:url value="/logout" /> ">log out</a>.
    </body>
</html>