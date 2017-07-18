package com.epam.drozdyk.webshop.controller.impl;

import com.epam.drozdyk.webshop.bean.model.Instrument;
import com.epam.drozdyk.webshop.bean.model.Order;
import com.epam.drozdyk.webshop.bean.model.OrderItem;
import com.epam.drozdyk.webshop.bean.model.User;
import com.epam.drozdyk.webshop.cart.Cart;
import com.epam.drozdyk.webshop.constant.OrderStatus;
import com.epam.drozdyk.webshop.controller.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.epam.drozdyk.webshop.constant.Constant.Attribute.*;
import static com.epam.drozdyk.webshop.constant.Constant.Message.FORMED_ORDER_MESSAGE;
import static com.epam.drozdyk.webshop.constant.Constant.ServletUrl.*;

@WebServlet(PLACE_ORDER_SERVLET_URL)
public class PlaceOrderServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute(CURRENT_USER_ATTR);
        if (user == null) {
            redirectToServlet(LOGIN_SERVLET_URL, resp);
            return;
        }
        Cart cart = obtainCart(req);
        if (cart == null) {
            redirectToServlet(INDEX_SERVLET_URL, resp);
            return;
        }
        List<OrderItem> orderItems = obtainOrderItems(cart);
        Order order = obtainOrder(user, orderItems);
        orderService.addNewOrder(order);
        req.getSession().setAttribute(USER_ORDER_ATTR, order);
        req.getSession().removeAttribute(USER_CART_ATTR);

        redirectToServlet(CONFIRM_ORDER_SERVLET_URL, resp);
    }

    private Cart obtainCart(HttpServletRequest req) {
        return (Cart) req.getSession().getAttribute(USER_CART_ATTR);
    }

    private List<OrderItem> obtainOrderItems(Cart cart) {
        List<OrderItem> orderItems = new ArrayList<>();
        for (Object cartItem : cart.getCart().entrySet()) {
            Map.Entry entry = (Map.Entry) cartItem;
            Instrument instrument = (Instrument) entry.getKey();
            Integer quantity = (Integer) entry.getValue();
            OrderItem orderItem = OrderItem.newBuilder()
                    .setInstrument(instrument)
                    .setPrice(instrument.getPrice())
                    .setQuantity(quantity)
                    .build();
            orderItems.add(orderItem);
        }

        return orderItems;
    }

    private Order obtainOrder(User user, List<OrderItem> orderItems) {
        return Order.newBuilder()
                .setId(UUID.randomUUID().toString())
                .setStatus(OrderStatus.FORMED)
                .setDetailing(FORMED_ORDER_MESSAGE)
                .setDate(LocalDateTime.now())
                .setUser(user)
                .setOrderItems(orderItems)
                .build();
    }
}
