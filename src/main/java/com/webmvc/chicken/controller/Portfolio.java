package com.webmvc.chicken.controller;

import com.webmvc.chicken.dao.CategoriesDB;
import com.webmvc.chicken.dao.ViewProductDB;
import com.webmvc.chicken.model.CategoriesEntity;
import com.webmvc.chicken.model.ViewProductEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Portfolio", value = "/portfolio")
public class Portfolio extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        List<CategoriesEntity> categoriesList = CategoriesDB.getListCategory();
        List<ViewProductEntity> productList = ViewProductDB.getListProduct();
        req.setAttribute("categoriesList", categoriesList);
        req.setAttribute("productList", productList);
        String url = "/portfolio.jsp";
        getServletContext().getRequestDispatcher(url).forward(req, resp);
    }

}
