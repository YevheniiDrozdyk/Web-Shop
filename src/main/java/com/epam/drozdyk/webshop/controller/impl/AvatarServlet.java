package com.epam.drozdyk.webshop.controller.impl;

import com.epam.drozdyk.webshop.bean.model.User;
import com.epam.drozdyk.webshop.controller.BaseServlet;
import org.apache.commons.io.FilenameUtils;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import static com.epam.drozdyk.webshop.constant.Constant.Attribute.*;
import static com.epam.drozdyk.webshop.constant.Constant.ServletUrl.AVATAR_SERVLET_URL;

@WebServlet(AVATAR_SERVLET_URL)
public class AvatarServlet extends BaseServlet {
    private static final String DEFAULT_USER_AVATAR = "static/images/default-avatar.png";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final User user = (User) req.getSession().getAttribute(CURRENT_USER_ATTR);
        final StringBuilder pathname = new StringBuilder();
        if (user.getAvatar() == null) {
            pathname.append(DEFAULT_USER_AVATAR);
        } else {
            final String directory = req.getServletContext().getInitParameter(IMG_DIRECTORY_ATTR);
            pathname.append(directory);
            pathname.append(AVATAR_DIRECTORY);
            pathname.append(user.getAvatar());
        }

        final File file = new File(pathname.toString());
        final BufferedImage avatar = ImageIO.read(file);
        final String extension = FilenameUtils.getExtension(file.getName());
        final OutputStream out = resp.getOutputStream();

        ImageIO.write(avatar, extension, out);
        out.close();
    }
}
