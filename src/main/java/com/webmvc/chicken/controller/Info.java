package com.webmvc.chicken.controller;

import com.webmvc.chicken.dao.AddressDB;
import com.webmvc.chicken.dao.CustomerDB;
import com.webmvc.chicken.model.AddressEntity;
import com.webmvc.chicken.model.CustomerEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Info", value = "/info")
public class Info extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        HttpSession httpSession = req.getSession();
        String url = "/login.jsp";
        String status = "";
        CustomerEntity userSession = (CustomerEntity) httpSession.getAttribute("customer");
        String action = req.getParameter("action");
        if (action == null) {
            action = "info";
        }
        if (action.equalsIgnoreCase("info")) {
            if (userSession == null) {
                url = "/login.jsp";
                status = "bạn cần đăng nhập";
            } else {
                int id = userSession.getCustomerId();
                AddressEntity address = AddressDB.getAddressByCustomerId(id);
                String name = req.getParameter("name");
                String email = req.getParameter("email");
                if (name == null || email == null || name.equalsIgnoreCase("") || email.equalsIgnoreCase("")) {
                    req.setAttribute("user", userSession);
                    req.setAttribute("address", address);
                    url = "/info.jsp";
                    status = "bạn điền thiếu thông tin ";
                } else {
                    userSession.setName(name);
                    userSession.setMail(email);
                    CustomerDB.update(userSession);
                    httpSession.setAttribute("customer", userSession);
                    req.setAttribute("user", userSession);
                    req.setAttribute("address", address);
                    url = "/info.jsp";
                }
            }
        } else if (action.equalsIgnoreCase("update")) {
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            if (name == null || email == null || name.equalsIgnoreCase("") || email.equalsIgnoreCase("")) {
                int id = userSession.getCustomerId();
                AddressEntity address = AddressDB.getAddressByCustomerId(id);
                req.setAttribute("user", userSession);
                req.setAttribute("address", address);
            } else {
                userSession.setName(name);
                userSession.setMail(email);
                CustomerDB.update(userSession);
                httpSession.setAttribute("customer", userSession);

                int id = userSession.getCustomerId();
                AddressEntity address = AddressDB.getAddressByCustomerId(id);

                req.setAttribute("user", userSession);
                req.setAttribute("address", address);
            }
            url = "/info.jsp";
        }

        req.setAttribute("status", status);
        getServletContext().getRequestDispatcher(url).forward(req, resp);
    }

}
