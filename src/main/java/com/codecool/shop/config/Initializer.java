package com.codecool.shop.config;

import com.codecool.shop.dao.*;
import com.codecool.shop.dao.implementation.*;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        ProductDao productDataStore = ProductDaoMem.getInstance();
        OrderDao orderDataStore = OrderDaoMem.getInstance();
        UserDao userDataStore = UserDaoMem.getInstance();

        supplierInitializer(supplierDataStore);
        productCategoryInitializer(productCategoryDataStore);

        productInitializer(productDataStore, productCategoryDataStore, supplierDataStore);

    }

    private void supplierInitializer(SupplierDao supplierDataStore) {
        //setting up new suppliers
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        supplierDataStore.add(amazon);
        Supplier lenovo = new Supplier("Lenovo", "Computers");
        supplierDataStore.add(lenovo);
        Supplier wish = new Supplier("Wish", "Weird stuff");
        supplierDataStore.add(wish);
    }

    private void productCategoryInitializer(ProductCategoryDao productCategoryDataStore) {
        //setting new product categories
        ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(tablet);
        ProductCategory nicolasCage = new ProductCategory("Nicolas Cage", "Actor", "Nicolas Cage. On your pants. On your mug. On your pillow. Everywhere.");
        productCategoryDataStore.add(nicolasCage);
        ProductCategory children = new ProductCategory("Children", "Toys", "Toys for (mostly) children.");
        productCategoryDataStore.add(children);
    }

    private void productInitializer(ProductDao productDataStore, ProductCategoryDao productCategoryDataStore, SupplierDao supplierDataStore) {
        //setting up products and printing it
        productDataStore.add(new Product("Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", productCategoryDataStore.find("Tablet"), supplierDataStore.find("Amazon")));
        productDataStore.add(new Product("Lenovo IdeaPad Miix 700", 479, "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", productCategoryDataStore.find("Tablet"), supplierDataStore.find("Lenovo")));
        productDataStore.add(new Product("Amazon Fire HD 8", 89, "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", productCategoryDataStore.find("Tablet"), supplierDataStore.find("Amazon")));
        productDataStore.add(new Product("Nicolas Cage Mug", 8, "USD", "Nicolas Cage staring at you nicely from your mug. Everyday.", productCategoryDataStore.find("Nicolas Cage"), supplierDataStore.find("Wish")));
        productDataStore.add(new Product("Nicolas Cage pants", 25, "USD", "Nicolas Cage staring at OTTERS nicely from your pants. Everyday.", productCategoryDataStore.find("Nicolas Cage"), supplierDataStore.find("Wish")));
        productDataStore.add(new Product("Potty trainer", 35, "USD", "You can easily train your child to do number one and number two tasks in this cute potty trainer.", productCategoryDataStore.find("Children"), supplierDataStore.find("Wish")));
    }
}
