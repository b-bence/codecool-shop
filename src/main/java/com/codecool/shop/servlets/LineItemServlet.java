package com.codecool.shop.servlets;


import com.codecool.shop.dao.UserDao;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.UserDaoMem;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet(name = "LineItemServlet", urlPatterns = "/api/add-new-line-item", loadOnStartup = 2)
public class LineItemServlet extends HttpServlet {

    private Optional<User> user = Optional.empty();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDataStore = UserDaoMem.getInstance();
        Order order;
        int productId = Integer.parseInt(request.getParameter("id"));

        if (user.isEmpty()) {
            user = Optional.of(new User());
            user.get().createOrder();
            userDataStore.add(user.get());
        }

        if (user.get().isThereAnActiveOrder()) {
            order = user.get().getLastOrder();
        } else {
            order = new Order(); // If a guest wants to make another order.
        }
        order.setUserId(user.get().getId());

        Product product = ProductDaoMem.getInstance().find(productId);

        String productName = product.getName();

        if (order.checkIfLineItemExists(productName)) {
            order.increaseLineItemNumber(productName);
        } else {
            LineItem lineItem = new LineItem(productId, product.getName(), product.getDefaultPrice(), product.getDefaultCurrency());
            order.addlineItem(lineItem);
        }

        int quantity = order.getAllLineItemCount();
        String json = String.format("{ \"userId\": \"%s\", \"quantity\": \"%d\" }", order.getUserId().toString(), quantity);
        JsonObject convertedObject = new Gson().fromJson(json, JsonObject.class);

        PrintWriter out = response.getWriter();
        out.println(convertedObject);
        out.flush();
    }

}