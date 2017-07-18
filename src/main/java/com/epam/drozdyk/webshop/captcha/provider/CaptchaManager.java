package com.epam.drozdyk.webshop.captcha.provider;

import com.epam.drozdyk.webshop.captcha.provider.impl.CookieCaptchaService;
import com.epam.drozdyk.webshop.captcha.provider.impl.HiddenFieldCaptchaService;
import com.epam.drozdyk.webshop.captcha.provider.impl.SessionCaptchaService;

import java.util.HashMap;

import static com.epam.drozdyk.webshop.constant.Constant.ServiceStorage.*;

public class CaptchaManager {
    private HashMap<String, CaptchaService> captchaServices;

    public CaptchaManager() {
        initiateCaptchaServices();
    }

    public CaptchaService getCaptchaService(String key) {
        CaptchaService captchaService = captchaServices.get(key);
        if (captchaService == null) {
            captchaService = new SessionCaptchaService();
        }

        return captchaService;
    }

    private void initiateCaptchaServices() {
        captchaServices = new HashMap<>();
        captchaServices.put(COOKIE_STORAGE, new CookieCaptchaService());
        captchaServices.put(HIDDEN_FIELD_STORAGE, new HiddenFieldCaptchaService());
        captchaServices.put(SESSION_STORAGE, new SessionCaptchaService());
    }
}
