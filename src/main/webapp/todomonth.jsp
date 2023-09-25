<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="model.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Todo</title>
        <script src="${pageContext.request.contextPath}/js/calendar.js"></script>
    <script src="${pageContext.request.contextPath}/js/color.js"></script>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

	<script src="${pageContext.request.contextPath}/js/startTodoModal.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/body.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tododatemain.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/navbar.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tag.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/form.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mymodal.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/deadline.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/task.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/week.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/watch.css">
    
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
	<% 	
		String title = request.getParameter("title");
		String priority = request.getParameter("priority");
		String tagid = request.getParameter("tagid");
		String date = request.getParameter("date");
		String stateFilter = request.getParameter("stateFilter");
		String prioFilter = request.getParameter("prioFilter");
		String tagidFilter=request.getParameter("tagidFilter");
		if(tagidFilter==null)
		{
			tagidFilter="-1";
		}
		String month = request.getParameter("month");
		String sort=request.getParameter("sort");
		if(sort==null)
			sort="";
		System.out.println("trang month"+ sort);
	%>
	
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
                    <a href="${pageContext.request.contextPath}/listDashboard" class="link-on-navbar" >
                        <span>
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-ui-checks-grid" viewBox="0 0 16 16">
                                <path d="M2 10h3a1 1 0 0 1 1 1v3a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1v-3a1 1 0 0 1 1-1zm9-9h3a1 1 0 0 1 1 1v3a1 1 0 0 1-1 1h-3a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1zm0 9a1 1 0 0 0-1 1v3a1 1 0 0 0 1 1h3a1 1 0 0 0 1-1v-3a1 1 0 0 0-1-1h-3zm0-10a2 2 0 0 0-2 2v3a2 2 0 0 0 2 2h3a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2h-3zM2 9a2 2 0 0 0-2 2v3a2 2 0 0 0 2 2h3a2 2 0 0 0 2-2v-3a2 2 0 0 0-2-2H2zm7 2a2 2 0 0 1 2-2h3a2 2 0 0 1 2 2v3a2 2 0 0 1-2 2h-3a2 2 0 0 1-2-2v-3zM0 2a2 2 0 0 1 2-2h3a2 2 0 0 1 2 2v3a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V2zm5.354.854a.5.5 0 1 0-.708-.708L3 3.793l-.646-.647a.5.5 0 1 0-.708.708l1 1a.5.5 0 0 0 .708 0l2-2z"/>
                            </svg>
                        </span>
                        dashboard
                    </a>
                    <a href="${pageContext.request.contextPath}/listTodo" class="link-on-navbar" id="active">
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
                    <a href="${pageContext.request.contextPath}/profile.jsp" class="link-on-navbar">
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

        <div class="buttons-container">
            <div class="buttons-on-left">
                <a href="${pageContext.request.contextPath}/listTodo" class="btn-view-todos" >
                    Day
                </a>
    
                <a href="${pageContext.request.contextPath}/listTodoThisWeek" class="btn-view-todos">
                    Week
                </a>

                <a href="${pageContext.request.contextPath}/listTodoThisMonth" class="btn-view-todos"  id="on-view">
                    Month
                </a>
                
            </div>

            <div class="buttons-on-right">
                <a id="btn-open-addTodoModal" class="btn-open-mymodal">
                
                    <svg width="16" height="16" viewBox='0 0 16 16' >
                        <line x1=1 y1=8 x2=14 y2=8 shape-rendering="crispEdges" stroke-width="1" stroke="#ffffff"/>
                        <line x1=8 y1=1 x2=8 y2=14 shape-rendering="crispEdges" stroke-width="1" stroke="#ffffff"/>
                    </svg>
                    Add todo
                </a>
                
                <c:if test="${openFormAddTodo != null}">
						<div class="mymodal" id="addTodoModal" style="display: flex;">
				</c:if>
				
				<c:if test="${openFormAddTodo == null}">
						<div class="mymodal" id="addTodoModal">
				</c:if>
				
                <!-- <div class="mymodal" id="addTodoModal"> -->
                    
                        <div class="mymodal-content">
                        <form action="${pageContext.request.contextPath}/insertTodo" method="post" >
                            <div class="mymodal-header">
                                <h3>Create new todo</h3>
                                <a class="btn-close-mymodal" id="btn-close-addTodoModal">
                                    <span>
                                        <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor" class="bi bi-x" viewBox="0 0 16 16">
                                            <path d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"/>
                                        </svg>
                                    </span>
                                </a>
                            </div>
                            <div class="mymodal-body">
                                <div class="form-container">
                                
                                	<input type="hidden" name="from" value = "todomonth" >
                                	
                                    <div class="input-text">
                                        <label for="title">Title</label>
                                        <input type="text" maxlength="100" name="title" value = "<c:out value='${title}'/>" >
                                        
                                        <div class="input-error">
                                            <p><c:out value="${titleError}"/></p>
                                        </div>
                                    </div>
                                    
                    
                                    <div class="input-date">
                                        <label for="date">Date</label>
                                        <input type="date" name="date" value = "${date}">

                                        <!-- <div class="input-error">
                                            <p>Invalid input</p>
                                        </div> -->
                                    </div>
                                    
                                    <div class="input-select">
	                                    <label for="priority">Priority</label>
	                                    <select name="priority">
	                                        <option value="High" <c:if test="${priority == 'High'}">selected</c:if> >High</option>
	                                        <option value="Medium" <c:if test="${priority == 'Medium'}">selected</c:if> >Medium</option>
	                                        <option value="Low" <c:if test="${priority == 'Low'}">selected</c:if> >Low</option>
	                                    </select>
	                                </div>
                    
                                    <div class="input-select">
                                        <label for="tagid">Tag</label>
                                        <select name="tagid">
                                        <c:forEach var="tag" items="${sessionScope.listTag}">
                                            <option value="${tag.id}" 
	                                            <c:if test="${tag.id == tagid}">selected</c:if> >
	                                            <c:out value="${tag.tagname}"></c:out>
                                            </option>
                                        </c:forEach>
                                        </select>

                                        <!-- <div class="input-error">
                                            <p>Invalid input</p>
                                        </div> -->
                                    </div>
                    
                                    <div class="input-time">
                                        <label for="deadline">Deadline</label>
                                        <input type="time" name="deadline">

                                        <!-- <div class="input-error">
                                            <p>Invalid input</p>
                                        </div> -->
                                    </div>
                                    
                                    <div class="input-time">
                                        <label for="remindat">Remind at</label>
                                        <input type="time" name="remindat">

                                        <!-- <div class="input-error">
                                            <p>Invalid input</p>
                                        </div> -->
                                    </div>
                                </div>
                            </div>
                            <div class="mymodal-footer">
                                <!-- <a href="#" class="btn-on-mymodal">
                                    Create
                                </a> -->
                                <input type="submit" value="Create" class="btn-submit-on-form">
                            </div>
        	<input type="hidden" name="CSRFToken" value="">

                        </form>
                    </div>
                    
                </div>
    
                <a id="btn-open-addTagModal" class="btn-open-mymodal" >
                    <svg width="16" height="16" viewBox='0 0 16 16' >
                        <line x1=1 y1=8 x2=14 y2=8 shape-rendering="crispEdges" stroke-width="1" stroke="#ffffff"/>
                        <line x1=8 y1=1 x2=8 y2=14 shape-rendering="crispEdges" stroke-width="1" stroke="#ffffff"/>
                    </svg>
                    Add tag
                </a>
    
			    <c:if test="${openFormAddTag != null}">
						<div class="mymodal" id="addTagModal" style="display: flex;">
						
				</c:if>
				
				<c:if test="${openFormAddTag == null}">
						<div class="mymodal" id="addTagModal">
				</c:if>
				
                <!-- <div class="mymodal" id="addTagModal"> -->
                    <div class="mymodal-content">
                
                    <form action="${pageContext.request.contextPath}/insertTag" method="post" >
                        <div class="mymodal-header">
                            <h3>Create new tag</h3>
                            <a class="btn-close-mymodal" id="btn-close-addTagModal">
                                <span>
                                    <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor" class="bi bi-x" viewBox="0 0 16 16">
                                        <path d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"/>
                                    </svg>
                                </span>
                            </a>
                        </div>
                        <div class="mymodal-body">
                            <div class="form-container">
                            
                            	<input type="hidden" name="from" value = "todomonth" >
                            	
                                <div class="input-text">
                                    <label for="tagname">Tag:</label>
                                    <input type="text" maxlength="50" name="tagname" value = "<c:out value='${tagname}'/>" required>
                                </div>
                
                                <div class="input-color">
                                    <label for="color">Color</label>
                                    <input type="color" maxlength="7" name="color" value = "<c:out value='${color}'/>" >
                                </div>
                            </div>
                        </div>
                        <div class="mymodal-footer">
                            <input type="submit" value="Create" class="btn-submit-on-form">
                        </div>
                                	<input type="hidden" name="CSRFToken" value="">
                        
                    </form>
                </div>
                </div>
            </div>
            <c:if test="${openFormEditTodo != null}">
					<div class="mymodal" id="editTodoModal" style="display: flex;" >
			</c:if>
			
			<c:if test="${openFormEditTodo == null}">
					<div class="mymodal" id="editTodoModal">
			</c:if>
			
            <!-- div class="mymodal" id="addTodoModal"> -->
                
                    <div class="mymodal-content">
                        <form action="${pageContext.request.contextPath}/updateTodo" method="post" >
                            <div class="mymodal-header">
                                <h3>Update todo</h3>
                                <a class="btn-close-mymodal" id="btn-close-editTodoModal">
                                    <span>
                                        <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor" class="bi bi-x" viewBox="0 0 16 16">
                                            <path d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"/>
                                        </svg>
                                    </span>
                                </a>
                            </div>
                            
                            <div class="mymodal-body">
                                <div class="form-container">
                                
                                	<input type="hidden" name="id" value = "<c:out value='${existingTodo.id}'/>" >
                                	<input type="hidden" name="from" value="todomonth" >
                                	
                                    <div class="input-text">
                                        <label for="title">Title</label>
                                        <input type="text" maxlength="100" name="title" value = "<c:out value='${existingTodo.title}'/>" >
                                        
                                        <div class="input-error">
                                            <p><c:out value="${titleError}"/></p>
                                        </div>
                                    </div>
                                    
                    
                                    <div class="input-date">
                                        <label for="date">Date</label>
                                        <input type="date" name="date" value = "<c:out value='${existingTodo.date}'/>">

                                        <!-- <div class="input-error">
                                            <p>Invalid input</p>
                                        </div> -->
                                    </div>
                                    
                                    <div class="input-select">
	                                    <label for="priority">Priority</label>
	                                    <select name="priority">
	                                        <option value="High" <c:if test="${existingTodo.priority == 'High'}">selected</c:if> >High</option>
	                                        <option value="Medium" <c:if test="${existingTodo.priority == 'Medium'}">selected</c:if> >Medium</option>
	                                        <option value="Low" <c:if test="${existingTodo.priority == 'Low'}">selected</c:if> >Low</option>
	                                    </select>
	                                </div>
                    
                                    <div class="input-select">
                                        <label for="tagid">Tag</label>
                                        <select name="tagid">
                                        <c:forEach var="tag" items="${sessionScope.listTag}">
                                            <option value="${tag.id}" 
	                                            <c:if test="${tag.id == existingTodo.tag.id}">selected</c:if> >
	                                            <c:out value="${tag.tagname}"></c:out>
                                            </option>
                                        </c:forEach>
                                        </select>

                                        <!-- <div class="input-error">
                                            <p>Invalid input</p>
                                        </div> -->
                                    </div>
                    
                                    <div class="input-time">
                                        <label for="deadline">Deadline</label>
                                        <input type="time" name="deadline" value = "<c:out value='${existingTodo.deadline}'/>">

                                        <!-- <div class="input-error">
                                            <p>Invalid input</p>
                                        </div> -->
                                    </div>
                                    
                                    <div class="input-time">
                                        <label for="remindat">Remind at</label>
                                        <input type="time" name="remindat" value = "<c:out value='${existingTodo.remindat}'/>">

                                        <!-- <div class="input-error">
                                            <p>Invalid input</p>
                                        </div> -->
                                    </div>

                                </div>
                            </div>
                            <div class="mymodal-footer">
                                <input type="submit" value="Update" class="btn-submit-on-form">
                            </div>
        	<input type="hidden" name="CSRFToken" value="">

                        </form>
                    </div>
            </div>
            <c:if test="${openFormEditTag != null}">
					<div class="mymodal" id="editTagModal" style="display: flex;">
					
			</c:if>
			
			<c:if test="${openFormEditTag == null}">
					<div class="mymodal" id="editTagModal">
			</c:if>
				
            <!-- <div class="mymodal" id="addTagModal"> -->
                <div class="mymodal-content">
                
                    <form action="${pageContext.request.contextPath}/updateTag" method="post" >
                        <div class="mymodal-header">
                            <h3>Update tag</h3>
                            <a class="btn-close-mymodal" id="btn-close-editTagModal">
                                <span>
                                    <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor" class="bi bi-x" viewBox="0 0 16 16">
                                        <path d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"/>
                                    </svg>
                                </span>
                            </a>
                        </div>
                        <div class="mymodal-body">
                            <div class="form-container">
                            
                            	<input type="hidden" name="id" value = "<c:out value='${existingTag.id}'/>" >
                            	<input type="hidden" name="from" value = "todomonth" >
                                
                                <div class="input-text">
                                    <label for="tagname">Tag:</label>
                                    <input type="text" maxlength="50" name="tagname" value = "<c:out value='${existingTag.tagname}'/>" required>
                                </div>
                
                                <div class="input-color">
                                    <label for="color">Color</label>
                                    <input type="color" maxlength="7" name="color" value = "<c:out value='${existingTag.color}'/>" >
                                </div>
                            </div>
                        </div>
                        <div class="mymodal-footer">
                            <input type="submit" value="Update" class="btn-submit-on-form">
                        </div>
                                	<input type="hidden" name="CSRFToken" value="">
                        
                    </form>
                </div>
            </div>
            
            <div class="mymodal" id="startTodoModal">
		        <div class="mymodal-content">
		            <div class="mymodal-header">
		                <h3>Focus mode</h3>
		                <a class="btn-close-mymodal" id="btn-close-startTodoModal">
		                    <span>
		                        <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor" class="bi bi-x" viewBox="0 0 16 16">
		                            <path d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"/>
		                        </svg>
		                    </span>
		                </a>
		            </div>
		            <div class="mymodal-body">
		
		                <!-- ===== watch.css ========= -->
		                <div class="form-container">
		
		
		                    <div class="watch">
		                        <span class="time" id="display">00:00:00</span>
		                    </div>
		                
		                     
		                    <div class="controls-watch">
		                        <a id="start">
		                            
		                                <img id="playButton"  src="${pageContext.request.contextPath}/assets/play_circle_black_24dp.svg"  />
		                            
		                        </a>
		
		                        <a id="stop" href="${pageContext.request.contextPath}/updateStartEnd?from=tododay&">
		        
		                                <img id="pauseButton" src="${pageContext.request.contextPath}/assets/pause_black_24dp.svg" />
		                            
		                        </a>
		                    </div>
		                    
		                </div>
		                <!-- ===== watch.css ========= -->
		    
		            </div>
		            <!--
		            <div class="mymodal-footer">
		                <a href="#" class="btn-on-mymodal" >
		                    Play
		                </a>
		                <a href="#" class="btn-on-mymodal">
		                    Pause
		                </a>
		            </div>
		            -->
		        </div>
   		 </div>

            <script src="${pageContext.request.contextPath}/js/todoform.js"></script>
            <script src="${pageContext.request.contextPath}/js/mymodal.js"></script>
        </div>
        
        <div class="left-content">
        
            <div class="tags-container">
                <!-- <h2 class="title-tags">Your Tags</h2> -->
                <div class="tags-inner-container">
                    <div class="tags-list">
                    <c:forEach var="tag" items="${sessionScope.listTag}">  
                        <div class="tag" style="background-color: <c:out value="${tag.color}"></c:out>;">  
                            
                            <p id="tag<c:out value="${tag.id}"></c:out>" class="tagname"><c:out value="${tag.tagname}"></c:out></p>
                            
                            <a href="${pageContext.request.contextPath}/editTag?id=<c:out value='${tag.id}'/>&from=todomonth" class="btn-on-tag">
                                <span>
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil" viewBox="0 0 16 16">
                                        <path d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5L13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175l-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z"/>
                                    </svg>
                                </span>
                            </a>
                    
                            <a href="${pageContext.request.contextPath}/deleteTag?id=<c:out value='${tag.id}'/>&from=todomonth" class="btn-on-tag">
                                <span>
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
                                        <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>
                                        <path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4L4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"/>
                                    </svg>
                                </span>
                            </a>
                
                            <script>
                                $("#tag<c:out value="${tag.id}"></c:out>").attr("style", "Color: " + ColorText('<c:out value="${tag.color}"></c:out>'));
                                $("#tag<c:out value="${tag.id}"></c:out>").siblings().attr("style", "Color: " + ColorText('<c:out value="${tag.color}"></c:out>'));
                            </script>
                        </div>
 					</c:forEach>
                    </div>
                </div>
            </div>
    
            <div class="filter-todo">
                <div class="filter-content">
                    <form action="${pageContext.request.contextPath}/filterTodo" method="post">
                        <!-- <h2 class="title-form">Filter todo</h2> -->
                            <div class="form-container">
                                <div class="input-month">
                                    <label for="month">Month</label>
                                    <input type="month" name="month" id="month" value="<%=month%>">
                                </div>
                                
                                <div class="input-select">
                                    <label for="stateFilter">State</label>
                                    <select name="stateFilter" id="stateFilter">
                                        <c:set var="stateFilter" value="<%= stateFilter %>"></c:set>
											<option value="null" selected></option>
											<option value="true" <c:if test="${stateFilter=='true'}">selected</c:if>>Done</option>
											<option value="false"<c:if test="${stateFilter=='false'}">selected</c:if>>Pending</option>
                                    </select>
                                </div>
                
                                <div class="input-select">
                                    <label for="tagidFilter">Tag</label>
                                    <select name="tagidFilter" id="tagidFilter">
                                    	<option value="-1" selected></option>
                                    	<c:set var="tagidFilter" value="<%= tagidFilter%>"></c:set>
                                        <c:forEach var="tag" items="${listTag}">
											<option value="${tag.id}" <c:if test="${tag.id == tagidFilter}">selected</c:if>>
											<c:out value="${tag.tagname}"></c:out>
											</option>
										</c:forEach>
                                    </select>
                                </div>
    
                                <div class="input-select">
                                    <label for="prioFilter">Priority</label>
                                    <select name="prioFilter" id="prioFilter">
                                        <c:set var="prioFilter" value="<%= prioFilter %>"></c:set>
											<option value="null" selected></option>
											<option value="High" <c:if test="${prioFilter=='High'}">selected</c:if>>High</option>
											<option value="Medium" <c:if test="${prioFilter=='Medium'}">selected</c:if>>Medium</option>
											<option value="Low" <c:if test="${prioFilter=='Low'}">selected</c:if>>Low</option>
                                    </select>
                                </div>
    							<input type="hidden" value="date" name="type">
    							<label style="display: block;line-height: 1.5;">
								  	<input type="checkbox" style="margin-right: 8px; margin-left: 8px;" id="sort" name="sort" value="Sort" <% if (sort.equals("Sort")) { %> checked <% } %>>
									Sort by level and progress of the task
								</label>
								
    							<input type="hidden" value="month" name="type">
                                <input type="submit" value="Filter" class="btn-submit-on-form">
                        </div>
                                	<input type="hidden" name="CSRFToken" value="">
                        
                    </form>
                </div>
            </div>
        </div>
        
        <div class="center-content">
            <div class="tasks-container">
                <div class="tasks-list">
                <c:forEach var="todo" items="${listTodo}">
                
                <c:if test="${todo.priority.equals('High')}">
                         <div class="task-container" style="border: 3px solid #FEE95C;" >  
                   </c:if>   
                   <c:if test="${todo.priority.equals('Medium')}">
                         <div class="task-container" style="border: 2px solid #eab159" >  
                   </c:if>   
                   <c:if test="${todo.priority.equals('Low')}">
                         <div class="task-container"style="border: 1px solid #bc85a3" >  
                   </c:if>
                   
                    <!-- <div class="task-container"> -->
                        <div class="done-area">
                            <c:if test="${todo.done == false}">
                            <a href="${pageContext.request.contextPath}/UpdateStatusTodo?id=<c:out value='${todo.id}'/>&from=todomonth" class="btn-done">
                            	<svg fill="currentColor" viewBox="0 0 16 16">
                                        <path d="M14 1a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1h12zM2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2z"/>
                                    </svg>
                            </a>
                            </c:if>
                            
                            <c:if test="${todo.done == true}">
                            <a class="btn-done">
                            	<svg fill="currentColor" viewBox="0 0 16 16">
                                        <path d="M2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2zm10.03 4.97a.75.75 0 0 1 .011 1.05l-3.992 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.75.75 0 0 1 1.08-.022z"/>
                                    </svg>
                            </a>
                            </c:if>
                        </div>
                        
                
                        <div class="title-area">
                            <p class="title"><c:out value="${todo.title}"></c:out></p>
                            <div class="info">
                                <p class="tag" style="background: <c:out value="${todo.tag.color}"></c:out>; 
                                						border-color: <c:out value="${todo.tag.color}"></c:out>">
                                	<c:out value="${todo.tag.tagname}"></c:out>
                                </p>
                                <p class="date">Date: ${todo.date}</p>
                                <c:if test="${todo.deadline != null}">
	                            	<p class="deadline">Deadline: <c:out value="${todo.deadline}"></c:out></p>
                            	</c:if>
                            	
                            	<c:if test="${todo.start != null}">
	                            	<p class="startend">Started: <c:out value="${todo.start}"></c:out></p>
	                            	<p class="startend">End: <c:out value="${todo.end}"></c:out></p>
                            	</c:if>
                                
                            </div>
                        </div>
                        
                
                        <div class="buttons-area">
                            <c:if test = "${todo.done == false}">
                            <a class="btn-on-todo btn-open-startTodoModal" id="btn-open-startTodoModal">
                            	<p style="display: none;" class="id"><c:out value='${todo.id}' /></p>
                            	
                                <svg fill="currentColor" viewBox="0 0 16 16">
                                    <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
                                    <path d="M6.271 5.055a.5.5 0 0 1 .52.038l3.5 2.5a.5.5 0 0 1 0 .814l-3.5 2.5A.5.5 0 0 1 6 10.5v-5a.5.5 0 0 1 .271-.445z"/>
                                </svg>
                            </a>
                            </c:if>
                            
                            <c:if test = "${todo.done == true}">
                            
                            <a class="btn-on-todo" id="btn-open-startTodoModal">
                            	<p style="display: none;"  class="id"><c:out value='${todo.id}' /></p>
                            	
                                <svg fill="#ffffff" viewBox="0 0 16 16">
                                    <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
                                    <path d="M6.271 5.055a.5.5 0 0 1 .52.038l3.5 2.5a.5.5 0 0 1 0 .814l-3.5 2.5A.5.5 0 0 1 6 10.5v-5a.5.5 0 0 1 .271-.445z"/>
                                </svg>
                            </a>
                            </c:if>
                
                            <a href="${pageContext.request.contextPath}/editTodo?id=<c:out value='${todo.id}'/>&from=todomonth" class="btn-on-todo">
                                    <svg fill="currentColor" viewBox="0 0 16 16">
                                        <path d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5L13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175l-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z"/>
                                    </svg>
                            </a>
                
                            <a href="${pageContext.request.contextPath}/deleteTodo?id=<c:out value='${todo.id}'/>&from=todomonth" class="btn-on-todo">
                                    <svg fill="currentColor" viewBox="0 0 16 16">
                                        <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>
                                        <path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4L4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"/>
                                    </svg>
                            </a>
                        </div>
                        
                    </div>
                </c:forEach>
                </div>
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
    
    <script src="${pageContext.request.contextPath}/js/watch.js"></script>
</body>
</html>