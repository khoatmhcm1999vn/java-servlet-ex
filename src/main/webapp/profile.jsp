<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="model.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profile</title>
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/calendar.js"></script>
    <script src="${pageContext.request.contextPath}/js/color.js"></script>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/body.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/profilemain.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/navbar.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/form.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/deadline.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/week.css">
</head>
<body onload="initCalendar()">
<% 
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); 
	response.setHeader("pragma ", "no-cache");
%>

<%
    User user = (User)session.getAttribute("user");
    if(user == null ){ %>
    <jsp:forward page="index.jsp"/>
    <% }%>
   
    <div class="main-container">

        <div class="navbar-container">
            <div class="navbar">
                <div class="nav-logo">
                    <a href="">
                        <span>
                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-check2-all" viewBox="0 0 16 16">
                                <path d="M12.354 4.354a.5.5 0 0 0-.708-.708L5 10.293 1.854 7.146a.5.5 0 1 0-.708.708l3.5 3.5a.5.5 0 0 0 .708 0l7-7zm-4.208 7l-.896-.897.707-.707.543.543 6.646-6.647a.5.5 0 0 1 .708.708l-7 7a.5.5 0 0 1-.708 0z"/>
                                <path d="M5.354 7.146l.896.897-.707.707-.897-.896a.5.5 0 1 1 .708-.708z"/>
                            </svg>
                        </span>
                        yourtodo
                    </a>
                </div>
                
                <div class="nav-links">
                    <a href="${pageContext.request.contextPath}/listDashboard" class="link-on-navbar">
                        <span>
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-ui-checks-grid" viewBox="0 0 16 16">
                                <path d="M2 10h3a1 1 0 0 1 1 1v3a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1v-3a1 1 0 0 1 1-1zm9-9h3a1 1 0 0 1 1 1v3a1 1 0 0 1-1 1h-3a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1zm0 9a1 1 0 0 0-1 1v3a1 1 0 0 0 1 1h3a1 1 0 0 0 1-1v-3a1 1 0 0 0-1-1h-3zm0-10a2 2 0 0 0-2 2v3a2 2 0 0 0 2 2h3a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2h-3zM2 9a2 2 0 0 0-2 2v3a2 2 0 0 0 2 2h3a2 2 0 0 0 2-2v-3a2 2 0 0 0-2-2H2zm7 2a2 2 0 0 1 2-2h3a2 2 0 0 1 2 2v3a2 2 0 0 1-2 2h-3a2 2 0 0 1-2-2v-3zM0 2a2 2 0 0 1 2-2h3a2 2 0 0 1 2 2v3a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V2zm5.354.854a.5.5 0 1 0-.708-.708L3 3.793l-.646-.647a.5.5 0 1 0-.708.708l1 1a.5.5 0 0 0 .708 0l2-2z"/>
                            </svg>
                        </span>
                        dashboard
                    </a>
                    <a href="${pageContext.request.contextPath}/listTodo" class="link-on-navbar">
                        <span>
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-check2" viewBox="0 0 16 16">
                                <path d="M13.854 3.646a.5.5 0 0 1 0 .708l-7 7a.5.5 0 0 1-.708 0l-3.5-3.5a.5.5 0 1 1 .708-.708L6.5 10.293l6.646-6.647a.5.5 0 0 1 .708 0z"/>
                            </svg>
                        </span>
                        todos
                    </a>
                    <a href="${pageContext.request.contextPath}/statistic" class="link-on-navbar">
                        <span>
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-bar-chart" viewBox="0 0 16 16">
                                <path d="M4 11H2v3h2v-3zm5-4H7v7h2V7zm5-5v12h-2V2h2zm-2-1a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h2a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1h-2zM6 7a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1v7a1 1 0 0 1-1 1H7a1 1 0 0 1-1-1V7zm-5 4a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1v3a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1v-3z"/>
                            </svg>
                        </span>
                        statistics
                    </a>
                    <a href="${pageContext.request.contextPath}/profile.jsp" class="link-on-navbar" id="active">
                        <span>
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person" viewBox="0 0 16 16">
                                <path d="M8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0zm4 8c0 1-1 1-1 1H3s-1 0-1-1 1-4 6-4 6 3 6 4zm-1-.004c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10z"/>
                            </svg>
                        </span>
                        profile
                    </a>
                </div>
    
                <div class="nav-buttons">
                    <a href="${pageContext.request.contextPath}/logout" class="btn-on-navbar">Logout</a>
                </div>
        
                <div class="nav-menu-dropdown">
                    <a href="#" onclick="$('.dropdown').toggle(300);">
                        <span>
                            <svg viewBox="0 0 100 80" width="24" height="24">
                                <rect width="100" height="20" rx="8"></rect>
                                <rect y="30" width="100" height="20" rx="8"></rect>
                                <rect y="60" width="100" height="20" rx="8"></rect>
                            </svg>
                        </span>
                    </a>
                    
                    <div class="dropdown" style="display: none;">
                        <a href="${pageContext.request.contextPath}/listDashboard" class="link-on-dropdown" >
                            <span>
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-ui-checks-grid" viewBox="0 0 16 16">
                                    <path d="M2 10h3a1 1 0 0 1 1 1v3a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1v-3a1 1 0 0 1 1-1zm9-9h3a1 1 0 0 1 1 1v3a1 1 0 0 1-1 1h-3a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1zm0 9a1 1 0 0 0-1 1v3a1 1 0 0 0 1 1h3a1 1 0 0 0 1-1v-3a1 1 0 0 0-1-1h-3zm0-10a2 2 0 0 0-2 2v3a2 2 0 0 0 2 2h3a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2h-3zM2 9a2 2 0 0 0-2 2v3a2 2 0 0 0 2 2h3a2 2 0 0 0 2-2v-3a2 2 0 0 0-2-2H2zm7 2a2 2 0 0 1 2-2h3a2 2 0 0 1 2 2v3a2 2 0 0 1-2 2h-3a2 2 0 0 1-2-2v-3zM0 2a2 2 0 0 1 2-2h3a2 2 0 0 1 2 2v3a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V2zm5.354.854a.5.5 0 1 0-.708-.708L3 3.793l-.646-.647a.5.5 0 1 0-.708.708l1 1a.5.5 0 0 0 .708 0l2-2z"/>
                                </svg>
                            </span>
                            dashboard
                        </a>
                        <a href="${pageContext.request.contextPath}/listTodo" class="link-on-dropdown">
                            <span>
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-check2" viewBox="0 0 16 16">
                                    <path d="M13.854 3.646a.5.5 0 0 1 0 .708l-7 7a.5.5 0 0 1-.708 0l-3.5-3.5a.5.5 0 1 1 .708-.708L6.5 10.293l6.646-6.647a.5.5 0 0 1 .708 0z"/>
                                </svg>
                            </span>
                            todos
                        </a>
                        <a href="${pageContext.request.contextPath}/statistic" class="link-on-dropdown">
                            <span>
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-bar-chart" viewBox="0 0 16 16">
                                    <path d="M4 11H2v3h2v-3zm5-4H7v7h2V7zm5-5v12h-2V2h2zm-2-1a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h2a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1h-2zM6 7a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1v7a1 1 0 0 1-1 1H7a1 1 0 0 1-1-1V7zm-5 4a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1v3a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1v-3z"/>
                                </svg>
                            </span>
                            statistics
                        </a>
                        <a href="${pageContext.request.contextPath}/profile.jsp" class="link-on-dropdown">
                            <span>
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person" viewBox="0 0 16 16">
                                    <path d="M8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0zm4 8c0 1-1 1-1 1H3s-1 0-1-1 1-4 6-4 6 3 6 4zm-1-.004c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10z"/>
                                </svg>
                            </span>
                            profile
                        </a>
                        <a href="#" class="link-on-dropdown">
                            <span>
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-box-arrow-left" viewBox="0 0 16 16">
                                    <path fill-rule="evenodd" d="M6 12.5a.5.5 0 0 0 .5.5h8a.5.5 0 0 0 .5-.5v-9a.5.5 0 0 0-.5-.5h-8a.5.5 0 0 0-.5.5v2a.5.5 0 0 1-1 0v-2A1.5 1.5 0 0 1 6.5 2h8A1.5 1.5 0 0 1 16 3.5v9a1.5 1.5 0 0 1-1.5 1.5h-8A1.5 1.5 0 0 1 5 12.5v-2a.5.5 0 0 1 1 0v2z"/>
                                    <path fill-rule="evenodd" d="M.146 8.354a.5.5 0 0 1 0-.708l3-3a.5.5 0 1 1 .708.708L1.707 7.5H10.5a.5.5 0 0 1 0 1H1.707l2.147 2.146a.5.5 0 0 1-.708.708l-3-3z"/>
                                </svg>
                            </span>
                            logout
                        </a>
                    </div>
                </div>
        
            </div>
        </div>

        <div class="profile-content">
            <div class="profile-form">
                <h2 class="title-form">Your profile</h2>
                <form action="${pageContext.request.contextPath}/updateUser" method="post">
                    <div class="form-container">
                        <div class="input-text">
                            <label for="fullname">Your name</label>
                            <input type="text" name="fullname" value="<c:out value='${sessionScope.user.fullname}'/>" required>
                        </div>
                        
                        <div class="input-radio">
                        <label>Gender</label>
                        <div class="radio-btn">
            
                            <input type="radio" id="male" name="gender" value=True checked>
                            <label for="male">Male</label>
                        
                            <input type="radio" id="female" name="gender" value=False 
                            	<c:if test="${sessionScope.user.gender == false}">checked</c:if> />
                            <label for="female">Female</label>
            
                        </div>
                    </div>
    
                        <div class="input-date">
                            <label for="birthdate">Birthdate</label>
                            <input type="date" name="birthdate" value="<c:out value='${sessionScope.user.birthdate}'/>" >
                        </div>

                        <input type="submit" value="Upload profile" class="btn-submit-on-form">
                    </div>
                            	<input type="hidden" name="CSRFToken" value="">
                    
                </form>            
            </div>
        </div>

        <div class="right-content">
            <div class="week-calendar">
                <div class="datetime-container">
                        <span id="month">October</span>
                        <span id="year">2020</span>
                </div>

                <div class="week-container">
                    <div class="day">
                        <span>SUN</span>
                        <span id="sun">22</span>
                        
                    </div>
                    <div class="day">
                        <span>MON</span>
                        <span id="mon">20</span>
                        
                    </div>
                    <div class="day">
                        <span>TUE</span>
                        <span id="tue">21</span>
                        
                    </div>
                    <div class="day">
                        <span>WED</span>
                        <span id="wed">22</span>
                        
                    </div>
                    <div class="day">
                        <span>THU</span>
                        <span id="thu">22</span>
                        
                    </div>
                    <div class="day">
                        <span>FRI</span>
                        <span id="fri">22</span>
                        
                    </div>
                    <div class="day">
                        <span>SAT</span>
                        <span id="sat">22</span>
                        
                    </div>
                </div>
            </div>

            <h2 class="title-deadlines">Deadline Pending</h2>

            <div class="deadlines-container">
                <div class="deadlines-list">
                <c:forEach var="todo" items="${sessionScope.listTodoTotal}">
                 <c:if test="${todo.deadline != null && todo.done == false}">
                    <div class="deadline">
                        <div class="deadline-header">
                            <p><span class="time"><c:out value="${todo.deadline}"></c:out></span> @ <c:out value="${todo.date}"></c:out></p>
                            <div class="buttons">
                                <a href="#" class="btn-on-deadline">
                                    <span>
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-square" viewBox="0 0 16 16">
                                            <path d="M14 1a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1h12zM2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2z"/>
                                        </svg>
                                    </span>
                                    <!-- <span>
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-check-square-fill" viewBox="0 0 16 16">
                                            <path d="M2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2zm10.03 4.97a.75.75 0 0 1 .011 1.05l-3.992 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.75.75 0 0 1 1.08-.022z"/>
                                        </svg>
                                    </span> -->
                                </a>
                
                                <a href="#" class="btn-on-deadline">
                                    <span>
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-play-circle" viewBox="0 0 16 16">
                                            <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
                                            <path d="M6.271 5.055a.5.5 0 0 1 .52.038l3.5 2.5a.5.5 0 0 1 0 .814l-3.5 2.5A.5.5 0 0 1 6 10.5v-5a.5.5 0 0 1 .271-.445z"/>
                                        </svg>
                                    </span>
                                    
                                </a>
                    
                                <a href="#" class="btn-on-deadline">
                                    <span>
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil" viewBox="0 0 16 16">
                                            <path d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5L13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175l-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z"/>
                                        </svg>
                                    </span>
                                    
                                </a>
                    
                                <a href="#" class="btn-on-deadline">
                                    <span>
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
                                            <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>
                                            <path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4L4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"/>
                                        </svg>
                                    </span>
                                    
                                </a>
                            </div>
                        </div>
            
                        <div class="deadline-content">
                            <!-- <p class="tag-on-deadline">work</p> -->
                            
                            
                            
                            <p><span class="tag-on-deadline span-tag-<c:out value="${todo.tag.id}"></c:out>" style="background-color: <c:out value="${tag.color}"></c:out>;"><c:out value="${todo.tag.tagname}" ></c:out></span>
                            	
                            	<c:out value="${todo.title}"></c:out>
                            </p>
                        </div>
                        <script>
                                $(".span-tag-<c:out value="${todo.tag.id}"></c:out>").attr("style", "color: " + ColorText('<c:out value="${todo.tag.color}"></c:out>'));
                                $(".span-tag-<c:out value="${todo.tag.id}"></c:out>").attr("style", "background-color: " + '<c:out value="${todo.tag.color}"></c:out>' );
                        </script>
            
                    </div>
    			</c:if>
    			</c:forEach>
                </div>
            </div>
            
        </div>
    </div>
</body>
</html>