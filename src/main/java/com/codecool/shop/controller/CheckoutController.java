package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/checkout"})
public class CheckoutController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OrderDao orderDataStore = OrderDaoMem.getInstance();

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
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        engine.process("product/checkout.html", context, resp.getWriter());
    }

}



