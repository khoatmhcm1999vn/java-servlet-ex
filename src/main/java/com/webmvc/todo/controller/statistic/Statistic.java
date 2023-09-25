package com.webmvc.todo.controller.statistic;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Calendar;
import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import com.webmvc.todo.dao.TodoDao;
import com.webmvc.todo.model.Tag;
import com.webmvc.todo.model.User;

@WebServlet("/statistic")
public class Statistic extends HttpServlet {
	private static final long serialVersionUID = 1L;

    TodoDao todoDao = null;

    public Statistic() {
        super();
        todoDao = new TodoDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
		if(user!=null) {
			System.out.println(user.getFullname());

	        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	        /* =========== Daily Statistic ============= */
	        Date dateFilter = new Date();
	        if(request.getParameter("dateStatistic") != null) {
	            String date_str = request.getParameter("dateStatistic");
	            request.setAttribute("date", date_str);
	            try {
	                dateFilter = df.parse(date_str);
	            } catch (ParseException e) {
	                e.printStackTrace();
	            }
	        }

	        List<Object[]> todos = todoDao.statisticTodoGroupByTag(user, dateFilter);
	        List<Object[]> dones = todoDao.statisticTodoDone(user, dateFilter);

	        float total = 0;
	        float done = 0;
	        for(Object[] obj : dones) {
	            Long amount = (Long)obj[1];
	            total = total + amount;
	            if ((boolean)obj[0] == true) {
	                done = (Long)obj[1];
	            }
	        }
	        JsonArray json = new JsonArray();
	        JsonArray jsonArray = new JsonArray();
	        todos.forEach(item -> {
	            JsonObject formDetailsJson = new JsonObject();
	            Tag tag = (Tag)item[0];
	            formDetailsJson.addProperty("Name", tag.getTagname());
	            formDetailsJson.addProperty("Amount", (Number)item[1]);

	            jsonArray.add(formDetailsJson);
	        });
	        json.addAll(jsonArray);
	        System.out.println(jsonArray);
	        request.setAttribute("countTodos", json);
	        request.setAttribute("percentDone", Math.round((done/total)*100));


	        /* =========== Weekly Statistic ============= */
	        /* User user = (User)session.getAttribute("user"); */
	        Calendar cal = Calendar.getInstance();

	        String week_str = "";
	        int year = 0;
	        int week =  0;

	        if (request.getParameter("weekStatistic") != null) {
	            week_str = request.getParameter("weekStatistic");
	            year = Integer.parseInt(week_str.substring(0, 4));
	            week =  Integer.parseInt(week_str.substring(6, 8));
	            request.setAttribute("week", week_str);

	            if (week == 53) {
	                cal.set(Calendar.WEEK_OF_YEAR, 1);
	            } else {
	                cal.set(Calendar.WEEK_OF_YEAR, week + 1);
	            }

	        } else {
	            year = cal.get(Calendar.YEAR);
	            week = cal.get(Calendar.WEEK_OF_YEAR);
	            cal.set(Calendar.WEEK_OF_YEAR, week);
	        }

	        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

	        String dbegin_str = df.format(cal.getTime());

	        int mn = Integer.parseInt(dbegin_str.substring(5, 7));
	        int dd = Integer.parseInt(dbegin_str.substring(8, 10));
	        System.out.println("Month: " + mn);
	        System.out.println("Date: " + dd);

	        cal.set(year, mn - 1, dd);

	        List<Date> dates = new ArrayList<Date>();

	        dates.add(cal.getTime());
	        for (int i=1; i<7; i++) {
	            cal.add(Calendar.DATE, 1);
	            dates.add(cal.getTime());
	        }
	        List<Object[]> weektodos = todoDao.statisticWeekly(user, dates.get(0), dates.get(6));

	        if (!weektodos.isEmpty()) {
	            List<Integer> listDone = new ArrayList<Integer>();
	            listDone.add(0);
	            listDone.add(0);
	            listDone.add(0);
	            listDone.add(0);
	            listDone.add(0);
	            listDone.add(0);
	            listDone.add(0);

	            List<Integer> listNotDone = new ArrayList<Integer>();
	            listNotDone.add(0);
	            listNotDone.add(0);
	            listNotDone.add(0);
	            listNotDone.add(0);
	            listNotDone.add(0);
	            listNotDone.add(0);
	            listNotDone.add(0);

	            weektodos.forEach(item -> {
	                System.out.println("----------------Vong lap");
	                System.out.println(item[0].toString());
	                System.out.println(item[1].toString());
	                System.out.println(item[2].toString());
	                System.out.println("----------------Vong lap");
	                Date d = (Date)item[0];
	                Boolean b = (Boolean)item[1];
	                System.out.println(d.getClass().getName());
	                System.out.println(item[0].getClass().getName());
	                System.out.println(b.getClass().getName());
	                System.out.println(item[1].getClass().getName());

	                System.out.println(item[2].getClass().getName());

	                for(int i=0; i<7; i++) {
	                    System.out.println(df.format(dates.get(i)));
	                    System.out.println(d.toString());
	                    System.out.println(b);
	                    System.out.println(d.toString().equals(df.format(dates.get(i))));

	                    if(d.toString().equals(df.format(dates.get(i))) && b.booleanValue() == true) {
	                        System.out.println("if 1");
	                        /*Number num = (Number)item[2];

	                        System.out.println(num.getClass().getName());
	                        int numi = num.intValue();
	                        System.out.println(numi.getClass().getName());*/

	                        listDone.set(i, ((Long)item[2]).intValue());

	                        System.out.println("Thoat");
	                        break;
	                    } else if(d.toString().equals(df.format(dates.get(i))) && b.booleanValue() == false) {
	                        System.out.println("if 2");
	                        /*Number num = (Number)item[2];
	                        int numi = num.intValue();*/

	                        listNotDone.set(i, ((Long)item[2]).intValue());
	                        System.out.println(listNotDone.get(i));
	                        System.out.println("Thoat");
	                        break;
	                    }
	                }
	            });

	            List<Integer> listTotal = new ArrayList<Integer>();
	            listTotal.add(listDone.get(0) + listNotDone.get(0));
	            listTotal.add(listDone.get(1) + listNotDone.get(1));
	            listTotal.add(listDone.get(2) + listNotDone.get(2));
	            listTotal.add(listDone.get(3) + listNotDone.get(3));
	            listTotal.add(listDone.get(4) + listNotDone.get(4));
	            listTotal.add(listDone.get(5) + listNotDone.get(5));
	            listTotal.add(listDone.get(6) + listNotDone.get(6));

	            System.out.println("List Done");
	            System.out.println(listDone.get(0));
	            System.out.println(listDone.get(1));
	            System.out.println(listDone.get(2));
	            System.out.println(listDone.get(3));
	            System.out.println(listDone.get(4));
	            System.out.println(listDone.get(5));
	            System.out.println(listDone.get(6));
	            System.out.println("========================");

	            System.out.println("List Not Done");
	            System.out.println(listNotDone.get(0));
	            System.out.println(listNotDone.get(1));
	            System.out.println(listNotDone.get(2));
	            System.out.println(listNotDone.get(3));
	            System.out.println(listNotDone.get(4));
	            System.out.println(listNotDone.get(5));
	            System.out.println(listNotDone.get(6));
	            System.out.println("========================");

	            System.out.println("List Total");
	            System.out.println(listTotal.get(0));
	            System.out.println(listTotal.get(1));
	            System.out.println(listTotal.get(2));
	            System.out.println(listTotal.get(3));
	            System.out.println(listTotal.get(4));
	            System.out.println(listTotal.get(5));
	            System.out.println(listTotal.get(6));
	            System.out.println("========================");



	            if (listTotal.get(0) != 0) {

	                System.out.println("//////////////////////////////////////////////////////////");
	                System.out.println(listDone.get(0)*100/listTotal.get(0));
	                request.setAttribute("sun", Math.round(listDone.get(0)*100/listTotal.get(0)));
	            } else {
	                request.setAttribute("sun", 0);
	            }

	            if (listTotal.get(1) != 0) {
	                System.out.println("//////////////////////////////////////////////////////////");
	                System.out.println(listDone.get(1)*100/listTotal.get(1));
	                request.setAttribute("mon", Math.round(listDone.get(1)*100/listTotal.get(1)));

	            } else {
	                request.setAttribute("mon", 0);
	            }

	            if (listTotal.get(2) != 0) {
	                System.out.println("//////////////////////////////////////////////////////////");
	                System.out.println(listDone.get(2)*100/listTotal.get(2));
	                request.setAttribute("tue", Math.round(listDone.get(2)*100/listTotal.get(2)));
	            } else {
	                request.setAttribute("tue", 0);
	            }

	            if (listTotal.get(3) != 0) {
	                System.out.println("//////////////////////////////////////////////////////////");
	                System.out.println(listDone.get(3)*100/listTotal.get(3));
	                request.setAttribute("wed", Math.round(listDone.get(3)*100/listTotal.get(3)));
	            } else {
	                request.setAttribute("wed", 0);
	            }

	            if (listTotal.get(4) != 0) {
	                System.out.println("//////////////////////////////////////////////////////////");
	                System.out.println(listDone.get(4)*100/listTotal.get(4));
	                request.setAttribute("thu", Math.round(listDone.get(4)*100/listTotal.get(4)));

	            } else {
	                request.setAttribute("thu", 0);
	            }

	            if (listTotal.get(5) != 0) {

	                System.out.println("//////////////////////////////////////////////////////////");
	                System.out.println(listDone.get(5)*100/listTotal.get(5));
	                request.setAttribute("fri", Math.round(listDone.get(5)*100/listTotal.get(5)));
	            } else {
	                request.setAttribute("fri", 0);
	            }

	            if (listTotal.get(6) != 0) {

	                System.out.println("//////////////////////////////////////////////////////////");
	                System.out.println(listDone.get(6)*100/listTotal.get(6));
	                request.setAttribute("sat", Math.round(listDone.get(6)*100/listTotal.get(6)));
	            } else {
	                request.setAttribute("sat", 0);
	            }

	        } else {
	            request.setAttribute("sun", 0);
	            request.setAttribute("mon", 0);
	            request.setAttribute("tue", 0);
	            request.setAttribute("wed", 0);
	            request.setAttribute("thu", 0);
	            request.setAttribute("fri", 0);
	            request.setAttribute("sat", 0);
	        }

	        SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yyyy");
	        System.out.println(df2.format(dates.get(0)));
	        System.out.println(df2.format(dates.get(6)));

	        request.setAttribute("beginWeek", df2.format(dates.get(0)));
	        request.setAttribute("endWeek", df2.format(dates.get(6)));

	        RequestDispatcher dispatcher = request.getRequestDispatcher("statistic.jsp");
	        dispatcher.forward(request, response);
		}else {
			System.out.println("Nguoi dung null");
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
	        dispatcher.forward(request, response);
		}
    }
}
