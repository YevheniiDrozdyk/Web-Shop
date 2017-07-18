package com.epam.drozdyk.webshop.controller.impl;

import com.epam.drozdyk.webshop.bean.form.PaymentForm;
import com.epam.drozdyk.webshop.bean.model.Order;
import com.epam.drozdyk.webshop.controller.BaseServlet;
import com.epam.drozdyk.webshop.mapper.form.PaymentFormMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.drozdyk.webshop.constant.Constant.Attribute.USER_ORDER_ATTR;
import static com.epam.drozdyk.webshop.constant.Constant.JspName.CONFIRM_ORDER_JSP;
import static com.epam.drozdyk.webshop.constant.Constant.ServletUrl.CONFIRM_ORDER_SERVLET_URL;
import static com.epam.drozdyk.webshop.constant.Constant.ServletUrl.INDEX_SERVLET_URL;
import static com.epam.drozdyk.webshop.constant.OrderStatus.ACCEPTED;

@WebServlet(CONFIRM_ORDER_SERVLET_URL)
public class ConfirmOrderServlet extends BaseServlet {
    private PaymentFormMapper paymentFormMapper;

    @Override
    public void init() throws ServletException {
        super.init();
        this.paymentFormMapper = new PaymentFormMapper();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forwardToPage(CONFIRM_ORDER_JSP, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PaymentForm paymentForm = paymentFormMapper.mapForm(req);
        Order order = (Order) req.getSession().getAttribute(USER_ORDER_ATTR);
        orderService.changeOrderStatus(order.getId(), ACCEPTED);

        redirectToServlet(INDEX_SERVLET_URL, resp);
    }
}
