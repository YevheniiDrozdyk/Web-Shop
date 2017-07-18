package com.epam.drozdyk.webshop.repository.impl;

import com.epam.drozdyk.webshop.bean.model.User;
import com.epam.drozdyk.webshop.database.MySqlConnection;
import com.epam.drozdyk.webshop.exception.RepositoryException;
import com.epam.drozdyk.webshop.mapper.model.impl.UserMapper;
import com.epam.drozdyk.webshop.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.epam.drozdyk.webshop.constant.Constant.SqlQuery.INSERT_NEW_USER;
import static com.epam.drozdyk.webshop.constant.Constant.SqlQuery.SELECT_USER_BY_EMAIL;

public class MySqlUserRepository implements UserRepository {
    private static final Logger LOGGER = LogManager.getLogger();
    private final UserMapper userMapper;

    public MySqlUserRepository() {
        this.userMapper = new UserMapper();
    }

    @Override
    public User getUser(String email) {
        Connection connection = MySqlConnection.getCurrentConnection();
        ResultSet rs = null;
        try (PreparedStatement ps = connection.prepareStatement(SELECT_USER_BY_EMAIL)) {
            ps.setString(1, email);
            rs = ps.executeQuery();
            return userMapper.mapUser(rs);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e.getCause());
            throw new RepositoryException(e.getMessage(), e.getCause());
        } finally {
            close(rs);
        }
    }

    @Override
    public void addNewUser(User user) {
        Connection connection = MySqlConnection.getCurrentConnection();
        try (PreparedStatement ps = connection.prepareStatement(INSERT_NEW_USER)) {
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getAvatar());
            ps.execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e.getCause());
            throw new RepositoryException(e.getMessage(), e.getCause());
        }
    }

    private void close(ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage(), e.getCause());
        }
    }
}
