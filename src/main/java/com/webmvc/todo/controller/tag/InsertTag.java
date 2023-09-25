package com.webmvc.todo.controller.tag;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.text.StringEscapeUtils;

import com.webmvc.todo.dao.TagDao;
import com.webmvc.todo.model.Tag;
import com.webmvc.todo.model.User;

@WebServlet("/insertTag")
public class InsertTag extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TagDao tagDao;
	HttpSession session = null;
	
	
    public InsertTag() {
        super();
        tagDao = new TagDao();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession(true);
		try {
			insertTag(request, response);
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
		doGet(request, response);
	}
	
	public void insertTag(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException, ParseException {

		User user = (User) session.getAttribute("user");
		if(user!=null) {
			if(request.getParameter("from").equals("dashboard") || request.getParameter("from").equals("tododay") || request.getParameter("from").equals("todoweek") || request.getParameter("from").equals("todomonth")) {
				
				String from = request.getParameter("from");
				String flag = "";
				
				String tagname = StringEscapeUtils.escapeHtml4(request.getParameter("tagname").trim());
				request.setAttribute("tagname", tagname);
				System.out.println("Tagname ::: " + tagname);
				
				String regexName = "^[\\p{L}\\d]{1}[\\p{L}\\d .'\\-,]{0,49}$";
				Pattern patternName = Pattern.compile(regexName);
				
				if (!patternName.matcher(tagname).matches())
				{
					System.out.println("Tagname ::: " + "!pattern.matcher(tagname).matches()");
					request.setAttribute("tagnameError", "* Tag Name should start with a letter or number and contain only letters, numbers, spaces and characters: (.), (,), (-), (')");
					flag = "error";
				}
				
				String color = StringEscapeUtils.escapeHtml4(request.getParameter("color").trim());
				request.setAttribute("color", color);
				System.out.println("Color ::: " + color);
				
				String regexColor = "^#([a-fA-F0-9]{6}|[a-fA-F0-9]{3})$";
				Pattern patternColor = Pattern.compile(regexColor);
				
				if (!patternColor.matcher(color).matches()) {
					System.out.println("Color ::: " + "!patternColor.matcher(color).matches()");
					request.setAttribute("colorError", "* Color code should be in the form #RRGGBB");
					flag="error";
				}
				
				if (flag.equals("")) {
					Tag newTag = new Tag(tagname, color, user);
					tagDao.saveTag(newTag);
				}
				
				if (from.equals("dashboard")) {
					response.sendRedirect("listDashboard");
				} else if (from.equals("tododay")) {
					response.sendRedirect("listTodo");
				} else if (from.equals("todoweek")) {
					response.sendRedirect("listTodoThisWeek");
				} else {
					response.sendRedirect("listTodoThisMonth");
				}	
				
			} else {
				RequestDispatcher dispatcher;
				dispatcher = request.getRequestDispatcher("error.jsp");
				dispatcher.forward(request, response);
			}
			
		} else {
			System.out.println("Nguoi dung null");
			RequestDispatcher dispatcher;
			dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
		}
		
	}

}
