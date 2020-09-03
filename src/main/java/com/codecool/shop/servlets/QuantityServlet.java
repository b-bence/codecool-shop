package com.codecool.shop.servlets;


import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.UserDaoMem;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.User;
import com.codecool.shop.util.Modification;
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
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@WebServlet(name = "QuantityServlet", urlPatterns = "/api/quantity", loadOnStartup = 3)
public class QuantityServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDataStore = UserDaoMem.getInstance();

        String productId = request.getParameter("productid");
        String userId = request.getParameter("userid");
        String modification = request.getParameter("modification");

        User currentUser = userDataStore.find(UUID.fromString(userId));
        Order currentOrder = currentUser.getLastOrder();
        LineItem currentLineItem = currentOrder.getLineItemById(Integer.parseInt(productId));
        int quantity = currentOrder.getLineItemQuantity(currentLineItem);
        if (modification.equals(Modification.decrease.toString())) {
//            Map<LineItem, Integer> lineItems = currentOrder.getLineItems();
            currentOrder.decreaseLineItemNumber(Integer.parseInt(productId));
        } else if (modification.equals(Modification.increase.toString())) {
            currentOrder.increaseLineItemNumber(Integer.parseInt(productId));
        }
        if (quantity == 1) {
            currentOrder.deleteOneTypeOflineItem(currentLineItem);
            quantity = 0;
        } else {
            quantity = currentOrder.getLineItemQuantity(currentLineItem);
        }

        String json = String.format("{ \"productId\": \"%s\", \"orderId\": \"%s\", \"quantity\": \"%d\" }", productId, userId, quantity);
        JsonObject convertedObject = new Gson().fromJson(json, JsonObject.class);

        PrintWriter out = response.getWriter();
        out.println(convertedObject);
        out.flush();
    }

}