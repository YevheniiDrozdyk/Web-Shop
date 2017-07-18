package com.epam.drozdyk.webshop.localization.provider.impl;

import com.epam.drozdyk.webshop.localization.provider.LocaleService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

import static com.epam.drozdyk.webshop.constant.Constant.Attribute.LANGUAGE_TAG_ATTR;
import static com.epam.drozdyk.webshop.constant.Constant.Attribute.LOCALE_COOKIE_MAX_AGE_ATTR;

public class CookieLocaleService implements LocaleService {

    @Override
    public Locale getLocale(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (LANGUAGE_TAG_ATTR.equals(cookie.getName())) {
                    return Locale.forLanguageTag(cookie.getValue());
                }
            }
        }

        return null;
    }

    @Override
    public void setLocale(HttpServletRequest req, HttpServletResponse resp, Locale locale) {
        Cookie cookie = new Cookie(LANGUAGE_TAG_ATTR, locale.toLanguageTag());
        int expiration = Integer.parseInt(req.getServletContext().getInitParameter(LOCALE_COOKIE_MAX_AGE_ATTR));
        cookie.setMaxAge(expiration * 60);

        resp.addCookie(cookie);
    }
}
