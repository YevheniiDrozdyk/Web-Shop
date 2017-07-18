package com.epam.drozdyk.webshop.service.impl;

import com.epam.drozdyk.webshop.bean.model.User;
import com.epam.drozdyk.webshop.database.TransactionHandler;
import com.epam.drozdyk.webshop.repository.UserRepository;
import com.epam.drozdyk.webshop.service.UserService;

public class DefaultUserService implements UserService {
    private final TransactionHandler transactionHandler;
    private final UserRepository userRepository;

    public DefaultUserService(TransactionHandler transactionHandler, UserRepository userRepository) {
        this.transactionHandler = transactionHandler;
        this.userRepository = userRepository;
    }

    @Override
    public boolean login(String email, String password) {
        return getUser(email).getPassword().equals(password);
    }

    @Override
    public void register(User user) {
        transactionHandler.doInTransaction(() -> {
            userRepository.addNewUser(user);
            return null;
        });
    }

    @Override
    public boolean isExist(String email) {
        return getUser(email) != null;
    }

    @Override
    public User getUser(String email) {
        return transactionHandler.doInTransaction(() ->
                userRepository.getUser(email));
    }
}
