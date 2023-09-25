package com.webmvc.todo.controller.user;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.text.StringEscapeUtils;

import com.webmvc.todo.dao.UserDao;
import com.webmvc.todo.model.User;

@WebServlet("/updateUser")
public class UpdateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDao userDao = null;
	HttpSession session = null;
	
    public UpdateUser() {
        super();
        userDao = new UserDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		session = request.getSession(true);
		try {
			updateUser(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void updateUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException, ParseException {
		User user = (User) session.getAttribute("user");
		if(user!=null) {
			String url = "";
			//String email = request.getParameter("email").trim();
			//String password = request.getParameter("password").trim();
			
			String fullname = StringEscapeUtils.escapeHtml4(request.getParameter("fullname").trim());
			request.setAttribute("fullname", fullname);
			System.out.println("Fullname ::: " + fullname);
			
			boolean gender = Boolean.parseBoolean(StringEscapeUtils.escapeHtml4(request.getParameter("gender").trim()));
			request.setAttribute("gender", gender);
			System.out.println("Gender ::: " + gender);
			
			String birthdate_str = StringEscapeUtils.escapeHtml4(request.getParameter("birthdate").trim());
			request.setAttribute("birthdate_str", birthdate_str);
			System.out.println("Birthdate ::: " + birthdate_str);
			
			String regexName = "^[\\p{L}\\d]{1}[\\p{L}\\d .'\\-,]{0,49}$";
			Pattern patternName = Pattern.compile(regexName);
			
			if (!patternName.matcher(fullname).matches())
			{
				System.out.println("Name ::: " + "!patternName.matcher(fullname).matches()");
				request.setAttribute("fullnameError", "* Name should start with a letter or number and contain only letters, numbers, spaces and characters: (.), (,), (-), (')");
				url="/profile.jsp";
			}
			
			if (birthdate_str.equals("")) {
				System.out.println("Birthdate ::: " + "birthdate_str.equals(\"\")");
				request.setAttribute("birthdateError", "* Invalid date!");
				url="/profile.jsp";
			}
			
			if (url=="") {
				user.setFullname(fullname);
				user.setGender(gender);
				
				try {
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					Date birthdate = df.parse(birthdate_str);
					user.setBirthdate(birthdate);
				} catch (Exception e) {
					//
				}
				
				userDao.updateUser(user);
	    		session.setAttribute("user", userDao.getUser(user.getId()));
				
			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("profile.jsp");
			dispatcher.forward(request, response);
			
		} else {
			System.out.println("Nguoi dung null");
			
			RequestDispatcher dispatcher;
			dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
		}
	}

}
