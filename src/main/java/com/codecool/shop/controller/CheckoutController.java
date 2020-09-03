package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.dao.implementation.UserDaoMem;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.User;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet(urlPatterns = {"/checkout"})
public class CheckoutController extends HttpServlet {
    String userIdStr;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDao userDataStore = UserDaoMem.getInstance();

        UUID userId = UUID.fromString(userIdStr);
        User currentUser = userDataStore.find(userId);
        Order currentOrder = currentUser.getLastOrder();

        String fullName = req.getParameter("firstname");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");

        String countryBilling = req.getParameter("country-billing");
        String cityBilling = req.getParameter("city-billing");
        String zipBilling = req.getParameter("zip-billing");
        String addressBilling = req.getParameter("address-billing");

        String countryShipping = req.getParameter("country-shipping");
        String cityShipping = req.getParameter("city-shipping");
        String zipShipping = req.getParameter("zip-shipping");
        String addressShipping = req.getParameter("address-shipping");


        if (countryShipping.equals("")){
            countryShipping = countryBilling;
            cityShipping = cityBilling;
            zipShipping = zipBilling;
            addressShipping = addressBilling;
        }

        currentUser.setShippingAddress(countryShipping + " " + cityShipping + " " + zipShipping + " " + addressShipping);



        currentOrder.addOrderDetail("fullName",fullName);
        currentOrder.addOrderDetail("email",email);
        currentOrder.addOrderDetail("phone",phone);

        currentOrder.addOrderDetail("countryBilling",countryBilling);
        currentOrder.addOrderDetail("cityBilling",cityBilling);
        currentOrder.addOrderDetail("zipBilling",zipBilling);
        currentOrder.addOrderDetail("addressBilling",addressBilling);

        currentOrder.addOrderDetail("countryShipping",countryShipping);
        currentOrder.addOrderDetail("cityShipping",cityShipping);
        currentOrder.addOrderDetail("zipShipping",zipShipping);
        currentOrder.addOrderDetail("addressShipping",addressShipping);

        String paymentUrl = "/payment?userid=" + userIdStr;
        resp.sendRedirect(paymentUrl);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userIdStr = req.getParameter("userid");

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        engine.process("product/checkout.html", context, resp.getWriter());
    }

}



