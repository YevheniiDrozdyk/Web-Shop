package com.epam.drozdyk.webshop.repository.impl;

import com.epam.drozdyk.webshop.bean.model.Producer;
import com.epam.drozdyk.webshop.database.MySqlConnection;
import com.epam.drozdyk.webshop.exception.RepositoryException;
import com.epam.drozdyk.webshop.mapper.model.impl.ProducerMapper;
import com.epam.drozdyk.webshop.repository.ProducerRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.epam.drozdyk.webshop.constant.Constant.SqlQuery.SELECT_ALL_PRODUCERS;

public class MySqlProducerRepository implements ProducerRepository {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public List<Producer> getProducers() {
        Connection connection = MySqlConnection.getCurrentConnection();
        try (PreparedStatement ps = connection.prepareStatement(SELECT_ALL_PRODUCERS);
             ResultSet rs = ps.executeQuery()) {
            return new ProducerMapper().mapProducers(rs);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e.getCause());
            throw new RepositoryException(e.getMessage(), e.getCause());
        }
    }
}
