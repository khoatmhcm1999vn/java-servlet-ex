package com.webmvc.chicken.controller;

import com.webmvc.chicken.dao.CustomerDB;
import com.webmvc.chicken.model.CustomerEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Sig", value = "/sig")
public class Sig extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        String url = "/login.jsp";
        String action = req.getParameter("action");
        String status = "";
        if (action == null) {
            url = "/login.jsp";
        } else if (action.equalsIgnoreCase("login")) {
            HttpSession httpSession = req.getSession();
            CustomerEntity userSession = (CustomerEntity) httpSession.getAttribute("customer");
            if (userSession == null) {
                String email = req.getParameter("email");
                String passwd = req.getParameter("passwd");
                CustomerEntity customer = CustomerDB.getCustomerByEmailAndPasswd(email, passwd);
                if (customer != null) {
                    httpSession.setAttribute("customer", customer);
                    url = "/index.jsp";
                    System.out.println("đăng nhập thành công");
                } else {
                    url = "/login.jsp";
                    status = "sai email hoặc mật khẩu";
                    req.setAttribute("status", status);
                }
            } else {
                url = "/report.jsp";
                status = "bạn đã đăng nhập rồi";
                req.setAttribute("status", status);
            }
        } else if (action.equalsIgnoreCase("logout")) {
            HttpSession session = req.getSession();
            session.removeAttribute("customer");
            session.removeAttribute("cart");
            url = "/index.jsp";
        }

        getServletContext().getRequestDispatcher(url).forward(req, resp);
    }

}
