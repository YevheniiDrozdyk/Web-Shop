package com.epam.drozdyk.webshop.mapper.filter;

import com.epam.drozdyk.webshop.bean.filter.InstrumentFilter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static com.epam.drozdyk.webshop.constant.Constant.DatabaseColumn.PRICE_COLUMN;
import static com.epam.drozdyk.webshop.constant.Constant.DatabaseColumn.PRODUCER_COLUMN;
import static com.epam.drozdyk.webshop.constant.Constant.Parameter.*;
import static com.epam.drozdyk.webshop.constant.Constant.SqlStatement.ASC_SORTING_STATEMENT;
import static com.epam.drozdyk.webshop.constant.Constant.SqlStatement.DESC_SORTING_STATEMENT;

public class InstrumentFilterMapper {
    private static final Logger LOGGER = LogManager.getLogger();

    public InstrumentFilter mapInstrumentFilter(HttpServletRequest req) {
        List<Integer> categories = obtainIds(req, CATEGORIES_IDS_LIST_PARAMETER);
        List<Integer> producers = obtainIds(req, PRODUCERS_IDS_LIST_PARAMETER);
        List<Boolean> adapters = obtainAdapters(req);
        Integer fromPrice = obtainPrice(req, FROM_PRICE_PARAMETER);
        Integer toPrice = obtainPrice(req, TO_PRICE_PARAMETER);
        Integer itemsLimit = obtainItemsLimit(req);
        String sortingColumn = obtainSortingColumn(req);
        String sortingType = obtainSortingType(req);
        Integer offset = obtainOffset(req, itemsLimit);

        return InstrumentFilter.newBuilder()
                .setCategories(categories)
                .setProducers(producers)
                .setAdapters(adapters)
                .setFromPrice(fromPrice)
                .setToPrice(toPrice)
                .setSortingColumn(sortingColumn)
                .setSortingType(sortingType)
                .setItemsLimit(itemsLimit)
                .setOffset(offset)
                .build();
    }

    private List<Integer> obtainIds(HttpServletRequest req, String parameter) {
        String[] idsArray = req.getParameterValues(parameter);
        List<Integer> ids = null;
        if (idsArray != null) {
            ids = new ArrayList<>();
            for (String id : idsArray) {
                ids.add(Integer.valueOf(id));
            }
        }

        return ids;
    }

    private List<Boolean> obtainAdapters(HttpServletRequest req) {
        String[] adaptersArr = req.getParameterValues(ADAPTERS_LIST_PARAMETER);
        List<Boolean> adapters = null;
        if (adaptersArr != null) {
            adapters = new ArrayList<>();
            for (String adapter : adaptersArr) {
                adapters.add(Boolean.valueOf(adapter));
            }
        }

        return adapters;
    }

    private Integer obtainPrice(HttpServletRequest req, String parameterName) {
        Integer price = null;
        try {
            price = Integer.valueOf(req.getParameter(parameterName));
            if (price < 0) {
                price = null;
            }
        } catch (NumberFormatException e) {
            LOGGER.error(e.getMessage(), e.getCause());
        }

        return price;
    }

    private Integer obtainItemsLimit(HttpServletRequest req) {
        Integer limit = null;
        try {
            limit = Integer.valueOf(req.getParameter(ITEMS_LIMIT_PARAMETER));
            if (limit <= 0) {
                limit = null;
            }
        } catch (NumberFormatException e) {
            LOGGER.error(e.getMessage(), e.getCause());
        }

        return limit;
    }

    private String obtainSortingColumn(HttpServletRequest req) {
        String sortingColumn = req.getParameter(SORTING_COLUMN_PARAMETER);
        if (PRODUCER_COLUMN.equals(sortingColumn)) {
            return sortingColumn;
        } else if (PRICE_COLUMN.equals(sortingColumn)) {
            return sortingColumn;
        } else {
            return null;
        }
    }

    private String obtainSortingType(HttpServletRequest req) {
        String sortingType = req.getParameter(SORTING_TYPE_PARAMETER);
        if (sortingType != null) {
            sortingType = sortingType.toUpperCase();
            if (ASC_SORTING_STATEMENT.equals(sortingType)) {
                return sortingType;
            } else if (DESC_SORTING_STATEMENT.equals(sortingType)) {
                return sortingType;
            } else {
                return null;
            }
        }

        return null;
    }

    private Integer obtainOffset(HttpServletRequest req, Integer itemsLimit) {
        if (itemsLimit == null) {
            return null;
        }
        Integer offset = null;
        try {
            Integer page = Integer.valueOf(req.getParameter(PAGE_PARAMETER));
            offset = (page - 1) * itemsLimit;
        } catch (NumberFormatException e) {
            LOGGER.error(e.getMessage(), e.getCause());
        }

        return offset;
    }
}
