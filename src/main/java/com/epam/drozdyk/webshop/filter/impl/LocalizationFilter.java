package com.epam.drozdyk.webshop.filter.impl;

import com.epam.drozdyk.webshop.exception.IncorrectLocaleException;
import com.epam.drozdyk.webshop.filter.BaseFilter;
import com.epam.drozdyk.webshop.localization.provider.LocaleService;
import org.apache.commons.lang3.LocaleUtils;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static com.epam.drozdyk.webshop.constant.Constant.Attribute.AVAILABLE_LOCALES_ATTR;
import static com.epam.drozdyk.webshop.constant.Constant.Attribute.LOCALE_SERVICE_ATTR;
import static com.epam.drozdyk.webshop.constant.Constant.Message.*;
import static com.epam.drozdyk.webshop.constant.Constant.Parameter.*;

public class LocalizationFilter extends BaseFilter {
    private LocaleService localeService;
    private Locale defaultLocale;
    private List<Locale> availableLocales = new ArrayList<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        localeService = (LocaleService) filterConfig.getServletContext().getAttribute(LOCALE_SERVICE_ATTR);
        initDefaultLocale(filterConfig);
        initAvailableLocales(filterConfig);
    }

    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        Locale locale = getLocale(req);
        localeService.setLocale(req, resp, locale);

        chain.doFilter(new WrappedHttpServletRequest(req, locale), resp);
    }

    private void initDefaultLocale(FilterConfig filterConfig) {
        String defaultLanguageTag = filterConfig.getInitParameter(DEFAULT_LANGUAGE_TAG_PARAMETER);
        Objects.requireNonNull(defaultLanguageTag, DEFAULT_LOCALE_ERROR_MESSAGE);
        defaultLocale = Locale.forLanguageTag(defaultLanguageTag);
    }

    private void initAvailableLocales(FilterConfig filterConfig) {
        String availableLanguageTags = filterConfig.getInitParameter(AVAILABLE_LANGUAGE_TAGS_PARAMETER);
        Objects.requireNonNull(availableLanguageTags, AVAILABLE_LOCALES_ERROR_MESSAGE);
        String[] languageTags = availableLanguageTags.split(",");
        for (String languageTag : languageTags) {
            Locale locale = Locale.forLanguageTag(languageTag);
            if (LocaleUtils.isAvailableLocale(locale)) {
                availableLocales.add(locale);
            } else {
                throw new IncorrectLocaleException(INCORRECT_LOCALE_MESSAGE);
            }
        }

        filterConfig.getServletContext().setAttribute(AVAILABLE_LOCALES_ATTR, availableLocales);
    }

    private Locale getLocale(HttpServletRequest req) {
        Locale locale = getRequestedLocale(req);
        if (locale == null || !isAvailable(locale)) {
            locale = localeService.getLocale(req);
        }
        if (locale == null) {
            locale = getBrowserLocale(req);
        }
        if (locale == null) {
            locale = defaultLocale;
        }

        return locale;
    }

    private Locale getRequestedLocale(HttpServletRequest req) {
        Locale locale = null;
        String languageTag = req.getParameter(LANGUAGE_PARAMETER);
        if (languageTag != null) {
            locale = Locale.forLanguageTag(languageTag);
        }

        return locale;
    }

    private boolean isAvailable(Locale locale) {
        return availableLocales.contains(locale);
    }

    private Locale getBrowserLocale(HttpServletRequest req) {
        Enumeration<Locale> locales = req.getLocales();
        if (locales != null) {
            while (locales.hasMoreElements()) {
                Locale locale = locales.nextElement();
                if (availableLocales.contains(locale)) {
                    return locale;
                }
            }
        }

        return null;
    }

    private class WrappedHttpServletRequest extends HttpServletRequestWrapper {
        private Locale locale;

        private WrappedHttpServletRequest(HttpServletRequest request, Locale locale) {
            super(request);
            this.locale = locale;
        }

        @Override
        public Locale getLocale() {
            return locale;
        }

        @Override
        public Enumeration<Locale> getLocales() {
            return Collections.enumeration(availableLocales);
        }
    }
}
