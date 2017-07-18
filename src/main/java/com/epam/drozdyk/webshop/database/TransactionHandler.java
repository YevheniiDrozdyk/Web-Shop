package com.epam.drozdyk.webshop.database;

import com.epam.drozdyk.webshop.exception.TransactionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class TransactionHandler {
    private static final Logger LOGGER = LogManager.getLogger();

    private final DataSource dataSource;

    public TransactionHandler(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public <T> T doInTransaction(TransactionMethod<T> method) {
        try (Connection connection = dataSource.getConnection()) {
            MySqlConnection.setCurrentConnection(connection);
            try {
                T result = method.invoke();
                connection.commit();
                return result;
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e.getCause());
                connection.rollback();
                throw e;
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e.getCause());
            throw new TransactionException(e.getMessage(), e.getCause());
        } finally {
            MySqlConnection.removeCurrentConnection();
        }
    }
}
