package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserDaoMem implements UserDao {

    private List<User> users = new ArrayList<>();
    private static UserDaoMem instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private UserDaoMem() {
    }

    public static UserDaoMem getInstance() {
        if (instance == null) {
            instance = new UserDaoMem();
        }
        return instance;
    }

    @Override
    public void add(User user) {
        users.add(user);
    }

    @Override
    public User find(UUID id) {
        return users.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    @Override
    public User find(String name) {
        return users.stream().filter(t -> t.getName().equals(name)).findFirst().orElse(null);
    }

    @Override
    public void remove(UUID id) {
        users.remove(find(id));
    }

    @Override
    public List<User> getAll() {
        return users;
    }
}
