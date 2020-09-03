package com.codecool.shop.model;

import com.codecool.shop.dao.implementation.OrderDaoMem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class User {

    private UUID id;
    private String name;
    private String eMail;
    private int phoneNumber;
    private HashMap<String, String> billingAddress = new HashMap<>();
    private String shippingAddress;
    private List<Order> orders = new ArrayList<>();


    public User() {
        this.id = UUID.randomUUID();
        this.name = "Guest" + this.id;

    }

    public void createOrder(){
        Order order = new Order();
        orders.add(order);
    }


    public void setBillingAddress(HashMap<String, String> billingAddress) {
        this.billingAddress = billingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String geteMail() {
        return eMail;
    }


    public HashMap<String, String> getBillingAddress() {
        return billingAddress;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public boolean isThereAnActiveOrder(){
        return orders.size() > 0 && orders.get(orders.size() - 1).isActive();
    }

    public Order getLastOrder() {
        if (orders.size() > 0) {
            return orders.get(orders.size() - 1);
        }
        return null;
    }


}
