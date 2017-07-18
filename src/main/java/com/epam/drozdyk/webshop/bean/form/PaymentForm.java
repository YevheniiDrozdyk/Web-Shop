package com.epam.drozdyk.webshop.bean.form;

public class PaymentForm {
    private String bankCard;
    private String deliveryAddress;

    private PaymentForm() {

    }

    public static Builder newBuilder() {
        return new PaymentForm().new Builder();
    }

    public String getBankCard() {
        return bankCard;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public class Builder {

        private Builder() {

        }

        public Builder setBankCard(String bankCard) {
            PaymentForm.this.bankCard = bankCard;

            return this;
        }

        public Builder setDeliveryAddress(String deliveryAddress) {
            PaymentForm.this.deliveryAddress = deliveryAddress;

            return this;
        }

        public PaymentForm build() {
            return PaymentForm.this;
        }
    }
}
