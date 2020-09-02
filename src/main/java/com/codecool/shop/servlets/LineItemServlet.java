package com.codecool.shop.servlets;


import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.*;
import com.codecool.shop.util.Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "LineItemServlet", urlPatterns = "/api/add-new-line-item", loadOnStartup = 2)
public class LineItemServlet extends HttpServlet {

    private Optional<User> user = Optional.empty();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //response.setContentType("application/json");
        //response.setCharacterEncoding("UTF-8");
        Order order;
        int productId = Integer.parseInt(request.getParameter("id"));

        if(user.isEmpty()){
            user = Optional.of(new User());
            user.get().createOrder();
        }

        if(user.get().isThereAnActiveOrder()){
            order = user.get().getLastOrder();
        }else {
            order = new Order(); // If a guest wants to make another order.
        }

        Product product = ProductDaoMem.getInstance().find(productId);
        LineItem lineItem = new LineItem(productId, product.getName(), product.getDefaultPrice(), product.getDefaultCurrency());
        order.addlineItem(lineItem);

        String result = "{'status':'ok'}";

        PrintWriter out = response.getWriter();
        out.println("ok:status");
        out.flush();
    }

}