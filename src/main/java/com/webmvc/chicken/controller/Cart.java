package com.webmvc.chicken.controller;

import com.webmvc.chicken.dao.ViewProductDB;
import com.webmvc.chicken.model.CartEntity;
import com.webmvc.chicken.model.LineItemEntity;
import com.webmvc.chicken.model.ViewProductEntity;
import com.webmvc.chicken.utils.MyUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Cart", value = "/cart")
public class Cart extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        String url = "/cart.jsp";
        String action = req.getParameter("action");

        if (action == null) {
            action = "details";
        }
        if (action.trim().equalsIgnoreCase("details")) {
            url = this.details(req);
        } else if (action.equalsIgnoreCase("addToCart")) {
            url = this.addToDetails(req);
        } else if (action.equalsIgnoreCase("update")) {
            url = this.update(req);
        }

        getServletContext().getRequestDispatcher(url).forward(req, resp);
    }

    private String addToDetails(HttpServletRequest req) {
        String url = "";
        String productCode = req.getParameter("productCode");

        ViewProductEntity product = ViewProductDB.getProductByCode(productCode);
        LineItemEntity lineItemEntity = new LineItemEntity();
        lineItemEntity.setProduct(product);

        HttpSession session = req.getSession();
        CartEntity cart = (CartEntity) session.getAttribute("cart");

        if (cart == null) {
            cart = new CartEntity();
            lineItemEntity.setQuantity(1);
        } else {
            if (cart.getItemByProductCode(productCode) == null) {
                lineItemEntity.setQuantity(1);
            } else {
                lineItemEntity.setQuantity(cart.getItemByProductCode(productCode).getQuantity() + 1);
            }
        }

        cart.addItem(lineItemEntity);
        session.setAttribute("cart", cart);
        req.setAttribute("cart", cart);
        url = "/cart.jsp";
        return url;
    }

    private String update(HttpServletRequest req) {
        String url = "";
        String productCode = req.getParameter("productCode");
        String quantityString = req.getParameter("quantity");

        HttpSession session = req.getSession();
        CartEntity cart = (CartEntity) session.getAttribute("cart");
        if (cart == null) {
            cart = new CartEntity();
        }
        int quantity;
        if (MyUtil.isInteger(quantityString)) {
            quantity = Integer.parseInt(quantityString);
            if (quantity < 0) {
                quantity = 1;
            }
        } else {
            quantity = 1;
        }

        ViewProductEntity product = ViewProductDB.getProductByCode(productCode);
        LineItemEntity lineItem = new LineItemEntity();
        lineItem.setProduct(product);
        lineItem.setQuantity(quantity);
        if (quantity > 0) {
            cart.addItem(lineItem);
        } else {
            cart.removeItem(lineItem);
        }

        session.setAttribute("cart", cart);
        req.setAttribute("cart", cart);
        url = "/cart.jsp";
        return url;
    }

    private String details(HttpServletRequest req) {
        String url;
        HttpSession session = req.getSession();
        CartEntity cart = (CartEntity) session.getAttribute("cart");
        if (cart == null){
            cart = new CartEntity();
        }
        session.setAttribute("cart", cart);
        req.setAttribute("cart", cart);
        url = "/cart.jsp";
        return url;
    }

}
