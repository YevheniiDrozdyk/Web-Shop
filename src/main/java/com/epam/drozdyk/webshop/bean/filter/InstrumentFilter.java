package com.epam.drozdyk.webshop.bean.filter;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class InstrumentFilter {
    private List<Integer> categories;
    private List<Integer> producers;
    private List<Boolean> adapters;
    private Integer fromPrice;
    private Integer toPrice;
    private String sortingColumn;
    private String sortingType;
    private Integer itemsLimit;
    private Integer offset;

    private InstrumentFilter() {

    }

    public static Builder newBuilder() {
        return new InstrumentFilter().new Builder();
    }

    public List<Integer> getCategories() {
        return categories;
    }

    public List<Integer> getProducers() {
        return producers;
    }

    public List<Boolean> getAdapters() {
        return adapters;
    }

    public Integer getFromPrice() {
        return fromPrice;
    }

    public Integer getToPrice() {
        return toPrice;
    }

    public String getSortingColumn() {
        return sortingColumn;
    }

    public String getSortingType() {
        return sortingType;
    }

    public Integer getItemsLimit() {
        return itemsLimit;
    }

    public Integer getOffset() {
        return offset;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("categories", categories)
                .append("producers", producers)
                .append("adapters", adapters)
                .append("fromPrice", fromPrice)
                .append("toPrice", toPrice)
                .append("sortingColumn", sortingColumn)
                .append("sortingType", sortingType)
                .append("itemsLimit", itemsLimit)
                .append("offset", offset)
                .toString();
    }

    public class Builder {

        private Builder() {

        }

        public Builder setCategories(List<Integer> categories) {
            InstrumentFilter.this.categories = categories;

            return this;
        }

        public Builder setProducers(List<Integer> producers) {
            InstrumentFilter.this.producers = producers;

            return this;
        }

        public Builder setAdapters(List<Boolean> adapters) {
            InstrumentFilter.this.adapters = adapters;

            return this;
        }

        public Builder setFromPrice(Integer fromPrice) {
            InstrumentFilter.this.fromPrice = fromPrice;

            return this;
        }

        public Builder setToPrice(Integer toPrice) {
            InstrumentFilter.this.toPrice = toPrice;

            return this;
        }

        public Builder setSortingColumn(String sortingColumn) {
            InstrumentFilter.this.sortingColumn = sortingColumn;

            return this;
        }

        public Builder setSortingType(String sortingType) {
            InstrumentFilter.this.sortingType = sortingType;

            return this;
        }

        public Builder setItemsLimit(Integer itemsLimit) {
            InstrumentFilter.this.itemsLimit = itemsLimit;

            return this;
        }

        public Builder setOffset(Integer offset) {
            InstrumentFilter.this.offset = offset;

            return this;
        }

        public InstrumentFilter build() {
            return InstrumentFilter.this;
        }
    }
}
