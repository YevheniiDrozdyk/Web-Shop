package com.epam.drozdyk.webshop.controller.impl;

import com.epam.drozdyk.webshop.bean.form.RegistrationForm;
import com.epam.drozdyk.webshop.bean.model.User;
import com.epam.drozdyk.webshop.captcha.ServerCaptcha;
import com.epam.drozdyk.webshop.captcha.provider.CaptchaService;
import com.epam.drozdyk.webshop.mapper.form.RegistrationFormMapper;
import com.epam.drozdyk.webshop.mapper.model.impl.UserMapper;
import com.epam.drozdyk.webshop.service.UserService;
import com.epam.drozdyk.webshop.validator.RegistrationFormValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.LinkedHashMap;

import static com.epam.drozdyk.webshop.constant.Constant.Attribute.LAST_REGISTRATION_FORM_ATTR;
import static com.epam.drozdyk.webshop.constant.Constant.Attribute.REGISTRATION_ERRORS_ATTR;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationServletTest {
    @InjectMocks
    @Spy
    private RegistrationServlet registrationServlet;
    @Mock
    private RequestDispatcher requestDispatcher;
    @Mock
    private HttpServletRequest req;
    @Mock
    private HttpServletResponse resp;
    @Mock
    private HttpSession session;

    @Mock
    private UserService userService;
    @Mock
    private CaptchaService captchaService;
    @Mock
    private RegistrationFormMapper formMapper;
    @Mock
    private RegistrationFormValidator validator;
    @Mock
    private UserMapper userMapper;

    @Mock
    private ServerCaptcha captcha;
    @Mock
    private RegistrationForm form;
    @Mock
    private HashMap<String, String> errors;
    @Mock
    private User user;

    @Before
    public void setUp() throws Exception {
        when(req.getSession()).thenReturn(session);
        when(req.getRequestDispatcher(any())).thenReturn(requestDispatcher);

        when(formMapper.mapForm(req)).thenReturn(form);
        when(userMapper.mapUser(form)).thenReturn(user);
    }

    @Test
    public void doGetInitially() throws Exception {
        when(session.getAttribute(any())).thenReturn(null);
        when(captchaService.generateCaptcha()).thenReturn(captcha);

        registrationServlet.doGet(req, resp);

        verify(captchaService).placeCaptcha(captcha, req, resp);
    }

    @Test
    public void doGetAfterRedirectFromDoPostBecauseInvalidRegistration() throws Exception {
        when(session.getAttribute(LAST_REGISTRATION_FORM_ATTR)).thenReturn(form);
        when(session.getAttribute(REGISTRATION_ERRORS_ATTR)).thenReturn(errors);

        registrationServlet.doGet(req, resp);

        verify(req).setAttribute(LAST_REGISTRATION_FORM_ATTR, form);
        verify(req).setAttribute(REGISTRATION_ERRORS_ATTR, errors);
        verify(session).removeAttribute(LAST_REGISTRATION_FORM_ATTR);
        verify(session).removeAttribute(REGISTRATION_ERRORS_ATTR);
    }

    @Test
    public void doPostWithValidRegistrationForm() throws Exception {
        when(validator.validateForm(form)).thenReturn(new LinkedHashMap<>());
        when(captchaService.verifyCaptcha(form.getCaptcha(), req)).thenReturn(true);
        when(userService.isExist(user.getEmail())).thenReturn(false);
        doNothing().when(registrationServlet).loadUserAvatar(user, req);

        registrationServlet.doPost(req, resp);
    }

    @Test
    public void doPostWithInvalidRegistrationForm() throws Exception {
        when(validator.validateForm(form)).thenReturn(errors);
        when(captchaService.verifyCaptcha(form.getCaptcha(), req)).thenReturn(false);
        when(userService.isExist(user.getEmail())).thenReturn(true);

        registrationServlet.doPost(req, resp);

        verify(session).setAttribute(LAST_REGISTRATION_FORM_ATTR, form);
        verify(session).setAttribute(REGISTRATION_ERRORS_ATTR, errors);
    }
}