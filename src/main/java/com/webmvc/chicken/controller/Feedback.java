package com.webmvc.chicken.controller;

import com.webmvc.chicken.dao.FeedBackDB;
import com.webmvc.chicken.model.FeedbackEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Feedback", value = "/feedback")
public class Feedback extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        String status = "";
        String url = "/contact.jsp";
        String email = req.getParameter("email");
        String name = req.getParameter("name");
        String subject = req.getParameter("subject");
        String message = req.getParameter("message");
        if (email == null || email.trim().equalsIgnoreCase("")) {
            status = "điền thiếu mail";
        } else if (name == null || name.trim().equalsIgnoreCase("")) {
            status = "điền thiếu name";
        } else {
            FeedbackEntity feedback = new FeedbackEntity();
            feedback.setEmail(email);
            feedback.setName(name);
            feedback.setSubject(subject);
            feedback.setMessage(message);
            FeedBackDB.insert(feedback);
            status = "d-block";
        }
        req.setAttribute("status", status);
        getServletContext().getRequestDispatcher(url).forward(req, resp);
    }

}
