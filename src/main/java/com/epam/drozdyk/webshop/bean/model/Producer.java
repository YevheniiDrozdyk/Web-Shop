package com.epam.drozdyk.webshop.bean.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Producer {
    private int id;
    private String name;

    private Producer() {

    }

    public static Builder newBuilder() {
        return new Producer().new Builder();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Producer producer = (Producer) o;

        return new EqualsBuilder()
                .append(id, producer.id)
                .append(name, producer.name)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(name)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .toString();
    }

    public class Builder {

        private Builder() {

        }

        public Builder setId(int id) {
            Producer.this.id = id;

            return this;
        }

        public Builder setName(String name) {
            Producer.this.name = name;

            return this;
        }

        public Producer build() {
            return Producer.this;
        }
    }
}
