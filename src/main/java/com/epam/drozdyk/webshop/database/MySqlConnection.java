package com.epam.drozdyk.webshop.database;

import com.epam.drozdyk.webshop.exception.MySqlConnectionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;

import static com.epam.drozdyk.webshop.constant.Constant.Message.CANNOT_FIND_CONNECTION_MESSAGE;

public class MySqlConnection {
    private static final ThreadLocal<Connection> CONNECTIONS = new ThreadLocal<>();
    private static final Logger LOGGER = LogManager.getLogger();

    private MySqlConnection() {

    }

    public static Connection getCurrentConnection() {
        Connection connection = CONNECTIONS.get();
        if (connection == null) {
            LOGGER.error(CANNOT_FIND_CONNECTION_MESSAGE);
            throw new MySqlConnectionException(CANNOT_FIND_CONNECTION_MESSAGE);
        }

        return connection;
    }

    static void setCurrentConnection(Connection c) {
        CONNECTIONS.set(c);
    }

    static void removeCurrentConnection() {
        CONNECTIONS.remove();
    }
}
