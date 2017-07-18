package com.epam.drozdyk.webshop.localization.provider.impl;

import com.epam.drozdyk.webshop.localization.provider.LocaleService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

import static com.epam.drozdyk.webshop.constant.Constant.Attribute.LANGUAGE_TAG_ATTR;

public class SessionLocaleService implements LocaleService {

    @Override
    public Locale getLocale(HttpServletRequest req) {
        String languageTag = (String) req.getSession().getAttribute(LANGUAGE_TAG_ATTR);
        if (languageTag != null) {
            return Locale.forLanguageTag(languageTag);
        }

        return null;
    }

    @Override
    public void setLocale(HttpServletRequest req, HttpServletResponse resp, Locale locale) {
        req.getSession().setAttribute(LANGUAGE_TAG_ATTR, locale.toLanguageTag());
    }
}
