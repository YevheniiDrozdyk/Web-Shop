package com.epam.drozdyk.webshop.controller.impl;

import com.epam.drozdyk.webshop.bean.form.LoginForm;
import com.epam.drozdyk.webshop.controller.BaseServlet;
import com.epam.drozdyk.webshop.mapper.form.LoginFormMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

import static com.epam.drozdyk.webshop.constant.Constant.Attribute.*;
import static com.epam.drozdyk.webshop.constant.Constant.JspName.LOGIN_JSP;
import static com.epam.drozdyk.webshop.constant.Constant.Message.INVALID_PASSWORD_MESSAGE;
import static com.epam.drozdyk.webshop.constant.Constant.Message.NONEXISTENT_USER_MESSAGE;
import static com.epam.drozdyk.webshop.constant.Constant.Parameter.EMAIL_PARAMETER;
import static com.epam.drozdyk.webshop.constant.Constant.Parameter.PASSWORD_PARAMETER;
import static com.epam.drozdyk.webshop.constant.Constant.ServletUrl.INDEX_SERVLET_URL;
import static com.epam.drozdyk.webshop.constant.Constant.ServletUrl.LOGIN_SERVLET_URL;

@WebServlet(LOGIN_SERVLET_URL)
public class LoginServlet extends BaseServlet {
    private LoginFormMapper formMapper;

    @Override
    public void init() throws ServletException {
        super.init();
        this.formMapper = new LoginFormMapper();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginForm loginForm = (LoginForm) req.getSession().getAttribute(LAST_LOGIN_FORM_ATTR);
        HashMap<String, String> errors = (HashMap<String, String>) req.getSession().getAttribute(LOGIN_ERRORS_ATTR);
        if (loginForm != null && errors != null) {
            req.setAttribute(LAST_LOGIN_FORM_ATTR, loginForm);
            req.setAttribute(LOGIN_ERRORS_ATTR, errors);
            req.getSession().removeAttribute(LAST_LOGIN_FORM_ATTR);
            req.getSession().removeAttribute(LOGIN_ERRORS_ATTR);
        }

        forwardToPage(LOGIN_JSP, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginForm loginForm = formMapper.mapForm(req);
        HashMap<String, String> errors = new HashMap<>();
        String email = loginForm.getEmail();
        String password = loginForm.getPassword();

        if (!userService.isExist(email)) {
            errors.put(EMAIL_PARAMETER, NONEXISTENT_USER_MESSAGE);
            req.getSession().setAttribute(LAST_LOGIN_FORM_ATTR, loginForm);
            req.getSession().setAttribute(LOGIN_ERRORS_ATTR, errors);

            redirectToServlet(LOGIN_SERVLET_URL, resp);
        } else if (userService.login(email, password)) {
            req.getSession().setAttribute(CURRENT_USER_ATTR, userService.getUser(email));

            redirectToServlet(INDEX_SERVLET_URL, resp);
        } else {
            errors.put(PASSWORD_PARAMETER, INVALID_PASSWORD_MESSAGE);
            req.getSession().setAttribute(LAST_LOGIN_FORM_ATTR, loginForm);
            req.getSession().setAttribute(LOGIN_ERRORS_ATTR, errors);

            redirectToServlet(LOGIN_SERVLET_URL, resp);
        }
    }
}
