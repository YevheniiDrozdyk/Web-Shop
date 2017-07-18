package com.epam.drozdyk.webshop.captcha.provider;

import com.epam.drozdyk.webshop.captcha.ServerCaptcha;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public abstract class CaptchaService {
    private static final int DELAY_SEVEN_MINUTES = 7;

    private Map<String, ServerCaptcha> captchaMap;

    public CaptchaService() {
        captchaMap = new ConcurrentHashMap<>();
    }

    public ServerCaptcha generateCaptcha() {
        ServerCaptcha captcha = new ServerCaptcha();
        String captchaId = captcha.getId();
        startAutoDeleting(captchaId);
        captchaMap.put(captchaId, captcha);

        return captcha;
    }

    public abstract void placeCaptcha(ServerCaptcha captcha, HttpServletRequest req, HttpServletResponse resp);

    public BufferedImage getCaptchaImage(HttpServletRequest req) {
        ServerCaptcha captcha = getServerCaptcha(req);
        if (captcha != null) {
            return captcha.getImage();
        }

        return new ServerCaptcha().getImage();
    }

    public boolean verifyCaptcha(String answer, HttpServletRequest req) {
        ServerCaptcha captcha = getServerCaptcha(req);
        if (captcha != null) {
            captchaMap.remove(captcha.getId());

            return isActual(captcha) && captcha.isCorrect(answer);
        }

        return false;
    }

    protected abstract ServerCaptcha getServerCaptcha(HttpServletRequest req);

    protected ServerCaptcha getCaptcha(String captchaId) {
        return captchaMap.get(captchaId);
    }

    private boolean isActual(ServerCaptcha captcha) {
        return captcha.isActual(LocalDateTime.now());
    }

    private void startAutoDeleting(String captchaId) {
        Executors.newSingleThreadScheduledExecutor()
                .schedule(() -> {
                    Thread t = new Thread(() ->
                            captchaMap.remove(captchaId));
                    t.setDaemon(true);
                    t.start();
                }, DELAY_SEVEN_MINUTES, TimeUnit.MINUTES);
    }
}
