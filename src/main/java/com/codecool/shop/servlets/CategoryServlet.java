package com.codecool.shop.servlets;

import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.util.Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "CategoryServlet", urlPatterns = "/api/category", loadOnStartup = 1)
public class CategoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String categoryName = request.getParameter("name");
        List<Product> productList = getProductsByCategoryName(categoryName);

        String result = Util.parseProductObjectListToJSON(productList);

        PrintWriter out = response.getWriter();
        out.println(result);
        out.flush();
    }

    private List<Product> getProductsByCategoryName(String categoryName) {
        ProductCategoryDaoMem productCategoryDaoMem = ProductCategoryDaoMem.getInstance();
        ProductCategory productCategory = productCategoryDaoMem.find(categoryName);
        ProductDaoMem productDaoMem = ProductDaoMem.getInstance();
        List<Product> productList = productDaoMem.getBy(productCategory);
        return productList;
    }
}
