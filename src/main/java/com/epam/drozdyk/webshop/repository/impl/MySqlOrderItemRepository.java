package com.epam.drozdyk.webshop.repository.impl;

import com.epam.drozdyk.webshop.bean.model.OrderItem;
import com.epam.drozdyk.webshop.database.MySqlConnection;
import com.epam.drozdyk.webshop.exception.RepositoryException;
import com.epam.drozdyk.webshop.repository.OrderItemRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static com.epam.drozdyk.webshop.constant.Constant.SqlQuery.INSERT_NEW_ORDER_ITEM;

public class MySqlOrderItemRepository implements OrderItemRepository {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void addNewOrderItems(String idOrder, List<OrderItem> orderItems) {
        Connection connection = MySqlConnection.getCurrentConnection();
        try (PreparedStatement ps = connection.prepareStatement(INSERT_NEW_ORDER_ITEM)) {
            for (OrderItem orderItem : orderItems) {
                ps.setString(1, idOrder);
                ps.setInt(2, orderItem.getInstrument().getId());
                ps.setInt(3, orderItem.getQuantity());
                ps.setInt(4, orderItem.getPrice());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e.getCause());
            throw new RepositoryException(e.getMessage(), e.getCause());
        }
    }
}
