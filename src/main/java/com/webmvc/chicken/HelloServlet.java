package com.webmvc.chicken;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {

    private String message;

    @Override
    public void init() {
        this.message = "Hello World!";
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        // Hello
        try (PrintWriter out = response.getWriter()) {
            out.println("<html><body>");
            out.println("<h1>" + this.message + "</h1>");
            out.println("</body></html>");
        }
    }

    @Override
    public void destroy() {

    }

}
