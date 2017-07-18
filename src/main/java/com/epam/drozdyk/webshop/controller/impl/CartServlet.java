package com.epam.drozdyk.webshop.controller.impl;

import com.epam.drozdyk.webshop.bean.model.Instrument;
import com.epam.drozdyk.webshop.cart.Cart;
import com.epam.drozdyk.webshop.controller.BaseServlet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.drozdyk.webshop.constant.Constant.Attribute.USER_CART_ATTR;
import static com.epam.drozdyk.webshop.constant.Constant.ContentType.JSON_CONTENT_TYPE;
import static com.epam.drozdyk.webshop.constant.Constant.Parameter.*;
import static com.epam.drozdyk.webshop.constant.Constant.ServletUrl.CART_SERVLET_URL;
import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static javax.servlet.http.HttpServletResponse.SC_OK;

@WebServlet(CART_SERVLET_URL)
public class CartServlet extends BaseServlet {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = obtainUserCart(req);
        int totalCost = 0;
        if (!cart.isEmpty()) {
            totalCost = cart.getTotalCost();
        }
        JSONObject jsonObject = new JSONObject();
        Instrument instrument = obtainInstrument(req);
        if (instrument == null) {
            jsonObject.put(TOTAL_COST_PARAMETER, totalCost);
        } else {
            jsonObject.put(QUANTITY_ITEMS_PARAMETER, cart.getQuantity(instrument));
        }

        resp.setContentType(JSON_CONTENT_TYPE);
        resp.getWriter().write(jsonObject.toJSONString());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = obtainUserCart(req);
        Instrument instrument = obtainInstrument(req);
        int statusCode;
        if (instrument != null) {
            cart.addItem(instrument);
            statusCode = SC_OK;
        } else {
            statusCode = SC_BAD_REQUEST;
        }

        resp.setStatus(statusCode);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = obtainUserCart(req);
        Instrument instrument = obtainInstrument(req);
        Integer value = obtainChangingValue(req);
        if (instrument == null) {
            resp.setStatus(SC_BAD_REQUEST);
            return;
        }
        if (value == null) {
            resp.setStatus(SC_BAD_REQUEST);
            return;
        }
        if (value == 1) {
            cart.increaseAmount(instrument);
            resp.setStatus(SC_OK);
            return;
        }
        if (value == -1) {
            Integer quantity = cart.getQuantity(instrument);
            if (quantity == 1) {
                resp.setStatus(SC_BAD_REQUEST);
            } else {
                cart.reduceAmount(instrument);
                resp.setStatus(SC_OK);
            }
        } else {
            resp.setStatus(SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = obtainUserCart(req);
        String vendorCode = req.getParameter(VENDOR_CODE_PARAMETER);
        if (vendorCode == null) {
            cart.clearCart();
            req.getSession().removeAttribute(USER_CART_ATTR);
            resp.setStatus(SC_OK);
            return;
        }
        Instrument instrument = instrumentService.getInstrument(vendorCode);
        int statusCode;
        if (instrument == null) {
            statusCode = SC_BAD_REQUEST;
        } else {
            cart.removeItem(instrument);
            statusCode = SC_OK;
        }

        resp.setStatus(statusCode);
    }

    private Cart obtainUserCart(HttpServletRequest req) {
        Cart cart = (Cart) req.getSession().getAttribute(USER_CART_ATTR);
        if (cart == null) {
            cart = new Cart();
            req.getSession().setAttribute(USER_CART_ATTR, cart);
        }

        return cart;
    }

    private Instrument obtainInstrument(HttpServletRequest req) {
        String vendorCode = req.getParameter(VENDOR_CODE_PARAMETER);

        return instrumentService.getInstrument(vendorCode);
    }

    private Integer obtainChangingValue(HttpServletRequest req) {
        Integer value = null;
        try {
            value = Integer.parseInt(req.getParameter(CHANGING_VALUE_PARAMETER));
        } catch (NumberFormatException e) {
            LOGGER.error(e.getMessage(), e.getCause());
        }

        return value;
    }
}
