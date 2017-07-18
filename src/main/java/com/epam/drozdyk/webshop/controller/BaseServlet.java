package com.epam.drozdyk.webshop.controller;

import com.epam.drozdyk.webshop.captcha.provider.CaptchaService;
import com.epam.drozdyk.webshop.service.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.drozdyk.webshop.constant.Constant.Attribute.*;
import static com.epam.drozdyk.webshop.constant.Constant.JspName.TEMPLATE_JSP_URL;

public abstract class BaseServlet extends HttpServlet {
    protected UserService userService;
    protected InstrumentService instrumentService;
    protected CategoryService categoryService;
    protected ProducerService producerService;
    protected OrderService orderService;
    protected CaptchaService captchaService;

    @Override
    public final void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.userService = (UserService) getServletContext().getAttribute(USER_SERVICE_ATTR);
        this.instrumentService = (InstrumentService) getServletContext().getAttribute(INSTRUMENT_SERVICE_ATTR);
        this.categoryService = (CategoryService) getServletContext().getAttribute(CATEGORY_SERVICE_ATTR);
        this.producerService = (ProducerService) getServletContext().getAttribute(PRODUCER_SERVICE_ATTR);
        this.orderService = (OrderService) getServletContext().getAttribute(ORDER_SERVICE_ATTR);
        this.captchaService = (CaptchaService) getServletContext().getAttribute(CAPTCHA_SERVICE_ATTR);
    }

    protected void forwardToPage(String page, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(CURRENT_PAGE_ATTR, page);
        req.getRequestDispatcher(TEMPLATE_JSP_URL).forward(req, resp);
    }

    protected void redirectToServlet(String url, HttpServletResponse resp) throws IOException {
        resp.sendRedirect(resp.encodeURL(url));
    }
}
