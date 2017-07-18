package com.epam.drozdyk.webshop.repository;

import com.epam.drozdyk.webshop.bean.model.OrderItem;

import java.util.List;

public interface OrderItemRepository {

    void addNewOrderItems(String idOrder, List<OrderItem> orderItems);
}
