package com.webmvc.chicken.controller;

import com.webmvc.chicken.dao.AddressDB;
import com.webmvc.chicken.model.AddressEntity;
import com.webmvc.chicken.model.CartEntity;
import com.webmvc.chicken.model.CustomerEntity;
import com.webmvc.chicken.utils.MyUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.lang.Integer.parseInt;

@WebServlet(name = "Address", value = "/address")
public class Address extends HttpServlet {

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
        String url = "/address.jsp";
        String status = "";

        CustomerEntity customer = (CustomerEntity) httpSession.getAttribute("customer");
        if (customer == null) {
            status = "lỗi hệ thống";
            url = "/address.jsp";
        } else {
            String action = req.getParameter("action");
            if (action == null || action.equalsIgnoreCase("")) {
                action = "address";
            }
            if (action.equalsIgnoreCase("address")) {
                AddressEntity address = AddressDB.getAddressByCustomer(customer);
                req.setAttribute("address", address);
                url = "/address.jsp";
            } else if (action.equalsIgnoreCase("update")) {
                String name = req.getParameter("name");
                String phone = req.getParameter("phone");
                String province = req.getParameter("province");
                String district = req.getParameter("district");
                String commune = req.getParameter("commune");
                String details = req.getParameter("details");

                if (name == null || phone == null || province == null || district == null || commune == null || details == null ||
                        name.equalsIgnoreCase("") || phone.equalsIgnoreCase("") ||
                        province.equalsIgnoreCase("") || district.equalsIgnoreCase("") ||
                        commune.equalsIgnoreCase("") || details.equalsIgnoreCase("")) {
                    status = "bạn cần điền đủ thông tin";
                    url = "/address.jsp";
                } else if (!MyUtil.isInteger(phone)) {
                    status = "bạn nhập sai định dang số điện thoại";
                    url = "/address.jsp";
                } else {
                    AddressEntity address = AddressDB.getAddressByCustomer(customer);
                    if (address == null) {
                        address = new AddressEntity();
                        address.setAddressName(name);
                        address.setPhone(parseInt(phone));
                        address.setProvince(province);
                        address.setDistrict(district);
                        address.setCommune(commune);
                        address.setDetails(details);
                        address.setCustomerId(customer);
                        AddressDB.insert(address);
                    } else {
                        address.setAddressName(name);
                        address.setPhone(parseInt(phone));
                        address.setProvince(province);
                        address.setDistrict(district);
                        address.setCommune(commune);
                        address.setDetails(details);
                        AddressDB.update(address);
                    }

                    String state = req.getParameter("state");
                    if (state == null) {
                        state = "info";
                    }
                    if (state.equalsIgnoreCase("info")) {
                        req.setAttribute("user", customer);
                        req.setAttribute("address", address);
                        url = "/info.jsp";
                        status = "đổi địa chỉ giao hàng thành công";
                    } else if (state.equalsIgnoreCase("bill")) {
                        req.setAttribute("address", address);
                        CartEntity cart = (CartEntity) httpSession.getAttribute("cart");
                        req.setAttribute("cart", cart);
                        url = "/bill.jsp";
                        status = "đổi địa chỉ giao hàng thành công";
                    }
                }
            }
        }
        String state = req.getParameter("state");
        req.setAttribute("state", state);
        req.setAttribute("status", status);
        getServletContext().getRequestDispatcher(url).forward(req, resp);
    }

}
