package com.epam.drozdyk.webshop.captcha.provider.impl;

import com.epam.drozdyk.webshop.captcha.ServerCaptcha;
import com.epam.drozdyk.webshop.captcha.provider.CaptchaService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.epam.drozdyk.webshop.constant.Constant.Attribute.CAPTCHA_OBJECT_ATTR;

public class SessionCaptchaService extends CaptchaService {

    @Override
    public void placeCaptcha(ServerCaptcha captcha, HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().setAttribute(CAPTCHA_OBJECT_ATTR, captcha);
    }

    @Override
    protected ServerCaptcha getServerCaptcha(HttpServletRequest req) {
        return (ServerCaptcha) req.getSession().getAttribute(CAPTCHA_OBJECT_ATTR);
    }
}
