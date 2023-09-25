package com.webmvc.todo.controller.todo;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
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

/**
 * Servlet implementation class FilterTodo
 */
@WebServlet("/filterTodo")
public class FilterTodo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDao userDao = null;
	HttpSession session = null;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FilterTodo() {
        super();
        userDao = new UserDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession(true);
		filterTodo(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	private void filterTodo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) session.getAttribute("user");
		if(user!=null) {
			int tagidFilter;
			try {
				tagidFilter = Integer.parseInt(request.getParameter("tagidFilter"));
			}catch(Exception e)
			{
				tagidFilter = -1;
			}

			String prioFilter=request.getParameter("prioFilter").trim();
			String stateFilter=request.getParameter("stateFilter").trim();
			String type= request.getParameter("type");
			String datefilter = request.getParameter("datefilter");
			String week= request.getParameter("week");
			String month= request.getParameter("month");
			if(week==null||week.equals("null"))
				week="";
			if(datefilter==null||datefilter.equals("null"))
				datefilter="";
			if(month==null||month.equals("null"))
				month="";
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String sort=request.getParameter("sort");
			List<Todo> listTodo =userDao.getTodosByUser(user.getId());
			List<Tag> listTag = userDao.getTagsByUser(user.getId());
			List<Todo> result=new ArrayList<Todo>();
				if(stateFilter.equals("null")==false)
				{
					for(int i=0; i< listTodo.size();i++) {
						if (listTodo.get(i).getDone()==Boolean.parseBoolean(request.getParameter("stateFilter")))
						{
							result.add(listTodo.get(i));
						}
						
					}
				}

				if(prioFilter.equals("null")==false)
				{
					if (result.size() > 0)
					{
						for( int i=0; i< result.size();i++) {
							if (result.get(i).getPriority().equals(prioFilter)==false)
							{
								result.remove(result.get(i));
								i--;
							}
						}
					}
					else if(stateFilter.equals("null"))
					{
						for( int i=0; i< listTodo.size();i++) {
							if (listTodo.get(i).getPriority().equals(prioFilter))
							{
								result.add(listTodo.get(i));
							}
						}
					}
				}

				if(tagidFilter!=-1)
				{
					if (result.size() > 0)
					{
							for( int i=0; i< result.size();i++) 
							{
								if (result.get(i).getTag().getId()!=tagidFilter)
								{
									result.remove(result.get(i));
									i--;
								}
							}
					}
					else if(stateFilter.equals("null") && prioFilter.equals("null"))
					{
						for( int i=0; i< listTodo.size();i++) 
						{
							if (listTodo.get(i).getTag().getId()==tagidFilter)
							{
								result.add(listTodo.get(i));
							}
						}
					}
				}

				if(type.equals("date"))
				{
					if(datefilter.equals("")==false)
					{
						try
						{
							Date date_filter = df.parse(datefilter);
							if (result.size() > 0)
							{
									for( int i=0; i< result.size();i++) 
									{
										if (result.get(i).getDate().compareTo(date_filter)>0||result.get(i).getDate().compareTo(date_filter)<0)
										{
											result.remove(result.get(i));
											i--;
										}
									}
							}
							else if(stateFilter.equals("null") && prioFilter.equals("null")&&tagidFilter==-1)
							{
								for( int i=0; i< listTodo.size();i++) 
								{
									if (listTodo.get(i).getDate().compareTo(date_filter)==0)
									{
										result.add(listTodo.get(i));
									}
								}
							}
						}
						catch (ParseException e)
						{
						}
					}
					else
					{
						String now= LocalDate.now().toString();
						try {
							Date date_filter = df.parse(now);
							if (result.size() > 0)
							{
									for( int i=0; i< result.size();i++) 
									{
										if (result.get(i).getDate().compareTo(date_filter)>0||result.get(i).getDate().compareTo(date_filter)<0)
										{
											result.remove(result.get(i));
											i--;
										}
									}
							}
							else if(stateFilter.equals("null") && prioFilter.equals("null")&&tagidFilter==-1)
							{
								for( int i=0; i< listTodo.size();i++) 
								{
									if (listTodo.get(i).getDate().compareTo(date_filter)==0)
									{
										result.add(listTodo.get(i));
									}
								}
							}
						}catch(Exception e) {
							
						}
					}
				}
				else if(type.equals("week"))
				{
					if(week.equals("")==false)
					{

						String[] parts = week.split("-W");
						String part1 = parts[0]; 
						String part2 = parts[1]; 
						int weekk = Integer.parseInt(part2);
						int year = Integer.parseInt(part1);
						// Get calendar, clear it and set week number and year.
						Calendar calendar = Calendar.getInstance();
						calendar.clear();
						calendar.set(Calendar.YEAR, year);
						if(year==2020)
							calendar.set(Calendar.WEEK_OF_YEAR, weekk);
						else
							calendar.set(Calendar.WEEK_OF_YEAR, weekk+1);
						// Now get the first day of week.
						Date datestart =calendar.getTime();
						calendar.add(Calendar.DATE, 6);
						Date dateend = calendar.getTime();
						if (result.size() > 0)
						{
								for( int i=0; i< result.size();i++)
								{
									if (result.get(i).getDate().compareTo(datestart)<0||result.get(i).getDate().compareTo(dateend)>0)
									{
										result.remove(result.get(i));
										i--;
									}
								}
						}
						else if(stateFilter.equals("null") && prioFilter.equals("null")&&tagidFilter==-1)
						{
							for( int i=0; i< listTodo.size();i++) 
							{
								if (listTodo.get(i).getDate().compareTo(datestart)>=0&&listTodo.get(i).getDate().compareTo(dateend)<=0)
								{
									result.add(listTodo.get(i));
								}
							}
						}
					}
					else
					{
						String now= LocalDate.now().toString();
						String[] parts = now.split("-");
						String part0 = parts[0]; 
						String part1 = parts[1]; 
						String part2 = parts[2]; 
						Calendar calendar = Calendar.getInstance(); 
						calendar.clear();
						calendar.set(Integer.parseInt(part0), Integer.parseInt(part1)-1,Integer.parseInt(part2) );       
						int weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
						calendar.clear();
						calendar.set(Calendar.YEAR, Integer.parseInt(part0));
						calendar.set(Calendar.WEEK_OF_YEAR, weekOfYear);
						// Now get the first day of week.
						Date datestart =calendar.getTime();
						calendar.add(Calendar.DATE, 6);
						Date dateend = calendar.getTime();
						if (result.size() > 0)
						{
								for( int i=0; i< result.size();i++)
								{
									if (result.get(i).getDate().compareTo(datestart)<0||result.get(i).getDate().compareTo(dateend)>0)
									{
										result.remove(result.get(i));
										i--;
									}
								}
						}
						else if(stateFilter.equals("null") && prioFilter.equals("null")&&tagidFilter==-1)
						{
							for( int i=0; i< listTodo.size();i++) 
							{
								if (listTodo.get(i).getDate().compareTo(datestart)>=0&&listTodo.get(i).getDate().compareTo(dateend)<=0)
								{
									result.add(listTodo.get(i));
								}
							}
						}
					}
				}

				else if(type.equals("month"))
				{
					if(month.equals("")==false)
					{

						String[] parts = month.split("-");
						String part1 = parts[0]; 
						String part2 = parts[1]; 
							int monthh = Integer.parseInt(part2);
							int year = Integer.parseInt(part1);
							// Get calendar, clear it and set week number and year.
							Calendar calendar = Calendar.getInstance();
							calendar.clear();
							calendar.set(Calendar.MONTH, monthh-1);
							calendar.set(Calendar.YEAR, year);
							// Now get the first day of week.
							Date datestart = calendar.getTime();
							
							if (monthh==2)
							{
								if((year%400==0||(year%4==0&&year%100!=0)))//nÄƒm nhuáº­n
								{
									calendar.add(Calendar.DAY_OF_MONTH,28);
								}
								else
								{
									calendar.add(Calendar.DAY_OF_MONTH,27);
								}
								}
							else if(monthh==1||monthh==3||monthh==5||monthh==7||monthh==8||monthh==10||monthh==12)
							{
								calendar.add(Calendar.DAY_OF_MONTH,30);
							}
							else if(monthh==4||monthh==6||monthh==9||monthh==11)
							{
								calendar.add(Calendar.DAY_OF_MONTH,29);
							}
							Date dateend = calendar.getTime();
							if (result.size() > 0)
							{
									for( int i=0; i< result.size();i++) 
									{
										if (result.get(i).getDate().compareTo(datestart)<0||result.get(i).getDate().compareTo(dateend)>0)
										{
											result.remove(result.get(i));
											i--;
										}
									}
							}
							else if(stateFilter.equals("null") && prioFilter.equals("null")&&tagidFilter==-1)
							{
								for( int i=0; i< listTodo.size();i++) 
								{
									if (listTodo.get(i).getDate().compareTo(datestart)>=0&&listTodo.get(i).getDate().compareTo(dateend)<=0)
									{
										result.add(listTodo.get(i));
									}
								}
							}
					}
					else
					{
						String now= LocalDate.now().toString();
						String[] parts = now.split("-");
						String part0 = parts[0]; 
						String part1 = parts[1]; 
						String part2 = parts[2]; 
						// Get calendar, clear it and set week number and year.
						Calendar calendar = Calendar.getInstance();
						calendar.clear();
						calendar.set(Calendar.MONTH, Integer.parseInt(part1)-1);
						calendar.set(Calendar.YEAR,Integer.parseInt(part0));
						// Now get the first day of week.
						Date datestart = calendar.getTime();
						
						if (Integer.parseInt(part1)==2)
							{
								if((Integer.parseInt(part0)%400==0||(Integer.parseInt(part0)%4==0&&Integer.parseInt(part0)%100!=0)))//nÄƒm nhuáº­n
									calendar.add(Calendar.DAY_OF_MONTH,28);
								else
									calendar.add(Calendar.DAY_OF_MONTH,27);
							}
							else if(Integer.parseInt(part1)==1||Integer.parseInt(part1)==3||Integer.parseInt(part1)==5||Integer.parseInt(part1)==7||Integer.parseInt(part1)==8||Integer.parseInt(part1)==10||Integer.parseInt(part1)==12)
							{
								calendar.add(Calendar.DAY_OF_MONTH,30);
							}
							else if(Integer.parseInt(part1)==4||Integer.parseInt(part1)==6||Integer.parseInt(part1)==9||Integer.parseInt(part1)==11)
							{
								calendar.add(Calendar.DAY_OF_MONTH,29);
							}
							Date dateend = calendar.getTime();
							if (result.size() > 0)
							{
									for( int i=0; i< result.size();i++) 
									{
										if (result.get(i).getDate().compareTo(datestart)<0||result.get(i).getDate().compareTo(dateend)>0)
										{
											result.remove(result.get(i));
											i--;
										}
									}
							}
							else if(stateFilter.equals("null") && prioFilter.equals("null")&&tagidFilter==-1)
							{
								for( int i=0; i< listTodo.size();i++) 
								{
									if (listTodo.get(i).getDate().compareTo(datestart)>=0&&listTodo.get(i).getDate().compareTo(dateend)<=0)
									{
										result.add(listTodo.get(i));
									}
								}
							}
							
					}
				}
				
			//}
			/////////////////////////////////Sort/////////////////////////////
			try {
				if(sort.equals("Sort")) {
					
					List<Todo> high=new ArrayList<Todo>();
					
					List<Todo> medium=new ArrayList<Todo>();
					
					List<Todo> low=new ArrayList<Todo>();
					
					List<Todo> highDone=new ArrayList<Todo>();
					
					List<Todo> mediumDone=new ArrayList<Todo>();
					
					List<Todo> lowDone=new ArrayList<Todo>();
					for(int i=0; i< result.size();i++) {
						if(result.get(i).getPriority().equals("High")) {
							if(result.get(i).getDone()==false)
							{
								high.add(result.get(i));
							}
							else
							{
								highDone.add(result.get(i));
							}
						}
						else if(result.get(i).getPriority().equals("Medium")) {
							if(result.get(i).getDone()==false)
							{
								medium.add(result.get(i));
							}
							else
							{
								mediumDone.add(result.get(i));
							}
						}
						else if(result.get(i).getPriority().equals("Low")) {
							if(result.get(i).getDone()==false)
							{
								low.add(result.get(i));
							}
							else
							{
								lowDone.add(result.get(i));
							}
						}
					}
					List<Todo> resultSort=new ArrayList<Todo>();
					for(int i = 0; i<(high.size()+medium.size()); i++)
					{
						   if(i<high.size())
							   resultSort.add(high.get(i));
						   else
							   resultSort.add(medium.get(i-high.size()));
					}
					int length=resultSort.size()+low.size();
					for(int i = resultSort.size(); i<length; i++)
					{
							resultSort.add(low.get(i-resultSort.size()));
					}
					length=resultSort.size()+highDone.size();
					for(int i = resultSort.size(); i<length; i++)
					{
							resultSort.add(highDone.get(i-resultSort.size()));
					}
					length=resultSort.size()+mediumDone.size();
					for(int i = resultSort.size(); i<length; i++)
					{
							resultSort.add(mediumDone.get(i-resultSort.size()));
					}
					length=resultSort.size()+lowDone.size();
					for(int i = resultSort.size(); i<length; i++)
					{
							resultSort.add(lowDone.get(i-resultSort.size()));
					}
					request.setAttribute("listTodo", resultSort);
					request.setAttribute("listTag", listTag);
				}
				else
				{
					request.setAttribute("listTodo", result);
					request.setAttribute("listTag", listTag);
				}
				
			}
			catch(Exception e) {
				
				request.setAttribute("listTodo", result);
				request.setAttribute("listTag", listTag);
			}
			if(type.equals("week"))
			{
				RequestDispatcher dispatcher = request.getRequestDispatcher("todoweek.jsp");
				dispatcher.forward(request, response);
			}
			else if(type.equals("date"))
			{
				RequestDispatcher dispatcher = request.getRequestDispatcher("tododay.jsp");
				dispatcher.forward(request, response);
			}
			else if(type.equals("month"))
			{
				RequestDispatcher dispatcher = request.getRequestDispatcher("todomonth.jsp");
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


