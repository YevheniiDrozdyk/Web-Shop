package com.epam.drozdyk.webshop.listener;

import com.epam.drozdyk.webshop.cart.Cart;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import static com.epam.drozdyk.webshop.constant.Constant.Attribute.USER_CART_ATTR;
import static com.epam.drozdyk.webshop.constant.Constant.Message.SESSION_CREATED_MESSAGE;
import static com.epam.drozdyk.webshop.constant.Constant.Message.SESSION_DESTROYED_MESSAGE;

@WebListener
public class ApplicationSessionListener implements HttpSessionListener {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        se.getSession().setAttribute(USER_CART_ATTR, new Cart());
        LOGGER.info(new StringBuilder(SESSION_CREATED_MESSAGE).append(se.getSession().getId()));
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        se.getSession().removeAttribute(USER_CART_ATTR);
        LOGGER.info(new StringBuilder(SESSION_DESTROYED_MESSAGE).append(se.getSession().getId()));
    }
}
