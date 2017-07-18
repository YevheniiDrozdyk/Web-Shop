package com.epam.drozdyk.webshop.captcha.provider.impl;

import com.epam.drozdyk.webshop.captcha.ServerCaptcha;
import com.epam.drozdyk.webshop.captcha.provider.CaptchaService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.epam.drozdyk.webshop.constant.Constant.Attribute.CAPTCHA_ID_ATTR;

public class CookieCaptchaService extends CaptchaService {
    private static final int FIVE_MINUTES = 60 * 5;

    @Override
    public void placeCaptcha(ServerCaptcha captcha, HttpServletRequest req, HttpServletResponse resp) {
        Cookie cookie = new Cookie(CAPTCHA_ID_ATTR, captcha.getId());
        cookie.setMaxAge(FIVE_MINUTES);
        resp.addCookie(cookie);
    }

    @Override
    protected ServerCaptcha getServerCaptcha(HttpServletRequest req) {
        Cookie cookie = findCookie(CAPTCHA_ID_ATTR, req.getCookies());
        if (cookie != null) {
            String captchaId = cookie.getValue();

            return getCaptcha(captchaId);
        }

        return null;
    }

    private Cookie findCookie(String name, Cookie[] cookies) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(name)) {
                return cookie;
            }
        }

        return null;
    }
}
