package com.epam.drozdyk.webshop.bean.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class Instrument implements Serializable {
    private int id;
    private String vendorCode;
    private String producer;
    private boolean adapter;
    private int price;
    private String category;
    private String image;

    private Instrument() {

    }

    public static Builder newBuilder() {
        return new Instrument().new Builder();
    }

    public int getId() {
        return id;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public String getProducer() {
        return producer;
    }

    public boolean isAdapter() {
        return adapter;
    }

    public int getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public String getImage() {
        return image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Instrument that = (Instrument) o;

        return new EqualsBuilder()
                .append(id, that.id)
                .append(adapter, that.adapter)
                .append(price, that.price)
                .append(vendorCode, that.vendorCode)
                .append(producer, that.producer)
                .append(category, that.category)
                .append(image, that.image)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(vendorCode)
                .append(producer)
                .append(adapter)
                .append(price)
                .append(category)
                .append(image)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("vendorCode", vendorCode)
                .append("producer", producer)
                .append("adapter", adapter)
                .append("price", price)
                .append("category", category)
                .append("image", image)
                .toString();
    }

    public class Builder {

        private Builder() {

        }

        public Builder setId(int id) {
            Instrument.this.id = id;

            return this;
        }

        public Builder setVendorCode(String vendorCode) {
            Instrument.this.vendorCode = vendorCode;

            return this;
        }

        public Builder setProducer(String producer) {
            Instrument.this.producer = producer;

            return this;
        }

        public Builder setAdapter(boolean adapter) {
            Instrument.this.adapter = adapter;

            return this;
        }

        public Builder setPrice(int price) {
            Instrument.this.price = price;

            return this;
        }

        public Builder setCategory(String category) {
            Instrument.this.category = category;

            return this;
        }

        public Builder setImage(String image) {
            Instrument.this.image = image;

            return this;
        }

        public Instrument build() {
            return Instrument.this;
        }
    }
}
