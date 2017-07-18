package com.epam.drozdyk.webshop.mapper.form;

import com.epam.drozdyk.webshop.bean.form.RegistrationForm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static com.epam.drozdyk.webshop.constant.Constant.Parameter.*;

public class RegistrationFormMapper {

    public RegistrationForm mapForm(HttpServletRequest req) throws IOException, ServletException {
        return RegistrationForm.newBuilder()
                .setFirstName(req.getParameter(FIRST_NAME_PARAMETER))
                .setLastName(req.getParameter(LAST_NAME_PARAMETER))
                .setEmail(req.getParameter(EMAIL_PARAMETER))
                .setPassword(req.getParameter(PASSWORD_PARAMETER))
                .setRetypePassword(req.getParameter(RETYPE_PASSWORD_PARAMETER))
                .setCaptcha(req.getParameter(CAPTCHA_PARAMETER))
                .setAgree(req.getParameter(AGREEMENT_PARAMETER))
                .build();
    }
}
