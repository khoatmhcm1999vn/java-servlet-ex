package com.webmvc.todo.controller.todo;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.webmvc.todo.dao.UserDao;
import com.webmvc.todo.model.Tag;
import com.webmvc.todo.model.Todo;
import com.webmvc.todo.model.User;


@WebServlet("/listTodo")
public class ListTodo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDao userDao = null;
	HttpSession session = null;
	
    public ListTodo() {
        super();
        userDao = new UserDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession(true);
		listTodo(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void listTodo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) session.getAttribute("user");
		if(user!=null) {
			String now = LocalDate.now().toString();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			
			List<Todo> listTodo = userDao.getTodosByUser(user.getId());
			List<Tag> listTag = userDao.getTagsByUser(user.getId());
			List<Todo> result=new ArrayList<Todo>();
			try {
				Date today = df.parse(now);
				for( int i=0; i< listTodo.size(); i++) {
					if (listTodo.get(i).getDate().compareTo(today)==0) {
						result.add(listTodo.get(i));
					}
				}
			} catch(Exception e) {
				
			}
			
			session.setAttribute("listTodo", result); 
			session.setAttribute("listTag", listTag);
			session.setAttribute("listTodoTotal", listTodo);
			 
			RequestDispatcher dispatcher = request.getRequestDispatcher("tododay.jsp");
			dispatcher.forward(request, response);
		} else {
			System.out.println("Nguoi dung null");
			RequestDispatcher dispatcher;
			dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
		}
	}

}
