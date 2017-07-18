package com.epam.drozdyk.webshop.mapper.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class ModelMapper {

    protected boolean getBoolean(ResultSet resultSet, String columnName) throws SQLException {
        return resultSet.getBoolean(columnName);
    }

    protected int getInt(ResultSet resultSet, String columnName) throws SQLException {
        return resultSet.getInt(columnName);
    }

    protected long getLong(ResultSet resultSet, String columnName) throws SQLException {
        return resultSet.getLong(columnName);
    }

    protected String getString(ResultSet resultSet, String columnName) throws SQLException {
        return resultSet.getString(columnName);
    }
}
