package com.epam.drozdyk.webshop.database;

import com.epam.drozdyk.webshop.bean.filter.InstrumentFilter;

import java.util.List;

import static com.epam.drozdyk.webshop.constant.Constant.SqlStatement.*;

public class SqlQueryBuilder {
    private StringBuilder query;

    public SqlQueryBuilder() {
        this.query = new StringBuilder();
    }

    public SqlQueryBuilder select() {
        query.append(SELECT_STATEMENT);

        return this;
    }

    public SqlQueryBuilder select(String... columns) {
        query.append(SELECT_STATEMENT);
        if (columns == null) {
            query.append(ALL_COLUMNS_STATEMENT);
        } else {
            int columnsCount = columns.length;
            int cursor = 1;
            for (String column : columns) {
                if (columnsCount == 1) {
                    query.append(column);
                } else {
                    query.append(column);
                    if (cursor != columnsCount) {
                        query.append(COMA_STATEMENT);
                    }
                }
                cursor++;
            }
        }

        return this;
    }

    public SqlQueryBuilder count() {
        query.append(COUNT_STATEMENT);

        return this;
    }

    public SqlQueryBuilder from(String... tables) {
        query.append(FROM_STATEMENT);
        int tablesCount = tables.length;
        int cursor = 1;
        for (String table : tables) {
            if (tablesCount == 1) {
                query.append(table);
            } else {
                query.append(table);
                if (cursor != tablesCount) {
                    query.append(COMA_STATEMENT);
                }
            }
            cursor++;
        }

        return this;
    }

    public SqlQueryBuilder innerJoin(String table) {
        query.append(INNER_JOIN_STATEMENT);
        query.append(table);

        return this;
    }

    public SqlQueryBuilder on(String joinCondition) {
        query.append(ON_STATEMENT);
        query.append(joinCondition);

        return this;
    }

    public SqlQueryBuilder where(InstrumentFilter filter) {
        if (filter.getCategories() == null && filter.getProducers() == null && filter.getAdapters() == null && filter.getFromPrice() == null && filter.getToPrice() == null) {
            return this;
        } else {
            query.append(WHERE_STATEMENT);
        }

        return this;
    }

    public SqlQueryBuilder and() {
        query.append(AND_STATEMENT);

        return this;
    }

    public SqlQueryBuilder like(String condition) {
        query.append(LIKE_START_STATEMENT);
        query.append(condition);
        query.append(LIKE_END_STATEMENT);

        return this;
    }

    public SqlQueryBuilder between(String column, Integer from, Integer to) {
        if (from != null) {
            notLessThan(column, from);
        }
        if (to != null) {
            notMoreThan(column, to);
        }

        return this;
    }

    public <T> SqlQueryBuilder in(String column, List<T> values) {
        if (values != null) {
            this.and();
            query.append(column)
                    .append(IN_START_STATEMENT)
                    .append(values.get(0));
            for (int i = 1; i < values.size(); i++) {
                query.append(COMA_STATEMENT)
                        .append(values.get(i));
            }
            query.append(IN_END_STATEMENT);
        }

        return this;
    }

    public SqlQueryBuilder orderBy(String sortingColumn, String sortingType) {
        if (sortingColumn != null) {
            query.append(ORDER_BY_STATEMENT);
            query.append(sortingColumn);
            if (sortingType != null) {
                query.append(WHITESPACE_STATEMENT);
                query.append(sortingType);
            }
        }

        return this;
    }

    public SqlQueryBuilder limit(Integer amount) {
        if (amount != null) {
            query.append(LIMIT_STATEMENT);
            query.append(amount);
        }

        return this;
    }

    public SqlQueryBuilder offset(Integer amount) {
        if (amount != null) {
            query.append(OFFSET_STATEMENT);
            query.append(amount);
        }

        return this;
    }

    public String build() {
        return query.toString();
    }

    private void notLessThan(String column, Integer value) {
        query.append(column);
        query.append(NOT_LESS_THAN_STATEMENT);
        query.append(value);
    }

    private void notMoreThan(String column, Integer value) {
        this.and();
        query.append(column);
        query.append(NOT_MORE_THAN_STATEMENT);
        query.append(value);
    }
}
