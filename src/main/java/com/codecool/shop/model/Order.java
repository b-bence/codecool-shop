package com.codecool.shop.model;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Order {

    private UUID id;
    private Map<Product, Integer> products;

    public Order() {
        this.id = new UUID(10, 1);
        this.products = new HashMap<>();
    }

    public void addProduct(Product product) {
        if (products.containsKey(product)) {
            products.put(product, products.get(product) + 1);
        }
        products.put(product, 1);
    }

    public void deleteOneProduct(Product product) {
        if (products.containsKey(product)) {
            products.put(product, products.get(product) - 1);
            if (products.get(product) <= 0) {
                products.remove(product);
            }
        }
    }

    public void deleteOneTypeOfProduct(Product product) {
        if (products.containsKey(product)) {
            products.remove(product);
        }
    }

    public UUID getId() {
        return id;
    }
}
