package com.epam.drozdyk.webshop.localization.provider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public interface LocaleService {

    Locale getLocale(HttpServletRequest req);

    void setLocale(HttpServletRequest req, HttpServletResponse resp, Locale locale);
}
