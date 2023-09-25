package com.webmvc.todo.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.webmvc.todo.model.User;

@WebServlet("/error")
public class Error extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession session = null;
	
    public Error() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession(true);
		redirect(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void redirect(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) session.getAttribute("user");
		RequestDispatcher dispatcher;
		if(user!=null) {
			response.sendRedirect("listDashboard");
		} else {
			dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
		}
	}

}
