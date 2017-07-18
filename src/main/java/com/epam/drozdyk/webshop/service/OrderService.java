package com.epam.drozdyk.webshop.service;

import com.epam.drozdyk.webshop.bean.model.Order;
import com.epam.drozdyk.webshop.constant.OrderStatus;

public interface OrderService {

    void addNewOrder(Order order);

    void changeOrderStatus(String id, OrderStatus status);
}
