package com.codecool.shop.dao;

import com.codecool.shop.model.Order;

import java.util.UUID;

public interface OrderDao {

    void add(Order order);
    Order find(UUID id);
    //Order find(String name);
    void remove(UUID id);

    //List<Supplier> getAll();
}
