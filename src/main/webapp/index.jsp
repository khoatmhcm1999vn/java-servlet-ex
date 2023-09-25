<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="model.*"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Insert title here</title>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/body.css">
    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/form.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mymodal.css">
    
    <style type="text/css">
    	body {
    		padding: 12px;
    		display: grid;
    		grid-template-columns: 50% 50%;
    		
    		background-image: url("${pageContext.request.contextPath}/assets/background1.png");
    		
    		
    	}
    	.login-form {
    		display: inline-block;
    		height: fit-content;
    		min-height: 630px;
    		width: 100%;
    		//padding: 28px;
    		padding: 20% 5%;
    		background: white;
    	}
    	
    	.login-form h3 {
    		display: block;
    		text-align: center;
    		
    		font-size: 32px;
    		
    		margin-bottom: 24px;
    		//letter-spacing: 3px;
    	}
    	
    	.login-form form {
    		margin: auto;
    		width: 50%;
    	}
    	
    	input[type=submit].btn-submit-on-form {
    		margin: 8px 0px;
    		padding: 12px 0px;
    		width: 100%;
    		border-radius: 0%;
    		
    	}
    	
    	.right-decor {
    		display: inline-block;
    		//background-image: url("${pageContext.request.contextPath}/assets/background1.png");
    		
    		background-color: #B500D8;	
    		background-image: 
		        -webkit-linear-gradient(
		            45deg,
		            rgba(255, 255, 255, .2) 25%,
					transparent 25%,
		            transparent 50%, 
		            rgba(255, 255, 255, .2) 50%,
		            rgba(255, 255, 255, .2) 75%,
		            transparent 75%,
		            transparent);
    		
    		padding: 5% 10%;
    		
    		line-height: 1.5;
    		
    		color: white;
    		
    		min-height: 630px;
    		
    	}
    	
    	.right-decor h3 {
    		font-size: 32px;
    		padding: 8px;
    		
    	}
    	
    	.right-decor p {
    		font-size: 18px;
    		padding: 8px 8px 8px 8px;
    	}
    	
    	.right-decor a {
    		display: block;
    		margin: auto;
    		width: 50%;
    		
    		padding: 12px 24px;
    		background: white;
    		color: black;
    		text-align: center;
    		
    		font-weight: 500;
    		
    	}
    	
    	.link-in-index {
    		margin: auto;
    		width: 80%;
    	}
    	
    	.link-in-index a {
    		display: block;
    		margin: 12px auto;
    		width: 50%;
    		
    		padding: 16px 24px;
    		background: white;
    		color: black;
    		text-align: center;
    		
    		font-weight: 500;
    		
    	}
    </style>
    
</head>
<body >
<%
    User user = (User)session.getAttribute("user");
    if(user != null ){ %>
    <jsp:forward page="error.jsp"/>
    <% }%>
    
<% 
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); 
	response.setHeader("pragma ", "no-cache");
%>

	<div class="right-decor">
    	<h3>Welcome to Todo App</h3>
    	<p>Having a list of all your tasks will allow you to sit down and make a plan. One study showed that fifteen minutes spent planning could save an hour of execution time!</p>
    	
    	<img src="${pageContext.request.contextPath}/assets/3 SCENE.svg">
    </div>
    
    <div class="link-in-index">
    	<a href="${pageContext.request.contextPath}/login.jsp">Login</a>
		<a href="${pageContext.request.contextPath}/signup.jsp">Sign Up</a>
		<a href="${pageContext.request.contextPath}/reset-password.jsp">Reset Password</a>
    </div>
    
    
</body>
</html>