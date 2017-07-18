package com.epam.drozdyk.webshop.mapper.form;

import com.epam.drozdyk.webshop.bean.form.LoginForm;

import javax.servlet.http.HttpServletRequest;

import static com.epam.drozdyk.webshop.constant.Constant.Parameter.EMAIL_PARAMETER;
import static com.epam.drozdyk.webshop.constant.Constant.Parameter.PASSWORD_PARAMETER;

public class LoginFormMapper {

    public LoginForm mapForm(HttpServletRequest req) {
        return LoginForm.newBuilder()
                .setEmail(req.getParameter(EMAIL_PARAMETER))
                .setPassword(req.getParameter(PASSWORD_PARAMETER))
                .build();
    }
}
