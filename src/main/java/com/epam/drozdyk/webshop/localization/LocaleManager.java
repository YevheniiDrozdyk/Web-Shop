package com.epam.drozdyk.webshop.localization;

import com.epam.drozdyk.webshop.localization.provider.LocaleService;
import com.epam.drozdyk.webshop.localization.provider.impl.CookieLocaleService;
import com.epam.drozdyk.webshop.localization.provider.impl.SessionLocaleService;

import java.util.HashMap;

import static com.epam.drozdyk.webshop.constant.Constant.ServiceStorage.COOKIE_STORAGE;
import static com.epam.drozdyk.webshop.constant.Constant.ServiceStorage.SESSION_STORAGE;

public class LocaleManager {
    private HashMap<String, LocaleService> localeServices;

    public LocaleManager() {
        initiateLocaleServices();
    }

    public LocaleService getLocaleService(String key) {
        LocaleService localeService = localeServices.get(key);
        if (localeService == null) {
            localeService = new SessionLocaleService();
        }

        return localeService;
    }

    private void initiateLocaleServices() {
        localeServices = new HashMap<>();
        localeServices.put(COOKIE_STORAGE, new CookieLocaleService());
        localeServices.put(SESSION_STORAGE, new SessionLocaleService());
    }
}
