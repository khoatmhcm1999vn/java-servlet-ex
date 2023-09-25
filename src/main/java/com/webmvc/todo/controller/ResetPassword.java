package com.webmvc.todo.controller;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.text.StringEscapeUtils;
import org.apache.commons.validator.routines.EmailValidator;

import com.webmvc.todo.dao.UserDao;
import com.webmvc.todo.model.User;
import com.webmvc.todo.utils.PasswordUtils;

@WebServlet("/resetPassword")
public class ResetPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao;
	
    public ResetPassword() {
        super();
        userDao = new UserDao();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			resetPassword(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("index.jsp");
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void resetPassword(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String url = "";
		
		String email = StringEscapeUtils.escapeHtml4(request.getParameter("email").trim());
		request.setAttribute("email", email);
		System.out.println("Email ::: " + email);
		
		String password = StringEscapeUtils.escapeHtml4(request.getParameter("password").trim());
		request.setAttribute("password", password);
		System.out.println("Password ::: " + password);
		
		EmailValidator validator = EmailValidator.getInstance();
		if (email.length()>100) {
			System.out.println("Email ::: " + "email.length()>101");
			request.setAttribute("emailError", "* Invalid email length!"); 
			url="/reset-password.jsp";
		} else if (!validator.isValid(email)) {
			System.out.println("Email ::: " + "validator.isValid(email)");
			request.setAttribute("emailError", "* Invalid email!"); 
			url="/reset-password.jsp";
		}
		
		String regexPassword = "[(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%])]{8,20}";
		Pattern patternPassword = Pattern.compile(regexPassword);
		
		if (!patternPassword.matcher(password).matches())
		{
			System.out.println("Password ::: " + "!patternPassword.matcher(password).matches()");
			request.setAttribute("passwordError", "* Password should be between 8 and 20 characters, with at least 1 uppercase, 1 lowercase, 1 digit and 1 special character (@#$%)");
			url="/reset-password.jsp";
		}
		
		if (url.equals("")) {
			User user = userDao.existUser(email);
			if ( user != null ) {
        		// Hash password
				String salt = PasswordUtils.generateSalt(20).get();
				System.out.println("Salt ::: " + salt);
				
				String passwordSecurity = PasswordUtils.hashPassword(password, salt).get();
				System.out.println("Password Security ::: " + passwordSecurity);
				
				userDao.resetPassword(email, passwordSecurity, salt);
				url="/reset-password-success.jsp";
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
				dispatcher.forward(request, response);
				
			} else {
				System.out.println("Email ::: " + "(User)userDao.EmailExist(email)) == null");
				request.setAttribute("emailError", "* This email is not registered!");
				url="/reset-password.jsp"; 
			}

			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
        }
		
	}

}
