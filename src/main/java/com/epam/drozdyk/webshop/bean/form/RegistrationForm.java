package com.epam.drozdyk.webshop.bean.form;

public class RegistrationForm {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String retypePassword;
    private String captcha;
    private String agreement;

    private RegistrationForm() {

    }

    public static Builder newBuilder() {
        return new RegistrationForm().new Builder();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRetypePassword() {
        return retypePassword;
    }

    public String getCaptcha() {
        return captcha;
    }

    public String getAgreement() {
        return agreement;
    }

    public class Builder {

        private Builder() {

        }

        public Builder setFirstName(String firstName) {
            RegistrationForm.this.firstName = firstName;

            return this;
        }

        public Builder setLastName(String lastName) {
            RegistrationForm.this.lastName = lastName;

            return this;
        }

        public Builder setEmail(String email) {
            RegistrationForm.this.email = email;

            return this;
        }

        public Builder setPassword(String password) {
            RegistrationForm.this.password = password;

            return this;
        }

        public Builder setRetypePassword(String retypePassword) {
            RegistrationForm.this.retypePassword = retypePassword;

            return this;
        }

        public Builder setCaptcha(String captcha) {
            RegistrationForm.this.captcha = captcha;

            return this;
        }

        public Builder setAgree(String agreement) {
            RegistrationForm.this.agreement = agreement;

            return this;
        }

        public RegistrationForm build() {
            return RegistrationForm.this;
        }
    }
}
