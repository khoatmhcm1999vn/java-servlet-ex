package com.webmvc.todo.controller.todo;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.webmvc.todo.dao.TodoDao;
import com.webmvc.todo.model.Todo;
import com.webmvc.todo.model.User;

@WebServlet("/deleteTodo")
public class DeleteTodo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TodoDao todoDao;
    HttpSession session = null;

    public DeleteTodo() {
        super();
        todoDao = new TodoDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession(true);

		try {
			deleteTodo(request, response);
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
	
	private void deleteTodo(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		
		User user = (User) session.getAttribute("user");
		if(user!=null) {
			if(request.getParameter("from").equals("dashboard") || request.getParameter("from").equals("tododay") || 
					request.getParameter("from").equals("todoweek") || request.getParameter("from").equals("todomonth")) {
				try {
					String from = request.getParameter("from").trim();
					
					int id = Integer.parseInt(request.getParameter("id"));
					Todo todo = todoDao.getTodo(id);
					if (todo.getUser().getId() == user.getId()) {
						todoDao.deleteTodo(id);
					
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
					
				} catch(Exception e) {
					RequestDispatcher dispatcher;
					dispatcher = request.getRequestDispatcher("error.jsp");
					dispatcher.forward(request, response);
				}
			}
			
		} else {
			System.out.println("Nguoi dung null");
			RequestDispatcher dispatcher;
			dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
		}
	}

}
