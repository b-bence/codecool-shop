package com.codecool.shop.model;

import java.util.Currency;

public class LineItem {

    private int productId;
    private String name;
    private float defaultPrice;
    private Currency defaultCurrency;

    public LineItem(int id, String name, float defaultPrice, Currency defaultCurrency) {
        this.productId = id;
        this.name = name;
        this.defaultPrice = defaultPrice;
        this.defaultCurrency = defaultCurrency;
    }

    public int getId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public float getDefaultPrice() {
        return defaultPrice;
    }

    public Currency getDefaultCurrency() {
        return defaultCurrency;
    }
}
