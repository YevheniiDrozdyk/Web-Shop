package com.epam.drozdyk.webshop.listener;

import com.epam.drozdyk.webshop.captcha.provider.CaptchaManager;
import com.epam.drozdyk.webshop.captcha.provider.CaptchaService;
import com.epam.drozdyk.webshop.database.TransactionHandler;
import com.epam.drozdyk.webshop.exception.MissingSecurityFileException;
import com.epam.drozdyk.webshop.localization.LocaleManager;
import com.epam.drozdyk.webshop.localization.provider.LocaleService;
import com.epam.drozdyk.webshop.repository.impl.*;
import com.epam.drozdyk.webshop.security.Constraints;
import com.epam.drozdyk.webshop.service.*;
import com.epam.drozdyk.webshop.service.impl.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

import static com.epam.drozdyk.webshop.constant.Constant.Attribute.*;
import static com.epam.drozdyk.webshop.constant.Constant.Message.CANNOT_OBTAIN_DATA_SOURCE;
import static com.epam.drozdyk.webshop.constant.Constant.Message.MISSING_SECURITY_FILE_MESSAGE;

public class ApplicationContextListener implements ServletContextListener {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        TransactionHandler transactionHandler = new TransactionHandler(obtainDataSource());
        UserService userService = new DefaultUserService(transactionHandler, new MySqlUserRepository());
        InstrumentService instrumentService = new DefaultInstrumentService(transactionHandler, new MySqlInstrumentRepository());
        CategoryService categoryService = new DefaultCategoryService(transactionHandler, new MySqlCategoryRepository());
        ProducerService producerService = new DefaultProducerService(transactionHandler, new MySqlProducerRepository());
        OrderService orderService = new DefaultOrderService(transactionHandler, new MySqlOrderRepository(), new MySqlOrderItemRepository());
        String captchaServiceKey = sce.getServletContext().getInitParameter(CAPTCHA_SERVICE_ATTR);
        CaptchaService captchaService = new CaptchaManager().getCaptchaService(captchaServiceKey);
        String localeServiceKey = sce.getServletContext().getInitParameter(LOCALE_SERVICE_ATTR);
        LocaleService localeService = new LocaleManager().getLocaleService(localeServiceKey);
        obtainSecurityConstraints(sce);

        sce.getServletContext().setAttribute(USER_SERVICE_ATTR, userService);
        sce.getServletContext().setAttribute(INSTRUMENT_SERVICE_ATTR, instrumentService);
        sce.getServletContext().setAttribute(CATEGORY_SERVICE_ATTR, categoryService);
        sce.getServletContext().setAttribute(PRODUCER_SERVICE_ATTR, producerService);
        sce.getServletContext().setAttribute(ORDER_SERVICE_ATTR, orderService);
        sce.getServletContext().setAttribute(CAPTCHA_SERVICE_ATTR, captchaService);
        sce.getServletContext().setAttribute(LOCALE_SERVICE_ATTR, localeService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        sce.getServletContext().removeAttribute(USER_SERVICE_ATTR);
        sce.getServletContext().removeAttribute(INSTRUMENT_SERVICE_ATTR);
        sce.getServletContext().removeAttribute(CATEGORY_SERVICE_ATTR);
        sce.getServletContext().removeAttribute(PRODUCER_SERVICE_ATTR);
        sce.getServletContext().removeAttribute(ORDER_SERVICE_ATTR);
        sce.getServletContext().removeAttribute(CAPTCHA_SERVICE_ATTR);
        sce.getServletContext().removeAttribute(LOCALE_SERVICE_ATTR);
    }

    private DataSource obtainDataSource() {
        DataSource ds = null;
        try {
            // Obtain an environment naming context
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            // Retrieve a data source
            ds = (DataSource) envContext.lookup("jdbc/gretong");
        } catch (NamingException e) {
            LOGGER.error(CANNOT_OBTAIN_DATA_SOURCE, e.getCause());
        }

        return ds;
    }

    private void obtainSecurityConstraints(ServletContextEvent sce) {
        String directory = sce.getServletContext().getInitParameter(SECURITY_DIRECTORY_ATTR);
        File file = new File(new StringBuilder(directory)
                .append(File.separator)
                .append(SECURITY_FILE_ATTR)
                .toString());
        if (!file.exists()) {
            throw new MissingSecurityFileException(MISSING_SECURITY_FILE_MESSAGE);
        }
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Constraints.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Constraints constraint = (Constraints) unmarshaller.unmarshal(file);
            sce.getServletContext().setAttribute(SECURITY_CONSTRAINTS_ATTR, constraint);
        } catch (JAXBException e) {
            LOGGER.error(e.getMessage(), e.getCause());
        }
    }
}
