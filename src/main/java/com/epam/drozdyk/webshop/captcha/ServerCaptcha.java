package com.epam.drozdyk.webshop.captcha;

import nl.captcha.Captcha;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

public class ServerCaptcha {
    private static final int CAPTCHA_WIDTH = 150;
    private static final int CAPTCHA_HEIGHT = 75;
    private static final int FIVE_MINUTES = 5;

    private final String id;
    private final Captcha captcha;
    private final LocalDateTime creationTime;

    public ServerCaptcha() {
        this.id = generateId();
        this.captcha = buildCaptcha();
        this.creationTime = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public BufferedImage getImage() {
        return captcha.getImage();
    }

    public boolean isCorrect(String answer) {
        return captcha.isCorrect(answer);
    }

    public boolean isActual(LocalDateTime currentTime) {
        return creationTime.until(currentTime, ChronoUnit.MINUTES) < FIVE_MINUTES;
    }

    private String generateId() {
        return UUID.randomUUID().toString();
    }

    private Captcha buildCaptcha() {
        return new Captcha.Builder(CAPTCHA_WIDTH, CAPTCHA_HEIGHT)
                .addText()
                .addBackground()
                .build();
    }
}
