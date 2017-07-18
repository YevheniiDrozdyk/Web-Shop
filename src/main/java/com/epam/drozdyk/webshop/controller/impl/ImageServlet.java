package com.epam.drozdyk.webshop.controller.impl;

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

import static com.epam.drozdyk.webshop.constant.Constant.Attribute.IMG_DIRECTORY_ATTR;
import static com.epam.drozdyk.webshop.constant.Constant.Attribute.INSTRUMENT_DIRECTORY;
import static com.epam.drozdyk.webshop.constant.Constant.Parameter.VENDOR_CODE_PARAMETER;
import static com.epam.drozdyk.webshop.constant.Constant.ServletUrl.IMAGE_SERVLET_URL;

@WebServlet(IMAGE_SERVLET_URL)
public class ImageServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String vendorCode = req.getParameter(VENDOR_CODE_PARAMETER);
        final String directory = req.getServletContext().getInitParameter(IMG_DIRECTORY_ATTR);
        final StringBuilder pathname = new StringBuilder(directory)
                .append(INSTRUMENT_DIRECTORY)
                .append(instrumentService.getInstrument(vendorCode).getImage());

        final File file = new File(pathname.toString());
        final BufferedImage instrumentImage = ImageIO.read(file);
        final String extension = FilenameUtils.getExtension(file.getName());
        final OutputStream out = resp.getOutputStream();

        ImageIO.write(instrumentImage, extension, out);
        out.close();
    }
}
