package com.epam.drozdyk.webshop.mapper.model.impl;

import com.epam.drozdyk.webshop.bean.model.Category;
import com.epam.drozdyk.webshop.mapper.model.ModelMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.epam.drozdyk.webshop.constant.Constant.DatabaseColumn.ID_COLUMN;
import static com.epam.drozdyk.webshop.constant.Constant.DatabaseColumn.NAME_COLUMN;

public class CategoryMapper extends ModelMapper {
    private static final Logger LOGGER = LogManager.getLogger();

    public List<Category> mapCategories(ResultSet resultSet) {
        List<Category> categories = null;
        try {
            categories = new ArrayList<>();
            resultSet.last();
            int rowCount = resultSet.getRow();
            resultSet.beforeFirst();
            for (int i = 0; i < rowCount; i++) {
                categories.add(mapCategory(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e.getCause());
        }

        return categories;
    }

    private Category mapCategory(ResultSet resultSet) {
        Category category = null;
        try {
            if (resultSet.next()) {
                category = Category.newBuilder()
                        .setId(getInt(resultSet, ID_COLUMN))
                        .setName(getString(resultSet, NAME_COLUMN))
                        .build();
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e.getCause());
        }

        return category;
    }
}
