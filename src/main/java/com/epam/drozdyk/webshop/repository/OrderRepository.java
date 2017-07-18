package com.epam.drozdyk.webshop.repository;

import com.epam.drozdyk.webshop.bean.model.Order;
import com.epam.drozdyk.webshop.constant.OrderStatus;

public interface OrderRepository {

    void addNewOrder(Order order);

    void changeOrderStatus(String id, OrderStatus status);
}
