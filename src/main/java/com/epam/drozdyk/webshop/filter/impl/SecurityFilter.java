package com.epam.drozdyk.webshop.filter.impl;

import com.epam.drozdyk.webshop.bean.model.User;
import com.epam.drozdyk.webshop.constant.UserRole;
import com.epam.drozdyk.webshop.filter.BaseFilter;
import com.epam.drozdyk.webshop.security.Constraint;
import com.epam.drozdyk.webshop.security.Constraints;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.drozdyk.webshop.constant.Constant.Attribute.*;
import static com.epam.drozdyk.webshop.constant.Constant.JspName.*;

public class SecurityFilter extends BaseFilter {
    private Constraints constraints;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        constraints = (Constraints) filterConfig.getServletContext().getAttribute(SECURITY_CONSTRAINTS_ATTR);
    }

    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        User user = (User) req.getSession().getAttribute(CURRENT_USER_ATTR);
        String path = req.getServletPath();
        if (isSecure(path)) {
            if (user == null) {
                forwardToPage(LOGIN_JSP, req, resp);
                return;
            }
            if (!isAccessible(path, user.getRole())) {
                forwardToPage(ERROR_PAGE_JSP, req, resp);
                return;
            }
        }
        chain.doFilter(req, resp);
    }

    private boolean isSecure(String url) {
        for (Constraint constraint : constraints.getConstraints()) {
            if (url.matches(constraint.getUrlPattern())) {
                return true;
            }
        }

        return false;
    }

    private void forwardToPage(String page, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(CURRENT_PAGE_ATTR, page);
        req.getRequestDispatcher(TEMPLATE_JSP_URL).forward(req, resp);
    }

    private boolean isAccessible(String url, UserRole role) {
        for (Constraint constraint : constraints.getConstraints()) {
            if (url.matches(constraint.getUrlPattern()) && constraint.getRoles().contains(role.name().toLowerCase())) {
                return true;
            }

        }

        return false;
    }
}
