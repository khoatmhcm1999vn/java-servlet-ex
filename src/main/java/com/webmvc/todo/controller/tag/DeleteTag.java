package com.webmvc.todo.controller.tag;

import java.io.IOException;
import java.sql.SQLException;
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

@WebServlet("/deleteTag")
public class DeleteTag extends HttpServlet {
	private static final long serialVersionUID = 1L;
	TagDao tagDao = null;
	HttpSession session = null;
    
    public DeleteTag() {
        super();
        tagDao = new TagDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession(true);
		try {
			deleteTag(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void deleteTag(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		
		User user = (User) session.getAttribute("user");
		if(user!=null) {
			if(request.getParameter("from").equals("dashboard") || request.getParameter("from").equals("tododay") || request.getParameter("from").equals("todoweek") || request.getParameter("from").equals("todomonth")) {
				
				String from = request.getParameter("from");
				
				int id = Integer.parseInt(request.getParameter("id"));
				Tag tag = tagDao.getTag(id);
				if (tag.getUser().getId() == user.getId()) {
					tagDao.deleteTag(id);
				
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
					session.invalidate();
					RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
					dispatcher.forward(request, response);
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
