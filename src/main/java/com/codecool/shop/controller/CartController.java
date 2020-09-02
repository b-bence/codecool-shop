package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.dao.implementation.UserDaoMem;
import com.codecool.shop.model.LineItem;
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
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@WebServlet(urlPatterns = {"/cart"})
public class CartController extends HttpServlet {

//    private Optional<Order> order = Optional.empty();

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
//    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        ProductDao productDataStore = ProductDaoMem.getInstance();
//        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        UserDao userDataStore = UserDaoMem.getInstance();
        OrderDao orderDataStore = OrderDaoMem.getInstance();
        String userIdStr = req.getParameter("userid");
        Map<LineItem, Integer> lineItemList = new HashMap<>();
        if (userIdStr != null) {
            UUID userId = UUID.fromString(userIdStr);
            User currentUser = userDataStore.find(userId);
            Order currentOrder = currentUser.getLastOrder();
            lineItemList = currentOrder.getLineItems();
        }

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("lineitems", lineItemList);
//        context.setVariable("category", productCategoryDataStore.find(1));
//        context.setVariable("products", productDataStore.getBy(productCategoryDataStore.find(1)));

        engine.process("product/cart.html", context, resp.getWriter());
    }

}


