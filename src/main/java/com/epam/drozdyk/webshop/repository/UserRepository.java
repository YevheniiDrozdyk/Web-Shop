package com.epam.drozdyk.webshop.repository;

import com.epam.drozdyk.webshop.bean.model.User;

public interface UserRepository {

    User getUser(String email);

    void addNewUser(User user);
}
