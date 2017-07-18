package com.epam.drozdyk.webshop.mapper.form;

import com.epam.drozdyk.webshop.bean.form.PaymentForm;

import javax.servlet.http.HttpServletRequest;

import static com.epam.drozdyk.webshop.constant.Constant.Parameter.BANK_CARD_PARAMETER;
import static com.epam.drozdyk.webshop.constant.Constant.Parameter.DELIVERY_ADDRESS_PARAMETER;

public class PaymentFormMapper {

    public PaymentForm mapForm(HttpServletRequest req) {
        return PaymentForm.newBuilder()
                .setBankCard(req.getParameter(BANK_CARD_PARAMETER))
                .setDeliveryAddress(req.getParameter(DELIVERY_ADDRESS_PARAMETER))
                .build();
    }
}
