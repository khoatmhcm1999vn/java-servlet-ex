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

@WebServlet("/editTodo")
public class EditTodo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TodoDao todoDao;
	HttpSession session = null;
	
    public EditTodo() {
        super();
		todoDao = new TodoDao();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession(true);

		try {
			showEditTodoForm(request, response);
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
	
	private void showEditTodoForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		
		User user = (User) session.getAttribute("user");
		if(user!=null) {
			if(request.getParameter("from").equals("dashboard") || request.getParameter("from").equals("tododay") || 
					request.getParameter("from").equals("todoweek") || request.getParameter("from").equals("todomonth")) {
				String from = request.getParameter("from").trim();
				try {
					int id = Integer.parseInt(request.getParameter("id"));
					
					Todo existingTodo = todoDao.getTodo(id);
					if (existingTodo.getUser().getId()==user.getId()) {
						request.setAttribute("existingTodo", existingTodo);
						request.setAttribute("openFormEditTodo", "open");
						
						RequestDispatcher dispatcher;
						
						if (from.equals("dashboard")) {
							dispatcher = request.getRequestDispatcher("dashboard.jsp");
						} else if (from.equals("tododay")) {
							dispatcher = request.getRequestDispatcher("tododay.jsp");
						} else if (from.equals("todoweek")) {
							dispatcher = request.getRequestDispatcher("todoweek.jsp");
						} else {
							dispatcher = request.getRequestDispatcher("todomonth.jsp");
						}
						
						dispatcher.forward(request, response);
					} else {
						session.invalidate();
						RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
						dispatcher.forward(request, response);
					}

				} catch (Exception e) {
					RequestDispatcher dispatcher;
					dispatcher = request.getRequestDispatcher("error.jsp");
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
