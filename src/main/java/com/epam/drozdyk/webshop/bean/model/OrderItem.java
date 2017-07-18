package com.epam.drozdyk.webshop.bean.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class OrderItem implements Serializable {
    private long id;
    private Instrument instrument;
    private Integer quantity;
    private int price;

    private OrderItem() {

    }

    public static Builder newBuilder() {
        return new OrderItem().new Builder();
    }

    public long getId() {
        return id;
    }

    public Instrument getInstrument() {
        return instrument;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        OrderItem orderItem = (OrderItem) o;

        return new EqualsBuilder()
                .append(id, orderItem.id)
                .append(price, orderItem.price)
                .append(instrument, orderItem.instrument)
                .append(quantity, orderItem.quantity)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(instrument)
                .append(quantity)
                .append(price)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("instrument", instrument)
                .append("quantity", quantity)
                .append("price", price)
                .toString();
    }

    public class Builder {

        private Builder() {

        }

        public Builder setId(int id) {
            OrderItem.this.id = id;

            return this;
        }

        public Builder setInstrument(Instrument instrument) {
            OrderItem.this.instrument = instrument;

            return this;
        }

        public Builder setQuantity(Integer quantity) {
            OrderItem.this.quantity = quantity;

            return this;
        }

        public Builder setPrice(Integer price) {
            OrderItem.this.price = price;

            return this;
        }

        public OrderItem build() {
            return OrderItem.this;
        }
    }
}
