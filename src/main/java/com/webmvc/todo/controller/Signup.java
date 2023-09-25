package com.webmvc.todo.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.text.StringEscapeUtils;
import org.apache.commons.validator.routines.EmailValidator;

import com.webmvc.todo.dao.TagDao;
import com.webmvc.todo.dao.UserDao;
import com.webmvc.todo.model.Tag;
import com.webmvc.todo.model.User;
import com.webmvc.todo.utils.PasswordUtils;

@WebServlet("/signup")
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao;
	private TagDao tagDao;
	
    public Signup() {
        super();
        userDao = new UserDao();
        tagDao = new TagDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			signup(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.sendRedirect("index.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void signup(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url="";
		
		String email = StringEscapeUtils.escapeHtml4(request.getParameter("email").trim());
		request.setAttribute("email", email);
		System.out.println("Email ::: " + email);
		
		String password =StringEscapeUtils.escapeHtml4(request.getParameter("password").trim());
		request.setAttribute("password", password);
		System.out.println("Password ::: " + password);
		
		String fullname = StringEscapeUtils.escapeHtml4(request.getParameter("fullname").trim());
		request.setAttribute("fullname", fullname);
		System.out.println("Fullname ::: " + fullname);
		
		boolean gender = Boolean.parseBoolean(StringEscapeUtils.escapeHtml4(request.getParameter("gender").trim()));
		request.setAttribute("gender", gender);
		System.out.println("Gender ::: " + gender);
		
		String birthdate_str = StringEscapeUtils.escapeHtml4(request.getParameter("birthdate").trim());
		request.setAttribute("birthdate_str", birthdate_str);
		System.out.println("Birthdate ::: " + birthdate_str);
		
		//String regexEmail = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
		//Pattern patternEmail = Pattern.compile(regexEmail);
		
		EmailValidator validator = EmailValidator.getInstance();
		if (email.length()>101) {
			System.out.println("Email ::: " + "email.length()>101");
			request.setAttribute("emailError", "* Invalid email length!"); 
			url="/signup.jsp";
		} else if (!validator.isValid(email)) {
			System.out.println("Email ::: " + "validator.isValid(email)");
			request.setAttribute("emailError", "* Invalid email!"); 
			url="/signup.jsp";
		} else if (((User)userDao.EmailExist(email)) != null ) {
			System.out.println("Email ::: " + "(User)userDao.EmailExist(email)) != null");
			request.setAttribute("emailError", "* Email already registered!");
			url="/signup.jsp"; 
		}
		
		/*
		 * if (email.equals("") == true) { request.setAttribute("emailError",
		 * "* You must enter email!"); url="/signup.jsp"; } else if
		 * (!patternEmail.matcher(email).matches()) { request.setAttribute("emailError",
		 * "* Invalid email!"); url="/signup.jsp"; } else if
		 * (((User)userDao.EmailExist(email))== null ) {
		 * request.setAttribute("emailError", "* Email already registered!");
		 * url="/signup.jsp"; }
		 */
		String regexName = "^[\\p{L}\\d]{1}[\\p{L}\\d .'\\-,]{0,49}$";
		Pattern patternName = Pattern.compile(regexName);
		
		if (!patternName.matcher(fullname).matches())
		{
			System.out.println("Name ::: " + "!patternName.matcher(fullname).matches()");
			request.setAttribute("fullnameError", "* Name should start with a letter or number and contain only letters, numbers, spaces and characters: (.), (,), (-), (')");
			url="/signup.jsp";
		} 
		
		String regexPassword = "[(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%])]{8,20}";
		Pattern patternPassword = Pattern.compile(regexPassword);
		
		if (!patternPassword.matcher(password).matches())
		{
			System.out.println("Password ::: " + "!patternPassword.matcher(password).matches()");
			request.setAttribute("passwordError", "* Password should be between 8 and 20 characters, with at least 1 uppercase, 1 lowercase, 1 digit and 1 special character (@#$%)");
			url="/signup.jsp";
		}
		
		
		if (birthdate_str.equals("")) {
			System.out.println("Birthdate ::: " + "birthdate_str.equals(\"\")");
			request.setAttribute("birthdateError", "* Invalid date!");
			url="/signup.jsp";
		}

		if (url=="") {
			try {
				
				// Hash password
				String salt = PasswordUtils.generateSalt(20).get();
				System.out.println("Salt ::: " + salt);
				
				String passwordSecurity = PasswordUtils.hashPassword(password, salt).get();
				System.out.println("Password Security ::: " + passwordSecurity);
				
				User newUser = new User(email, passwordSecurity, salt, fullname, gender);
				
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				Date birthdate = df.parse(birthdate_str);
				newUser.setBirthdate(birthdate);
					
				userDao.saveUser(newUser);
					
				Tag defaultTag = new Tag(0, "Other", "#cccccc", newUser);
				tagDao.saveTag(defaultTag);
					
				url="/signup-success.jsp";
				
				System.out.println("New User ::: " + "userDao.saveUser(newUser);");
						
				} catch (Exception e) {
					System.out.println("Birthdate ::: " + e.getMessage());
					url="/signup.jsp";
					request.setAttribute("birthdateError", "* Invalid date!");	
				}
		}
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}
