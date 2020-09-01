package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
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
        supplierInitializer(supplierDataStore);
        productCategoryInitializer(productCategoryDataStore);
        productInitializer(productDataStore, productCategoryDataStore, supplierDataStore);
    }

    private void supplierInitializer(SupplierDao supplierDataStore) {
        //setting up new suppliers
        Supplier amazon = new Supplier("Amazon", "Useful and/or weird products");
        supplierDataStore.add(amazon);
        Supplier lenovo = new Supplier("Ali Express", "Mostly weird stuff");
        supplierDataStore.add(lenovo);
        Supplier wish = new Supplier("Wish", "Purely weird stuff");
        supplierDataStore.add(wish);
        Supplier redBubble = new Supplier("Red Bubble", "Fun masks");
        supplierDataStore.add(redBubble);
    }

    private void productCategoryInitializer(ProductCategoryDao productCategoryDataStore) {
        //setting new product categories
        ProductCategory tablet = new ProductCategory("Dad bag", "Accessories", "Useful and nice looking bags to carry your stuff.");
        productCategoryDataStore.add(tablet);
        ProductCategory nicolasCage = new ProductCategory("Nicolas Cage", "Actor", "Nicolas Cage. On your pants. On your mug. On your pillow. Everywhere.");
        productCategoryDataStore.add(nicolasCage);
        ProductCategory children = new ProductCategory("Children", "Toys", "Toys for (mostly) children.");
        productCategoryDataStore.add(children);
        ProductCategory mask = new ProductCategory("Mask", "Health", "Masks to cover your germs.");
        productCategoryDataStore.add(mask);
        ProductCategory jeffGoldblum = new ProductCategory("Jeff Goldblum", "Actor", "If you're not a fan of Nicolas Cage, then there's Jeff Goldblum.");
        productCategoryDataStore.add(jeffGoldblum);
        ProductCategory pet = new ProductCategory("Pet", "Pet", "Pet related accessories.");
        productCategoryDataStore.add(pet);
        ProductCategory accessories = new ProductCategory("Accessories", "Other", "Accessories, unsorted.");
        productCategoryDataStore.add(accessories);
        ProductCategory swimSuit = new ProductCategory("Swim suit", "Clothes", "All types of clothing items.");
        productCategoryDataStore.add(swimSuit);
        ProductCategory itAccessories = new ProductCategory("IT Accessories", "IT related products", "Everything related to computers.");
        productCategoryDataStore.add(itAccessories);
    }

    private void productInitializer(ProductDao productDataStore, ProductCategoryDao productCategoryDataStore, SupplierDao supplierDataStore) {
        //setting up products and printing it
        productDataStore.add(new Product("Dad bag - The Allen", 49.9f, "USD", "Store your stuff in this useful bag that also gives you a nice daddy body - 2 in 1! The Allen edition.", productCategoryDataStore.find("Dad bag"), supplierDataStore.find("Ali Express")));
        productDataStore.add(new Product("Dad bag - The Wolfgang", 52.4f, "USD", "Store your stuff in this useful bag that also gives you a nice daddy body - 2 in 1! The Wolfgang edition.", productCategoryDataStore.find("Dad bag"), supplierDataStore.find("Ali Express")));
        productDataStore.add(new Product("Dad bag - The Sherman", 69, "USD", "Store your stuff in this useful bag that also gives you a nice daddy body - 2 in 1! The Sherman edition.", productCategoryDataStore.find("Dad bag"), supplierDataStore.find("Ali Express")));
        productDataStore.add(new Product("Nicolas Cage Mug", 8, "USD", "Nicolas Cage staring at you nicely from your mug. Everyday.", productCategoryDataStore.find("Nicolas Cage"), supplierDataStore.find("Wish")));
        productDataStore.add(new Product("Nicolas Cage pants", 25, "USD", "Nicolas Cage staring at OTTERS nicely from your pants. Everyday.", productCategoryDataStore.find("Nicolas Cage"), supplierDataStore.find("Wish")));
        productDataStore.add(new Product("Potty trainer", 35, "USD", "You can easily train your child to do number one and number two tasks in this cute potty trainer.", productCategoryDataStore.find("Children"), supplierDataStore.find("Wish")));
        productDataStore.add(new Product("Ace ventura mask - reborn", 15, "USD", "Contain your germs with this nice mask showing the most beautiful thing: birth.", productCategoryDataStore.find("Mask"), supplierDataStore.find("Red Bubble")));
        productDataStore.add(new Product("Ace ventura mask - shikaka", 15, "USD", "No more virus, no more guano.", productCategoryDataStore.find("Mask"), supplierDataStore.find("Red Bubble")));
        productDataStore.add(new Product("Ace ventura mask - bumblebee tuna", 15, "USD", "Keep calm, and bumblebee tuna.", productCategoryDataStore.find("Mask"), supplierDataStore.find("Red Bubble")));
        productDataStore.add(new Product("Pulp fiction mask - Kahuna edition", 15, "USD", "Take it off only if you are having your Kahuna Burger.", productCategoryDataStore.find("Mask"), supplierDataStore.find("Red Bubble")));
        productDataStore.add(new Product("Pulp fiction mask - say what one more time", 15, "USD", "I dare you. I double dare you.", productCategoryDataStore.find("Mask"), supplierDataStore.find("Red Bubble")));
        productDataStore.add(new Product("Home alone mask", 15, "USD", "Where is Kevin?", productCategoryDataStore.find("Mask"), supplierDataStore.find("Red Bubble")));
        productDataStore.add(new Product("Nicolas Cage pillowcase", 13.4f, "USD", "Nicolas Cage protecting your dreams at night. Every night.", productCategoryDataStore.find("Nicolas Cage"), supplierDataStore.find("Wish")));
        productDataStore.add(new Product("Jeff Goldblum pillowcase", 13.4f, "USD", "Ssssshhh....", productCategoryDataStore.find("Jeff Goldblum"), supplierDataStore.find("Wish")));
        productDataStore.add(new Product("Jeff Goldblum shower curtain", 38.7f, "USD", "There's no entering there. Only through Jeff's body.", productCategoryDataStore.find("Jeff Goldblum"), supplierDataStore.find("Wish")));
        productDataStore.add(new Product("Cat coat - reindeer edition", 23, "USD", "Our fur friends might also need a warm suit for winter.", productCategoryDataStore.find("Pet"), supplierDataStore.find("Amazon")));
        productDataStore.add(new Product("Headband with mullet hair", 31, "USD", "Take a trip to retro with the greatest haircut and fashion ever.", productCategoryDataStore.find("Accessories"), supplierDataStore.find("Amazon")));
        productDataStore.add(new Product("Suture pad", 17.7f, "USD", "Practice flesh sewing on the go!", productCategoryDataStore.find("Accessories"), supplierDataStore.find("Wish")));
        productDataStore.add(new Product("Swim suit - Cat vs. shark", 34.9f, "USD", "Cowboy cat riding rainbow vomitting shark filling up long existing market gap.", productCategoryDataStore.find("Swim suit"), supplierDataStore.find("Amazon")));
        productDataStore.add(new Product("Swim suit - Thanos", 42.2f, "USD", "Taking over the world and still have time to appear on this fashionable swim suit.", productCategoryDataStore.find("Swim suit"), supplierDataStore.find("Amazon")));
        productDataStore.add(new Product("Swim suit - Trump", 19.1f, "USD", "Ruling the USA, ruling this swim suit.", productCategoryDataStore.find("Swim suit"), supplierDataStore.find("Amazon")));
        productDataStore.add(new Product("Handitaur", 21.2f, "USD", "Get rid of your boring puppets, the Handitaur has arrived.", productCategoryDataStore.find("Children"), supplierDataStore.find("Wish")));
        productDataStore.add(new Product("Batman water pistol", 9.7f, "USD", "The Dark Knight returns, and spitting water.", productCategoryDataStore.find("Children"), supplierDataStore.find("Ali Express")));
        productDataStore.add(new Product("Pepe the frog - mouse pad", 12.3f, "USD", "The health of your wrists are very important, there's no butt about it.", productCategoryDataStore.find("IT Accessories"), supplierDataStore.find("Ali Express")));
    }
}
