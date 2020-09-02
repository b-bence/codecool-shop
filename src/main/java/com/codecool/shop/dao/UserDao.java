package com.codecool.shop.dao;

import com.codecool.shop.model.User;

import java.util.List;
import java.util.UUID;

public interface UserDao {

    void add(User user);
    User find(UUID id);
    User find(String name);
    void remove(UUID id);

    List<User> getAll();


}
