package com.webmvc.todo.controller;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.text.*;
import org.apache.commons.validator.routines.EmailValidator;

import com.webmvc.todo.dao.UserDao;
import com.webmvc.todo.model.User;
import com.webmvc.todo.utils.PasswordUtils;

@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    UserDao userDao = null;
    
    public Login() {
        super();
        userDao = new UserDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			login(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("index.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String url = "";
		
		String email  = StringEscapeUtils.escapeHtml4(request.getParameter("email").trim());
		System.out.println("Email: " + email);
		request.setAttribute("email", email);
		
		String password = StringEscapeUtils.escapeHtml4(request.getParameter("password").trim());
		System.out.println("Password: " + password);
		request.setAttribute("password", password);
		
		EmailValidator validator = EmailValidator.getInstance();
		if (email.length()>101) {
			System.out.println("Email ::: " + "email.length()>101");
			request.setAttribute("emailError", "* Invalid email length!"); 
			url="/login.jsp";
		} else if (!validator.isValid(email)) {
			System.out.println("Email ::: " + "validator.isValid(email)");
			request.setAttribute("emailError", "* Invalid email!"); 
			url="/login.jsp";
		}
		
		if(password.equals(""))
		{
			System.out.println("Password ::: " + "password.equals(\"\")");
			request.setAttribute("passwordError", "* You must enter password");
			url="/login.jsp";
		}
		
		if (url.equals(""))
		{
			User user = userDao.EmailExist(email);
			
			if (user != null) {
				String salt = user.getSalt();
				String key = user.getPassword();
				
				if (PasswordUtils.correctPassword(password, key, salt))
				{
					HttpSession session = request.getSession(true);
					session.setAttribute("user", user);
				}
				else
				{
					url="/login.jsp";
					request.setAttribute("passwordError", "* Password incorrect");
				}
				
			} else {
				url="/login.jsp";
				request.setAttribute("emailError", "* Email not registered");
			}
		}

		if (url.equals("")) {
			System.out.println("Chuyen qua listDashboard");
			response.sendRedirect("listDashboard");
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
		}
		
	}
	
}
