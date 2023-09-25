package com.webmvc.todo.controller.todo;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.webmvc.todo.dao.TagDao;
import com.webmvc.todo.dao.TodoDao;

import com.webmvc.todo.model.Todo;
import com.webmvc.todo.model.User;


/**
 * Servlet implementation class UpdateStatusTodo
 */
@WebServlet("/UpdateStatusTodo")
public class UpdateStatusTodo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TodoDao todoDao;
	private TagDao tagDao;
	HttpSession session = null;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateStatusTodo() {
        super();
        todoDao = new TodoDao();
		tagDao = new TagDao();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		session = request.getSession(true);
		try {
			updateStatusTodo(request, response);
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	private void updateStatusTodo(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException, ParseException {

		User user = (User) session.getAttribute("user");
		if(user!=null) {
			
			if(request.getParameter("from").equals("dashboard") || request.getParameter("from").equals("tododay") || 
					request.getParameter("from").equals("todoweek") || request.getParameter("from").equals("todomonth")) {
				String from = request.getParameter("from").trim();
				
				int id = Integer.parseInt(request.getParameter("id"));
				Todo td = todoDao.getTodo(id);
				if (td.getUser().getId()==user.getId()) {
					System.out.println(td.toString());
					if(td.getDone() == false){
						td.setDone(true);
					}
					
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
					Date date = new Date();
					td.setDoneat(date);
					System.out.println(td.toString());
					todoDao.updateTodo(td);
					
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
