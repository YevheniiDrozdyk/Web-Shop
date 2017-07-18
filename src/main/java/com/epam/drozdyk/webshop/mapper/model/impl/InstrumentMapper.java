package com.epam.drozdyk.webshop.mapper.model.impl;

import com.epam.drozdyk.webshop.bean.model.Instrument;
import com.epam.drozdyk.webshop.mapper.model.ModelMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.epam.drozdyk.webshop.constant.Constant.DatabaseColumn.*;

public class InstrumentMapper extends ModelMapper {
    private static final Logger LOGGER = LogManager.getLogger();

    public Instrument mapInstrument(ResultSet resultSet) {
        Instrument instrument = null;
        try {
            if (resultSet.next()) {
                instrument = Instrument.newBuilder()
                        .setId(getInt(resultSet, ID_COLUMN))
                        .setVendorCode(getString(resultSet, VENDOR_CODE_COLUMN))
                        .setProducer(getString(resultSet, PRODUCER_COLUMN))
                        .setAdapter(getBoolean(resultSet, ADAPTER_COLUMN))
                        .setPrice(getInt(resultSet, PRICE_COLUMN))
                        .setCategory(getString(resultSet, CATEGORY_COLUMN))
                        .setImage(getString(resultSet, IMAGE_COLUMN))
                        .build();
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e.getCause());
        }

        return instrument;
    }

    public List<Instrument> mapInstruments(ResultSet resultSet) {
        List<Instrument> instruments = null;
        try {
            instruments = new ArrayList<>();
            resultSet.last();
            int rowCount = resultSet.getRow();
            resultSet.beforeFirst();
            for (int i = 0; i < rowCount; i++) {
                instruments.add(mapInstrument(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e.getCause());
        }

        return instruments;
    }

    public Integer mapInstrumentCount(ResultSet resultSet) {
        Integer count = null;
        try {
            if (resultSet.next()) {
                count = getInt(resultSet, RECORD_COUNT_COLUMN);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e.getCause());
        }

        return count;
    }
}
