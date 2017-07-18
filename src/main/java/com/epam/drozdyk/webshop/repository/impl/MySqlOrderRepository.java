package com.epam.drozdyk.webshop.repository.impl;

import com.epam.drozdyk.webshop.bean.model.Order;
import com.epam.drozdyk.webshop.constant.OrderStatus;
import com.epam.drozdyk.webshop.database.MySqlConnection;
import com.epam.drozdyk.webshop.exception.RepositoryException;
import com.epam.drozdyk.webshop.repository.OrderRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import static com.epam.drozdyk.webshop.constant.Constant.SqlQuery.INSERT_NEW_ORDER;
import static com.epam.drozdyk.webshop.constant.Constant.SqlQuery.UPDATE_ORDER_STATUS;

public class MySqlOrderRepository implements OrderRepository {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void addNewOrder(Order order) {
        Connection connection = MySqlConnection.getCurrentConnection();
        try (PreparedStatement ps = connection.prepareStatement(INSERT_NEW_ORDER)) {
            ps.setString(1, order.getId());
            ps.setString(2, order.getStatus().name());
            ps.setString(3, order.getDetailing());
            ps.setTimestamp(4, Timestamp.valueOf(order.getDate()));
            ps.setLong(5, order.getUser().getId());
            ps.execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e.getCause());
            throw new RepositoryException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void changeOrderStatus(String id, OrderStatus status) {
        Connection connection = MySqlConnection.getCurrentConnection();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_ORDER_STATUS)) {
            ps.setString(1, status.name());
            ps.setString(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e.getCause());
            throw new RepositoryException(e.getMessage(), e.getCause());
        }

    }
}
