package com.epam.drozdyk.webshop.mapper.model.impl;

import com.epam.drozdyk.webshop.bean.form.RegistrationForm;
import com.epam.drozdyk.webshop.bean.model.User;
import com.epam.drozdyk.webshop.mapper.model.ModelMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.epam.drozdyk.webshop.constant.Constant.DatabaseColumn.*;
import static com.epam.drozdyk.webshop.constant.Constant.Parameter.*;

public class UserMapper extends ModelMapper {
    private static final Logger LOGGER = LogManager.getLogger();

    public User mapUser(RegistrationForm form) {
        return User.newBuilder()
                .setFirstName(form.getFirstName())
                .setLastName(form.getLastName())
                .setEmail(form.getEmail())
                .setPassword(form.getPassword())
                .build();
    }

    public User mapUser(ResultSet resultSet) {
        User user = null;
        try {
            if (resultSet.next()) {
                user = User.newBuilder()
                        .setId(getLong(resultSet, ID_COLUMN))
                        .setFirstName(getString(resultSet, FIRST_NAME_PARAMETER))
                        .setLastName(getString(resultSet, LAST_NAME_PARAMETER))
                        .setEmail(getString(resultSet, EMAIL_COLUMN))
                        .setPassword(getString(resultSet, PASSWORD_COLUMN))
                        .setAvatar(getString(resultSet, AVATAR_PARAMETER))
                        .setRole(getString(resultSet, ROLE_COLUMN))
                        .build();
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e.getCause());
        }

        return user;
    }
}
