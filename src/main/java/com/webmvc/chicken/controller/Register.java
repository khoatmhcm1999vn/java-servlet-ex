package com.webmvc.chicken.controller;

import com.webmvc.chicken.dao.CustomerDB;
import com.webmvc.chicken.model.CustomerEntity;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Register", value = "/register")
public class Register extends HttpServlet {

    private String host;

    private String port;

    private String username;

    private String pass;

    @Override
    public void init() {
        // reads SMTP server setting from web.xml file
        ServletContext context = getServletContext();
        this.host = context.getInitParameter("host");
        this.port = context.getInitParameter("port");
        this.username = context.getInitParameter("username");
        this.pass = context.getInitParameter("pass");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        String url = "/register.jsp";
        String action = req.getParameter("action");
        String status = "";
        HttpSession httpSession = req.getSession();
        if (action == null) {
            action = "index";
        }
        if (action.equalsIgnoreCase("index")) {
            url = "/register.jsp";
        } else if (action.equalsIgnoreCase("register")) {
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String passwd = req.getParameter("passwd");
            String rePasswd = req.getParameter("rePasswd");

            if (name == null || email == null || passwd == null || rePasswd == null ||
                name.equalsIgnoreCase("") || email.equalsIgnoreCase("") || passwd.equalsIgnoreCase("") ||
                rePasswd.equalsIgnoreCase("")) {
                url = "/register.jsp";
                status = "bạn nhập thiếu thông tin";
            } else if (!passwd.equalsIgnoreCase(rePasswd)) {
                url = "/register.jsp";
                status = "mật khẩu không khớp";
            } else if (CustomerDB.getCustomerByEmail(email) != null) {
                url = "/register.jsp";
                status = "email này đã được đăng ký";
                req.setAttribute("status", status);
            } else {
                CustomerEntity customer = new CustomerEntity();
                customer.setName(name);
                customer.setMail(email);
                customer.setPasswd(passwd);
                url = "/index.jsp";
                CustomerDB.insert(customer);
                httpSession.setAttribute("customer", customer);
                status = "Đăng ký thành công";
            }
        }

        req.setAttribute("status", status);
        getServletContext().getRequestDispatcher(url).forward(req, resp);
    }

}
