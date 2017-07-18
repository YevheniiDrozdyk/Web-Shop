package com.epam.drozdyk.webshop.service.impl;

import com.epam.drozdyk.webshop.bean.model.Order;
import com.epam.drozdyk.webshop.constant.OrderStatus;
import com.epam.drozdyk.webshop.database.TransactionHandler;
import com.epam.drozdyk.webshop.repository.OrderItemRepository;
import com.epam.drozdyk.webshop.repository.OrderRepository;
import com.epam.drozdyk.webshop.service.OrderService;

public class DefaultOrderService implements OrderService {
    private final TransactionHandler transactionHandler;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public DefaultOrderService(TransactionHandler transactionHandler, OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.transactionHandler = transactionHandler;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public void addNewOrder(Order order) {
        transactionHandler.doInTransaction(() -> {
            orderRepository.addNewOrder(order);
            orderItemRepository.addNewOrderItems(order.getId(), order.getOrderItems());

            return null;
        });
    }

    @Override
    public void changeOrderStatus(String id, OrderStatus status) {
        transactionHandler.doInTransaction(() -> {
            orderRepository.changeOrderStatus(id, status);

            return null;
        });
    }
}
