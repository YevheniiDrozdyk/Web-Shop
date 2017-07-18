package com.epam.drozdyk.webshop.repository.impl;

import com.epam.drozdyk.webshop.bean.filter.InstrumentFilter;
import com.epam.drozdyk.webshop.bean.model.Instrument;
import com.epam.drozdyk.webshop.database.MySqlConnection;
import com.epam.drozdyk.webshop.database.SqlQueryBuilder;
import com.epam.drozdyk.webshop.exception.RepositoryException;
import com.epam.drozdyk.webshop.mapper.model.impl.InstrumentMapper;
import com.epam.drozdyk.webshop.repository.InstrumentRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.epam.drozdyk.webshop.constant.Constant.DatabaseColumn.*;
import static com.epam.drozdyk.webshop.constant.Constant.DatabaseCondition.INSTRUMENT_CATEGORY_JOIN_CONDITION;
import static com.epam.drozdyk.webshop.constant.Constant.DatabaseCondition.INSTRUMENT_PRODUCER_JOIN_CONDITION;
import static com.epam.drozdyk.webshop.constant.Constant.DatabaseTable.*;
import static com.epam.drozdyk.webshop.constant.Constant.SqlQuery.SELECT_ALL_INSTRUMENTS;
import static com.epam.drozdyk.webshop.constant.Constant.SqlQuery.SELECT_INSTRUMENT_BY_VENDOR_CODE;
import static com.epam.drozdyk.webshop.constant.Constant.SqlStatement.PERCENT_STATEMENT;

public class MySqlInstrumentRepository implements InstrumentRepository {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Instrument getInstrument(String vendorCode) {
        Connection connection = MySqlConnection.getCurrentConnection();
        ResultSet rs = null;
        try (PreparedStatement ps = connection.prepareStatement(SELECT_INSTRUMENT_BY_VENDOR_CODE)) {
            ps.setString(1, new StringBuilder(PERCENT_STATEMENT)
                    .append(vendorCode)
                    .append(PERCENT_STATEMENT)
                    .toString());
            rs = ps.executeQuery();
            return new InstrumentMapper().mapInstrument(rs);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e.getCause());
            throw new RepositoryException(e.getMessage(), e.getCause());
        } finally {
            close(rs);
        }
    }

    @Override
    public List<Instrument> getInstruments() {
        Connection connection = MySqlConnection.getCurrentConnection();
        try (PreparedStatement ps = connection.prepareStatement(SELECT_ALL_INSTRUMENTS);
             ResultSet rs = ps.executeQuery()) {
            return new InstrumentMapper().mapInstruments(rs);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e.getCause());
            throw new RepositoryException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<Instrument> getInstruments(InstrumentFilter filter) {
        String sql = obtainSqlForGetInstruments(filter);
        Connection connection = MySqlConnection.getCurrentConnection();
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            return new InstrumentMapper().mapInstruments(rs);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e.getCause());
            throw new RepositoryException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Integer getInstrumentCount(InstrumentFilter filter) {
        String sql = obtainSqlForGetInstrumentCount(filter);
        Connection connection = MySqlConnection.getCurrentConnection();
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            return new InstrumentMapper().mapInstrumentCount(rs);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e.getCause());
            throw new RepositoryException(e.getMessage(), e.getCause());
        }
    }

    private void close(ResultSet resultSet) {
        try {
            resultSet.close();
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage(), e.getCause());
        }
    }

    private String obtainSqlForGetInstruments(InstrumentFilter filter) {
        return new SqlQueryBuilder()
                .select(INSTRUMENT_ALL_COLUMNS, CATEGORY_NAME_AS_CATEGORY_COLUMN, PRODUCER_NAME_AS_PRODUCER_COLUMN)
                .from(INSTRUMENT_TABLE_NAME)
                .innerJoin(CATEGORY_TABLE_NAME)
                .on(INSTRUMENT_CATEGORY_JOIN_CONDITION)
                .innerJoin(PRODUCER_TABLE_NAME)
                .on(INSTRUMENT_PRODUCER_JOIN_CONDITION)
                .where(filter)
                .between(PRICE_COLUMN, filter.getFromPrice(), filter.getToPrice())
                .in(ID_CATEGORY_COLUMN, filter.getCategories())
                .in(ID_PRODUCER_COLUMN, filter.getProducers())
                .in(ADAPTER_COLUMN, filter.getAdapters())
                .orderBy(filter.getSortingColumn(), filter.getSortingType())
                .limit(filter.getItemsLimit())
                .offset(filter.getOffset())
                .build();
    }

    private String obtainSqlForGetInstrumentCount(InstrumentFilter filter) {
        return new SqlQueryBuilder()
                .select()
                .count()
                .from(INSTRUMENT_TABLE_NAME)
                .innerJoin(CATEGORY_TABLE_NAME)
                .on(INSTRUMENT_CATEGORY_JOIN_CONDITION)
                .innerJoin(PRODUCER_TABLE_NAME)
                .on(INSTRUMENT_PRODUCER_JOIN_CONDITION)
                .where(filter)
                .between(PRICE_COLUMN, filter.getFromPrice(), filter.getToPrice())
                .in(ID_CATEGORY_COLUMN, filter.getCategories())
                .in(ID_PRODUCER_COLUMN, filter.getProducers())
                .in(ADAPTER_COLUMN, filter.getAdapters())
                .build();
    }
}
