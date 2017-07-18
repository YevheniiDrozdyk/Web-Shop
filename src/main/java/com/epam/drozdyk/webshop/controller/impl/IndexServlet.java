package com.epam.drozdyk.webshop.controller.impl;

import com.epam.drozdyk.webshop.controller.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.drozdyk.webshop.constant.Constant.JspName.INDEX_JSP;
import static com.epam.drozdyk.webshop.constant.Constant.ServletUrl.INDEX_HTML_SERVLET_URL;
import static com.epam.drozdyk.webshop.constant.Constant.ServletUrl.INDEX_SERVLET_URL;

@WebServlet({INDEX_SERVLET_URL, INDEX_HTML_SERVLET_URL})
public class IndexServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forwardToPage(INDEX_JSP, req, resp);
    }
}
