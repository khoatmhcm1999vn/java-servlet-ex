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
    		grid-template-columns: 40% 60%;
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
    		padding: 28px;
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

<%
	String emailError = (String)request.getAttribute("emailError");
	if (emailError == null) emailError = "";
	
	String passwordError = (String)request.getAttribute("passwordError");
	if (passwordError == null) passwordError ="";
	
	String fullnameError = (String)request.getAttribute("fullnameError");
	if (fullnameError == null) fullnameError = "";
	
	String birthdateError = (String)request.getAttribute("birthdateError");
	if (birthdateError == null) birthdateError = "";
	
	String email = (String)request.getAttribute("email");
	if (email == null) email = "";
	
	String password = (String)request.getAttribute("password");
	if (password == null) password = "";
	
	String fullname = (String)request.getAttribute("fullname");
	if (fullname == null) fullname = "";
	
	String gender = request.getParameter("gender");
	String birthdate = (String)request.getAttribute("birthdate");
%>
    <%-- <a id="btn-open-signupModal" class="btn-open-mymodal">Sign Up</a> --%>
    <div class="login-form">
        	<form action="${pageContext.request.contextPath}/signup" method="post">
                <h3>Sign Up</h3>
                <div class="form-container">
                    

                    <div class="input-text">
                        <label for="email">Email:</label>
                        <input type="text" maxlength="100" name="email" value="<%= email %>">
                        
                        <div class="input-error">
                    		<p><%= emailError %></p>
                      	</div>
                    </div>
    
                    <div class="input-password">
                        <label for="password">Password</label>
                        <input type="password" maxlength="20" name="password" value="<%= password %>">
                        
                        <div class="input-error">
                    		<p><%= passwordError %></p>
                      	</div>
                    </div>

                    <div class="input-text">
                        <label for="fullname">Your name</label>
                        <input type="text" maxlength="50" name="fullname" value="<%= fullname %>">
                    	
                    	<div class="input-error">
                    		<p><%= fullnameError %></p>
                      	</div>
                    </div>

                    <div class="input-radio">
                        <label>Gender</label>
                        <div class="radio-btn">
            
                            <input type="radio" id="male" name="gender" value=True checked>
                            <label for="male">Male</label>
                        
                            <input type="radio" id="female" name="gender" value=False 
                            	<% if (gender != null && gender.equals("False")) { %> checked <% } %> />
                            <label for="female">Female</label>
            
                        </div>
                    </div>

                    <div class="input-date">
                        <label for="birthdate">Birthdate</label>
                        <input type="date" name="birthdate" value=" <%= birthdate %>">
                        
                        <div class="input-error">
                    		<p><%= birthdateError %></p>
                      	</div>
                    </div>

                </div>
            <input type="submit" value="Sign Up" class="btn-submit-on-form">
            <input type="hidden" name="CSRFToken" value="">
        </form>
    </div>
    
    <div class="right-decor">
    	<h3>Welcome to Todo App</h3>
    	<p>Having a list of all your tasks will allow you to sit down and make a plan. One study showed that fifteen minutes spent planning could save an hour of execution time!</p>
    	<a href="${pageContext.request.contextPath}/login.jsp">Login
    	<svg xmlns="http://www.w3.org/2000/svg" 
    			enable-background="new 0 0 24 24" 
    			height="16px" viewBox="0 0 24 24" 
    			width="16px" fill="#000000">
    		<rect fill="none" height="16" width="16"/>
    		<path d="M15,5l-1.41,1.41L18.17,11H2V13h16.17l-4.59,4.59L15,19l7-7L15,5z"/>
    	</svg>
		</a>
    </div>

    <script src="${pageContext.request.contextPath}/js/todoform.js"></script>
    <script src="${pageContext.request.contextPath}/js/signupmodal.js"></script>

</body>
</html>