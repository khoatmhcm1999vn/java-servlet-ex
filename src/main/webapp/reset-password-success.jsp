<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
    		grid-template-columns: 60% 40%;
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
    	}
    	.login-form {
    		display: inline-block;
    		height: fit-content;
    		min-height: 630px;
    		width: 100%;
    		//padding: 28px;
    		padding: 20% 10%;
    		background: white;
    	}
    	
    	.login-form h3 {
    		display: block;
    		text-align: center;
    		
    		font-size: 32px;
    		
    		margin-bottom: 24px;
    		//letter-spacing: 3px;
    		line-height: 1.5;
    		
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
    		background-image: url("${pageContext.request.contextPath}/assets/background1.png");
    		
    		padding: 20% 20%;
    		
    		line-height: 1.5;
    		
    		color: white;
    		
    		
    	}
    	
    	.right-decor h3 {
    		font-size: 32px;
    		padding: 12px;
    		
    	}
    	
    	.right-decor p {
    		font-size: 18px;
    		padding: 12px 12px 36px 12px;
    	}
    	
    	.right-decor a {
    		display: inline-block;
    		margin: 8px auto ;
    		width: 40%;
    		
    		padding: 12px 24px;
    		background: white;
    		color: black;
    		text-align: center;
    		
    		font-weight: 500;
    		
    	}
    	
    	.forget-password {
    		display: block;
    		text-align: center;
    		padding: 16px 0px;
    		
    		//border: black 1px solid;
    		color: black;
    		font-weight: 500;
    	}
    	
    	a {
    		color: black;
    	}
    	
    </style>
    
</head>
<body >
<% 
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); 
	response.setHeader("pragma ", "no-cache");
%>
	<div class="right-decor">
    	<h3>Welcome to Todo App</h3>
    	<p>Having a list of all your tasks will allow you to sit down and make a plan. One study showed that fifteen minutes spent planning could save an hour of execution time!</p>
    	
		<a href="${pageContext.request.contextPath}/signup.jsp">
    	<svg xmlns="http://www.w3.org/2000/svg" 
    			enable-background="new 0 0 24 24" 
    			height="16px" viewBox="0 0 24 24" 
    			width="16px" fill="#000000">
    		<rect fill="none" height="16" width="16"/>
    		<path d="M9,19l1.41-1.41L5.83,13H22V11H5.83l4.59-4.59L9,5l-7,7L9,19z"/>
    	</svg>
		Sign Up
		</a>
    	
    	<a href="${pageContext.request.contextPath}/login.jsp">
    	Login 
    	<svg xmlns="http://www.w3.org/2000/svg" 
    			enable-background="new 0 0 24 24" 
    			height="16px" viewBox="0 0 24 24" 
    			width="16px" fill="#000000">
    		<rect fill="none" height="16" width="16"/>
    		<path d="M15,5l-1.41,1.41L18.17,11H2V13h16.17l-4.59,4.59L15,19l7-7L15,5z"/>
    	</svg>
    	</a>
		
    </div>

    <div class="login-form">
	    <h3>
	    	<svg xmlns="http://www.w3.org/2000/svg" 
	    		height="128px" viewBox="0 0 24 24" 
	    		width="128px" fill="#27B681">
	    		<path d="M0 0h24v24H0V0z" fill="none"/>
	    		<path d="M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zM9.29 16.29L5.7 12.7c-.39-.39-.39-1.02 0-1.41.39-.39 1.02-.39 1.41 0L10 14.17l6.88-6.88c.39-.39 1.02-.39 1.41 0 .39.39.39 1.02 0 1.41l-7.59 7.59c-.38.39-1.02.39-1.41 0z"/>
	    	</svg>
	    </h3>
    
        <h3>Congratulations!</h3>
        <h3>You have successfully reset your password.</h3>
    </div>
   
</body>
</html>
