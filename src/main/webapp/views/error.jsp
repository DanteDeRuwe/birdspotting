<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Document</title>
</head>
<body>
	<h1>Oops, something went wrong...</h1>
	<h2 style="font-weight: 500">That's okay, no need to cry!</h2>

	<p>The error is a <em style="color:#b71a1a">${exception.getClass().getName()}</em> and it says:</p>
	<blockquote>${exception.getMessage()}</blockquote>
</body>
</html>