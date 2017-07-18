package com.epam.drozdyk.webshop.validator;

import com.epam.drozdyk.webshop.bean.form.RegistrationForm;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

import static com.epam.drozdyk.webshop.constant.Constant.Message.*;
import static com.epam.drozdyk.webshop.constant.Constant.Parameter.*;
import static com.epam.drozdyk.webshop.constant.Constant.Regex.*;

public class RegistrationFormValidator {

    public HashMap<String, String> validateForm(RegistrationForm form) {
        HashMap<String, String> errors = new LinkedHashMap<>();
        if (!validate(NAME_REGEX, form.getFirstName())) {
            errors.put(FIRST_NAME_PARAMETER, NAME_ERROR_MESSAGE);
        }
        if (!validate(NAME_REGEX, form.getLastName())) {
            errors.put(LAST_NAME_PARAMETER, NAME_ERROR_MESSAGE);
        }
        if (!validate(EMAIL_REGEX, form.getEmail())) {
            errors.put(EMAIL_PARAMETER, EMAIL_ERROR_MESSAGE);
        }
        if (!validate(PASSWORD_REGEX, form.getPassword())) {
            errors.put(PASSWORD_PARAMETER, PASSWORD_ERROR_MESSAGE);
        }
        if (!form.getRetypePassword().equals(form.getPassword())) {
            errors.put(RETYPE_PASSWORD_PARAMETER, RETYPE_PASSWORD_ERROR_MESSAGE);
        }
        if (form.getAgreement() == null) {
            errors.put(AGREEMENT_PARAMETER, AGREEMENT_ERROR_MESSAGE);
        }

        return errors;
    }

    private boolean validate(String pattern, String value) {
        return Pattern.compile(pattern).matcher(value).find();
    }
}
