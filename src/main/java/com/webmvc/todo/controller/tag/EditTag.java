package com.webmvc.todo.controller.tag;

import java.io.IOException;
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

@WebServlet("/editTag")
public class EditTag extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TagDao tagDao = null;
    HttpSession session = null;
    
    public EditTag() {
        super();
        tagDao = new TagDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession(true);
		try {
			showEditTagForm(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void showEditTagForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		User user = (User) session.getAttribute("user");
		
		if(user!=null) {
			if(request.getParameter("from").equals("dashboard") || request.getParameter("from").equals("tododay") || 
					request.getParameter("from").equals("todoweek") || request.getParameter("from").equals("todomonth")) {
				
				String from = request.getParameter("from");
				try {
					int id = Integer.parseInt(request.getParameter("id"));
					Tag existingTag = tagDao.getTag(id);
					
					if (existingTag.getUser().getId() == user.getId()) {
						request.setAttribute("existingTag", existingTag);
						request.setAttribute("openFormEditTag", "open");
						
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
