package com.epam.drozdyk.webshop.bean.form;

public class LoginForm {
    private String email;
    private String password;

    private LoginForm() {

    }

    public static Builder newBuilder() {
        return new LoginForm().new Builder();
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public class Builder {

        private Builder() {

        }

        public Builder setEmail(String email) {
            LoginForm.this.email = email;

            return this;
        }

        public Builder setPassword(String password) {
            LoginForm.this.password = password;

            return this;
        }

        public LoginForm build() {
            return LoginForm.this;
        }
    }
}
