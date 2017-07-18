package com.epam.drozdyk.webshop.service;

import com.epam.drozdyk.webshop.bean.model.User;

public interface UserService {

    boolean login(String email, String password);

    void register(User user);

    boolean isExist(String email);

    User getUser(String email);
}
