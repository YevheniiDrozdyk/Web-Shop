package com.epam.drozdyk.webshop.bean.model;

import com.epam.drozdyk.webshop.constant.UserRole;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class User implements Serializable {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String avatar;
    private UserRole role;

    private User() {

    }

    public static Builder newBuilder() {
        return new User().new Builder();
    }

    public long getId() {
        return id;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public UserRole getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return new EqualsBuilder()
                .append(id, user.id)
                .append(firstName, user.firstName)
                .append(lastName, user.lastName)
                .append(email, user.email)
                .append(password, user.password)
                .append(avatar, user.avatar)
                .append(role, user.role)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(firstName)
                .append(lastName)
                .append(email)
                .append(password)
                .append(avatar)
                .append(role)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("firstName", firstName)
                .append("lastName", lastName)
                .append("email", email)
                .append("password", password)
                .append("avatar", avatar)
                .append("role", role)
                .toString();
    }

    public class Builder {

        private Builder() {

        }

        public Builder setId(long id) {
            User.this.id = id;

            return this;
        }

        public Builder setFirstName(String firstName) {
            User.this.firstName = firstName;

            return this;
        }

        public Builder setLastName(String lastName) {
            User.this.lastName = lastName;

            return this;
        }

        public Builder setEmail(String email) {
            User.this.email = email;

            return this;
        }

        public Builder setPassword(String password) {
            User.this.password = password;

            return this;
        }

        public Builder setAvatar(String avatar) {
            User.this.avatar = avatar;

            return this;
        }

        public Builder setRole(String role) {
            User.this.role = UserRole.valueOf(role);

            return this;
        }

        public User build() {
            return User.this;
        }
    }
}
