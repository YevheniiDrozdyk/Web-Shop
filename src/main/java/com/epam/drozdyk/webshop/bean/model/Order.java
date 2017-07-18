package com.epam.drozdyk.webshop.bean.model;

import com.epam.drozdyk.webshop.constant.OrderStatus;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private String id;
    private OrderStatus status;
    private String detailing;
    private LocalDateTime date;
    private User user;
    private List<OrderItem> orderItems;

    private Order() {

    }

    public static Builder newBuilder() {
        return new Order().new Builder();
    }

    public String getId() {
        return id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public String getDetailing() {
        return detailing;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public User getUser() {
        return user;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        return new EqualsBuilder()
                .append(id, order.id)
                .append(status, order.status)
                .append(detailing, order.detailing)
                .append(date, order.date)
                .append(user, order.user)
                .append(orderItems, order.orderItems)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(status)
                .append(detailing)
                .append(date)
                .append(user)
                .append(orderItems)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("status", status)
                .append("detailing", detailing)
                .append("date", date)
                .append("user", user)
                .append("orderItems", orderItems)
                .toString();
    }

    public class Builder {

        private Builder() {

        }

        public Builder setId(String id) {
            Order.this.id = id;

            return this;
        }

        public Builder setStatus(OrderStatus status) {
            Order.this.status = status;

            return this;
        }

        public Builder setDetailing(String detailing) {
            Order.this.detailing = detailing;

            return this;
        }

        public Builder setDate(LocalDateTime date) {
            Order.this.date = date;

            return this;
        }

        public Builder setUser(User user) {
            Order.this.user = user;

            return this;
        }

        public Builder setOrderItems(List<OrderItem> orderItems) {
            Order.this.orderItems = orderItems;

            return this;
        }

        public Order build() {
            return Order.this;
        }
    }
}
