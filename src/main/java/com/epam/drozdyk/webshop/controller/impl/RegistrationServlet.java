package com.epam.drozdyk.webshop.controller.impl;

import com.epam.drozdyk.webshop.bean.form.RegistrationForm;
import com.epam.drozdyk.webshop.bean.model.User;
import com.epam.drozdyk.webshop.captcha.ServerCaptcha;
import com.epam.drozdyk.webshop.constant.Constant;
import com.epam.drozdyk.webshop.controller.BaseServlet;
import com.epam.drozdyk.webshop.mapper.form.RegistrationFormMapper;
import com.epam.drozdyk.webshop.mapper.model.impl.UserMapper;
import com.epam.drozdyk.webshop.validator.RegistrationFormValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.util.HashMap;

import static com.epam.drozdyk.webshop.constant.Constant.Attribute.*;
import static com.epam.drozdyk.webshop.constant.Constant.ContentType.IMAGE_CONTENT_TYPE;
import static com.epam.drozdyk.webshop.constant.Constant.JspName.REGISTRATION_JSP;
import static com.epam.drozdyk.webshop.constant.Constant.Message.*;
import static com.epam.drozdyk.webshop.constant.Constant.Parameter.*;
import static com.epam.drozdyk.webshop.constant.Constant.ServletUrl.INDEX_SERVLET_URL;
import static com.epam.drozdyk.webshop.constant.Constant.ServletUrl.REGISTRATION_SERVLET_URL;

@WebServlet(REGISTRATION_SERVLET_URL)
@MultipartConfig
public class RegistrationServlet extends BaseServlet {
    private static final Logger LOGGER = LogManager.getLogger();

    private RegistrationFormMapper formMapper;
    private RegistrationFormValidator formValidator;
    private UserMapper userMapper;

    @Override
    public void init() throws ServletException {
        super.init();
        this.formMapper = new RegistrationFormMapper();
        this.formValidator = new RegistrationFormValidator();
        this.userMapper = new UserMapper();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RegistrationForm form = (RegistrationForm) req.getSession().getAttribute(LAST_REGISTRATION_FORM_ATTR);
        HashMap<String, String> errors = (HashMap<String, String>) req.getSession().getAttribute(REGISTRATION_ERRORS_ATTR);
        if (form != null && errors != null) {
            req.setAttribute(LAST_REGISTRATION_FORM_ATTR, form);
            req.setAttribute(REGISTRATION_ERRORS_ATTR, errors);
            req.getSession().removeAttribute(LAST_REGISTRATION_FORM_ATTR);
            req.getSession().removeAttribute(REGISTRATION_ERRORS_ATTR);
        }
        ServerCaptcha serverCaptcha = captchaService.generateCaptcha();
        captchaService.placeCaptcha(serverCaptcha, req, resp);

        forwardToPage(REGISTRATION_JSP, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RegistrationForm form = formMapper.mapForm(req);
        HashMap<String, String> errors = formValidator.validateForm(form);
        if (!captchaService.verifyCaptcha(form.getCaptcha(), req)) {
            errors.put(CAPTCHA_PARAMETER, CAPTCHA_ERROR_MESSAGE);
        }
        if (userService.isExist(form.getEmail())) {
            errors.put(EXISTING_USER_PARAMETER, EXISTING_USER_MESSAGE);
        }
        if (errors.isEmpty()) {
            User user = userMapper.mapUser(form);
            loadUserAvatar(user, req);
            userService.register(user);

            redirectToServlet(INDEX_SERVLET_URL, resp);
        } else {
            req.getSession().setAttribute(LAST_REGISTRATION_FORM_ATTR, form);
            req.getSession().setAttribute(REGISTRATION_ERRORS_ATTR, errors);

            redirectToServlet(REGISTRATION_SERVLET_URL, resp);
        }
    }

    void loadUserAvatar(User user, HttpServletRequest req) throws IOException, ServletException {
        final String directory = req.getServletContext().getInitParameter(IMG_DIRECTORY_ATTR);
        final StringBuilder pathname = new StringBuilder(directory)
                .append(Constant.Attribute.AVATAR_DIRECTORY);
        final Part filePart = req.getPart(AVATAR_PARAMETER);
        final String contentType = filePart.getContentType();

        if (contentType.startsWith(IMAGE_CONTENT_TYPE)) {
            final String extension = contentType.substring(contentType.indexOf("/") + 1);
            final StringBuilder fileName = new StringBuilder(user.getEmail())
                    .append(".")
                    .append(extension);
            pathname.append(fileName);
            user.setAvatar(fileName.toString());
        } else {
            return;
        }

        OutputStream out = null;
        InputStream fileContent = null;
        try {
            out = new FileOutputStream(new File(pathname.toString()));
            fileContent = filePart.getInputStream();
            int read;
            final byte[] bytes = new byte[fileContent.available()];
            while ((read = fileContent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            LOGGER.info(UPLOAD_FILE_MESSAGE, pathname);
        } catch (FileNotFoundException e) {
            LOGGER.error(UPLOAD_FILE_ERROR_MESSAGE, e.getMessage());
        } finally {
            if (out != null) {
                out.close();
            }
            if (fileContent != null) {
                fileContent.close();
            }
        }
    }
}
