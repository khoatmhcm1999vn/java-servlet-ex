package com.webmvc.todo.controller.todo;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.webmvc.todo.dao.TodoDao;
import com.webmvc.todo.model.Tag;
import com.webmvc.todo.model.Todo;
import com.webmvc.todo.model.User;

@WebServlet("/updateTodo")
public class UpdateTodo extends HttpServlet {
	private static final long serialVersionUID = 1L;  
	private TodoDao todoDao;
	private TagDao tagDao;
	HttpSession session = null;
	
    public UpdateTodo() {
        super();
		todoDao = new TodoDao();
		tagDao = new TagDao();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession(true);
		try {
			updateTodo(request, response);
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
	
	private void updateTodo(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException, ParseException {
		User user = (User) session.getAttribute("user");
		if(user!=null) {
			if(request.getParameter("from").equals("dashboard") || request.getParameter("from").equals("tododay") || 
					request.getParameter("from").equals("todoweek") || request.getParameter("from").equals("todomonth")) {
				String from = request.getParameter("from");
				try {
					
					int id = Integer.parseInt(request.getParameter("id"));
					String flag = "";
					
					String title = StringEscapeUtils.escapeHtml4(request.getParameter("title").trim());
					request.setAttribute("title", title);
					System.out.println("title ::: " + title);
					
					String regexName = "^[\\p{L}\\d]{1}[\\p{L}\\d .'\\-,]{0,99}$";
					Pattern patternName = Pattern.compile(regexName);
					
					if (!patternName.matcher(title).matches())
					{
						System.out.println("Title ::: " + "!pattern.matcher(title).matches()");
						request.setAttribute("titleError", "* Title should start with a letter or number and contain only letters, numbers, spaces and characters: (.), (,), (-), (')");
						flag = "error";
					}
					
					//String title = request.getParameter("title").trim();
					
					//String priority = request.getParameter("priority").trim();
					
					String priority = StringEscapeUtils.escapeHtml4(request.getParameter("priority").trim());
					request.setAttribute("priority", priority);
					System.out.println("priority ::: " + priority);
					
					if ((!priority.equals("High")) && (!priority.equals("Medium")) && (!priority.equals("Low"))) {
						System.out.println("priority ::: " + "!priority.equals(...)");
						request.setAttribute("priorityError", "* Invalid priority");
						flag = "error";
					}
					
					Integer tagid = Integer.parseInt(request.getParameter("tagid").trim());

					Tag tag = tagDao.getTag(tagid);
					
					String date_str = request.getParameter("date");
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					Date date = df.parse(date_str);
					
					Todo existingTodo = new Todo(id, title, priority, tag, date);
					
					if (flag.equals("")) {
						
						existingTodo.setUser(user);
						if (tag.getUser().getId()==user.getId()&&existingTodo.getUser().getId()==user.getId()) {
							todoDao.updateTodo(existingTodo);

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