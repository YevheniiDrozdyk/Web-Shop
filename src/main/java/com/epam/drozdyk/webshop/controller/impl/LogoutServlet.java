package com.epam.drozdyk.webshop.controller.impl;

import com.epam.drozdyk.webshop.controller.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.drozdyk.webshop.constant.Constant.ServletUrl.INDEX_SERVLET_URL;
import static com.epam.drozdyk.webshop.constant.Constant.ServletUrl.LOGOUT_SERVLET_URL;

@WebServlet(LOGOUT_SERVLET_URL)
public class LogoutServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        redirectToServlet(INDEX_SERVLET_URL, resp);
    }
}
