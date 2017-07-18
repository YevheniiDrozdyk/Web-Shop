package com.epam.drozdyk.webshop.captcha.provider.impl;

import com.epam.drozdyk.webshop.captcha.ServerCaptcha;
import com.epam.drozdyk.webshop.captcha.provider.CaptchaService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.epam.drozdyk.webshop.constant.Constant.Attribute.CAPTCHA_ID_ATTR;
import static com.epam.drozdyk.webshop.constant.Constant.Parameter.CAPTCHA_ID_PARAMETER;

public class HiddenFieldCaptchaService extends CaptchaService {

    @Override
    public void placeCaptcha(ServerCaptcha captcha, HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute(CAPTCHA_ID_ATTR, captcha.getId());
    }

    @Override
    protected ServerCaptcha getServerCaptcha(HttpServletRequest req) {
        String captchaId = req.getParameter(CAPTCHA_ID_PARAMETER);

        return getCaptcha(captchaId);
    }
}
