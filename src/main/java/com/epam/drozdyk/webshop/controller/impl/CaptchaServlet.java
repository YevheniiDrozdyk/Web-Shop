package com.epam.drozdyk.webshop.controller.impl;

import com.epam.drozdyk.webshop.controller.BaseServlet;
import nl.captcha.servlet.CaptchaServletUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.drozdyk.webshop.constant.Constant.ServletUrl.CAPTCHA_SERVLET_URL;

@WebServlet(CAPTCHA_SERVLET_URL)
public class CaptchaServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CaptchaServletUtil.writeImage(resp, captchaService.getCaptchaImage(req));
    }
}
