package com.webmvc.chicken.controller;

import com.webmvc.chicken.dao.*;
import com.webmvc.chicken.model.*;
import com.webmvc.chicken.utils.MyUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet(name = "CheckOut", value = "/check-out")
public class CheckOut extends HttpServlet {

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
        String url;
        String action = req.getParameter("action");
        String status = "";
        CustomerEntity customer = (CustomerEntity) httpSession.getAttribute("customer");
        if (customer == null) {
            url = "/report.jsp";
            status = "bạn chưa đăng nhập";
        } else {
            if (action == null) {
                action = "checkOut";
            }
            switch (action) {
                case "checkOut": {
                    AddressEntity address = AddressDB.getAddressByCustomer(customer);
                    CartEntity cart = (CartEntity) httpSession.getAttribute("cart");
                    req.setAttribute("address", address);
                    req.setAttribute("cart", cart);
                    url = "/bill.jsp";
                    break;
                }
                case "checkCode": {
                    String code = req.getParameter("code");
                    DiscountEntity discount = DiscountDB.getDiscountByCode(code);
                    if (discount == null) {
                        CartEntity cart = (CartEntity) httpSession.getAttribute("cart");
                        cart.setDiscountCode("Mã không hợp lệ");
                        cart.setDiscountValue(null);
                        req.setAttribute("cart", cart);
                        AddressEntity address = AddressDB.getAddressByCustomer(customer);
                        req.setAttribute("address", address);
                    } else {
                        long millis = System.currentTimeMillis();
                        Date toDate = new Date(millis);
                        CartEntity cart = (CartEntity) httpSession.getAttribute("cart");
                        if (toDate.getTime() > discount.getBeginDiscount().getTime() && toDate.getTime() < discount.getEndDiscount().getTime()) {
                            cart.setDiscountCode(discount.getDiscountCode());
                            cart.setDiscountValue(discount.getDiscountValue());
                            req.setAttribute("cart", cart);
                            AddressEntity address = AddressDB.getAddressByCustomer(customer);
                            req.setAttribute("address", address);
                        } else {
                            cart.setDiscountCode("Mã hết hạn");
                            cart.setDiscountValue(null);
                            req.setAttribute("cart", cart);
                            AddressEntity address = AddressDB.getAddressByCustomer(customer);
                            req.setAttribute("address", address);
                        }
                    }
                    url = "/bill.jsp";
                    break;
                }
                case "final": {
                    CartEntity cart = (CartEntity) httpSession.getAttribute("cart");
                    BillEntity bill = new BillEntity();
                    bill.setBillId(MyUtil.createBillCode(customer));
                    AddressEntity address = AddressDB.getAddressByCustomer(customer);
                    bill.setAddressByAddressId(address);
                    Date toDate = Date.valueOf(MyUtil.sqlDate());
                    bill.setInvoiceDate(toDate);
                    bill.setProcessed((byte) 0);
                    bill.setCustomer(customer);

                    if (cart.getDiscountValue() != 0.0) {
                        bill.setDiscount(DiscountDB.getDiscountByCode(cart.getDiscountCode()));
                    }
                    BillDB.insert(bill);

                    List<LineItemEntity> listLineItem = cart.getItems();
                    for (LineItemEntity lineItemEntity : listLineItem) {
                        BillItemEntity item = new BillItemEntity();
                        item.setBillId(bill);
                        item.setQuantity(lineItemEntity.getQuantity());
                        item.setProductId(ProductDB.getProductByCode(lineItemEntity.getProduct().getProductCode()));
                        BillItemDB.insert(item);
                    }
                    httpSession.removeAttribute("cart");
                    url = "/report.jsp";
                    status = "cảm ơn bạn đã sử dụng dịch vụ của chúng tôi";
                    break;
                }
                default: {
                    throw new IOException("Unexpected value: " + action);
                }
            }
        }

        req.setAttribute("status", status);
        getServletContext().getRequestDispatcher(url).forward(req, resp);
    }

}
