package com.epam.drozdyk.webshop.controller.impl;

import com.epam.drozdyk.webshop.controller.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.drozdyk.webshop.constant.Constant.JspName.CHECKOUT_JSP;
import static com.epam.drozdyk.webshop.constant.Constant.ServletUrl.CHECKOUT_SERVLET_URL;

@WebServlet(CHECKOUT_SERVLET_URL)
public class CheckoutServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forwardToPage(CHECKOUT_JSP, req, resp);
    }
}
