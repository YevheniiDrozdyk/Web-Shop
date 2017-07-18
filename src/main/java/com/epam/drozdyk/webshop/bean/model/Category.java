package com.epam.drozdyk.webshop.bean.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Category {
    private int id;
    private String name;

    private Category() {

    }

    public static Builder newBuilder() {
        return new Category().new Builder();
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

        Category category = (Category) o;

        return new EqualsBuilder()
                .append(id, category.id)
                .append(name, category.name)
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
            Category.this.id = id;

            return this;
        }

        public Builder setName(String name) {
            Category.this.name = name;

            return this;
        }

        public Category build() {
            return Category.this;
        }
    }
}
