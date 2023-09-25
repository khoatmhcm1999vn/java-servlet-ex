<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Error</title>

	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

	<!-- Google font -->
	<link href="https://fonts.googleapis.com/css?family=Quicksand:700" rel="stylesheet">

	<!-- Custom stlylesheet -->
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style_error.css" />
    
</head>
<body >
<% 
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); 
	response.setHeader("pragma ", "no-cache");
%>
	<div id="notfound">
		<div class="notfound">
			<div class="notfound-bg">
				<div></div>
				<div></div>
				<div></div>
			</div>
			<h1>oops!</h1>
			<h2>WE ARE SORRY, BUT THE PAGE YOU REQUESTED WAS NOT FOUND</h2>
			<a href="${pageContext.request.contextPath}/error">Bach to home</a>
		</div>
	</div>
</body>
</html>