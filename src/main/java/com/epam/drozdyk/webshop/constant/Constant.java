package com.epam.drozdyk.webshop.constant;

public interface Constant {

    interface ServletUrl {
        String INDEX_SERVLET_URL = "/index";
        String INDEX_HTML_SERVLET_URL = "/index.html";
        String REGISTRATION_SERVLET_URL = "/registration";
        String CAPTCHA_SERVLET_URL = "/captcha";
        String LOGIN_SERVLET_URL = "/login";
        String LOGOUT_SERVLET_URL = "/logout";
        String AVATAR_SERVLET_URL = "/avatar";
        String IMAGE_SERVLET_URL = "/image";
        String SHOW_LIST_SERVLET_URL = "/showList";
        String CHECKOUT_SERVLET_URL = "/checkout";
        String CART_SERVLET_URL = "/cart";
        String PLACE_ORDER_SERVLET_URL = "/placeOrder";
        String CONFIRM_ORDER_SERVLET_URL = "/confirmOrder";
    }

    interface JspName {
        String TEMPLATE_JSP_URL = "/WEB-INF/view/page-template.jsp";
        String INDEX_JSP = "index.jsp";
        String REGISTRATION_JSP = "registration.jsp";
        String LOGIN_JSP = "login.jsp";
        String LIST_JSP = "list.jsp";
        String CHECKOUT_JSP = "checkout.jsp";
        String CONFIRM_ORDER_JSP = "confirm-order.jsp";
        String ERROR_PAGE_JSP = "error-page.jsp";
    }

    interface Parameter {
        String FIRST_NAME_PARAMETER = "first_name";
        String LAST_NAME_PARAMETER = "last_name";
        String EMAIL_PARAMETER = "email_address";
        String PASSWORD_PARAMETER = "user_password";
        String RETYPE_PASSWORD_PARAMETER = "retype_password";
        String AGREEMENT_PARAMETER = "agreement";
        String CAPTCHA_PARAMETER = "captcha";
        String EXISTING_USER_PARAMETER = "existing_user";
        String CAPTCHA_ID_PARAMETER = "captcha_id";
        String AVATAR_PARAMETER = "avatar";
        String VENDOR_CODE_PARAMETER = "code";
        String CATEGORIES_IDS_LIST_PARAMETER = "categories";
        String PRODUCERS_IDS_LIST_PARAMETER = "producers";
        String ADAPTERS_LIST_PARAMETER = "adapters";
        String FROM_PRICE_PARAMETER = "from_price";
        String TO_PRICE_PARAMETER = "to_price";
        String ITEMS_LIMIT_PARAMETER = "limit";
        String SORTING_COLUMN_PARAMETER = "sorting_column";
        String SORTING_TYPE_PARAMETER = "sorting_type";
        String PAGE_PARAMETER = "page";
        String TOTAL_COST_PARAMETER = "total";
        String QUANTITY_ITEMS_PARAMETER = "quantity";
        String CHANGING_VALUE_PARAMETER = "value";
        String BANK_CARD_PARAMETER = "bank_card";
        String DELIVERY_ADDRESS_PARAMETER = "delivery_address";
        String DEFAULT_LANGUAGE_TAG_PARAMETER = "defaultLangTag";
        String AVAILABLE_LANGUAGE_TAGS_PARAMETER = "availableLangTags";
        String LANGUAGE_PARAMETER = "lang";
    }

    interface Attribute {
        String USER_SERVICE_ATTR = "userService";
        String INSTRUMENT_SERVICE_ATTR = "instrumentService";
        String CATEGORY_SERVICE_ATTR = "categoryService";
        String PRODUCER_SERVICE_ATTR = "producerService";
        String ORDER_SERVICE_ATTR = "orderService";
        String CURRENT_PAGE_ATTR = "currentPage";
        String REGISTRATION_ERRORS_ATTR = "regErrors";
        String LAST_REGISTRATION_FORM_ATTR = "lastRegForm";
        String CAPTCHA_SERVICE_ATTR = "captchaService";
        String CAPTCHA_OBJECT_ATTR = "captchaObject";
        String CAPTCHA_ID_ATTR = "captchaId";
        String LOGIN_ERRORS_ATTR = "loginErrors";
        String LAST_LOGIN_FORM_ATTR = "lastLoginForm";
        String CURRENT_USER_ATTR = "currentUser";
        String INSTRUMENT_FILTER_ATTR = "instrumentFilter";
        String INSTRUMENTS_LIST_ATTR = "instruments";
        String CATEGORIES_LIST_ATTR = "categories";
        String PRODUCERS_LIST_ATTR = "producers";
        String PAGE_COUNT_ATTR = "pageCount";
        String USER_CART_ATTR = "userCart";
        String USER_ORDER_ATTR = "userOrder";
        String LOCALE_SERVICE_ATTR = "localeService";
        String LANGUAGE_TAG_ATTR = "languageTag";
        String LOCALE_COOKIE_MAX_AGE_ATTR = "localeCookieMaxAge";
        String AVAILABLE_LOCALES_ATTR = "availableLocales";

        String IMG_DIRECTORY_ATTR = "imgDirectory";
        String AVATAR_DIRECTORY = "avatar\\";
        String INSTRUMENT_DIRECTORY = "instrument\\";

        String SECURITY_DIRECTORY_ATTR = "securityDir";
        String SECURITY_CONSTRAINTS_ATTR = "securityConstraints";
        String SECURITY_FILE_ATTR = "security.xml";
    }

    interface ContentType {
        String IMAGE_CONTENT_TYPE = "image/";
        String JSON_CONTENT_TYPE = "application/json";
    }

    interface ServiceStorage {
        String COOKIE_STORAGE = "cookie";
        String HIDDEN_FIELD_STORAGE = "hiddenField";
        String SESSION_STORAGE = "session";
    }

    interface Regex {
        String EMAIL_REGEX = "^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        String PASSWORD_REGEX = "^(?=.*\\d)(?=.*[a-zA-Z])[a-zA-Z0-9]{8,}$";
        String NAME_REGEX = "^[a-zA-Z\\-]{3,}$";
    }

    interface Message {
        /* Registration messages */
        String NAME_ERROR_MESSAGE = "Name should contain only symbols and minimum required length is 3";
        String EMAIL_ERROR_MESSAGE = "Email should have appropriate domain address";
        String PASSWORD_ERROR_MESSAGE = "Password should contain 8 characters (at least 1 Alphabet and 1 Number)";
        String RETYPE_PASSWORD_ERROR_MESSAGE = "Passwords should match";
        String AGREEMENT_ERROR_MESSAGE = "You should confirm agreement";
        String UPLOAD_FILE_MESSAGE = "Avatar file is uploaded to: ";
        String SESSION_CREATED_MESSAGE = "Session created: ";
        String SESSION_DESTROYED_MESSAGE = "Session destroyed: ";
        String FORMED_ORDER_MESSAGE = "Order was formed. Now waiting for payment.";
        String ACCEPTED_ORDER_MESSAGE = "Order was payment. Now now needs to deliver.";

        /* Error messages */
        String CAPTCHA_ERROR_MESSAGE = "Captcha is not correct";
        String EXISTING_USER_MESSAGE = "User with such email already exist";
        String CANNOT_FIND_CONNECTION_MESSAGE = "Cannot find any connection to database";
        String CANNOT_OBTAIN_DATA_SOURCE = "Cannot obtain data source";
        String UPLOAD_FILE_ERROR_MESSAGE = "Problems during file upload";
        String NONEXISTENT_USER_MESSAGE = "User with such email is not exist";
        String INVALID_PASSWORD_MESSAGE = "Entered password is invalid";
        String DEFAULT_LOCALE_ERROR_MESSAGE = "Default locale has an error";
        String AVAILABLE_LOCALES_ERROR_MESSAGE = "Available locales have errors";
        String INCORRECT_LOCALE_MESSAGE = "Locale is incorrect";
        String MISSING_SECURITY_FILE_MESSAGE = "Security file is missing";
    }

    interface DatabaseTable {
        String INSTRUMENT_TABLE_NAME = "instrument";
        String CATEGORY_TABLE_NAME = "category";
        String PRODUCER_TABLE_NAME = "producer";
    }

    interface DatabaseColumn {
        String ID_COLUMN = "id";
        String EMAIL_COLUMN = "email";
        String PASSWORD_COLUMN = "password";
        String ROLE_COLUMN = "role";

        String INSTRUMENT_ALL_COLUMNS = "instrument.*";
        String VENDOR_CODE_COLUMN = "vendor_code";
        String PRODUCER_COLUMN = "producer";
        String ADAPTER_COLUMN = "adapter";
        String PRICE_COLUMN = "price";
        String CATEGORY_COLUMN = "category";
        String IMAGE_COLUMN = "image";

        String NAME_COLUMN = "name";
        String CATEGORY_NAME_AS_CATEGORY_COLUMN = "category.name AS category";
        String PRODUCER_NAME_AS_PRODUCER_COLUMN = "producer.name AS producer";
        String ID_CATEGORY_COLUMN = "id_category";
        String ID_PRODUCER_COLUMN = "id_producer";

        String RECORD_COUNT_COLUMN = "count(*)";
    }

    interface DatabaseCondition {
        String INSTRUMENT_CATEGORY_JOIN_CONDITION = "instrument.id_category=category.id";
        String INSTRUMENT_PRODUCER_JOIN_CONDITION = "instrument.id_producer=producer.id";
    }

    interface SqlQuery {
        String SELECT_USER_BY_EMAIL = "SELECT * FROM user WHERE email=?";
        String INSERT_NEW_USER = "INSERT INTO user(first_name,last_name,email,password,avatar) VALUES(?,?,?,?,?)";

        String SELECT_INSTRUMENT_BY_VENDOR_CODE = "SELECT instrument.*, category.name AS category, producer.name AS producer\n" +
                "FROM instrument\n" +
                "INNER JOIN category ON instrument.id_category=category.id\n" +
                "INNER JOIN producer ON instrument.id_producer=producer.id\n" +
                "WHERE vendor_code LIKE ?";
        String SELECT_ALL_INSTRUMENTS = "SELECT instrument.*, category.name AS category, producer.name AS producer\n" +
                "FROM instrument\n" +
                "INNER JOIN category ON instrument.id_category=category.id\n" +
                "INNER JOIN producer ON instrument.id_producer=producer.id";

        String SELECT_ALL_CATEGORIES = "SELECT * FROM category";
        String SELECT_ALL_PRODUCERS = "SELECT * FROM producer";

        String INSERT_NEW_ORDER = "INSERT INTO `order`(`id`,`status`,`detailing`,`date`,`id_user`) VALUES(?,?,?,?,?)";
        String INSERT_NEW_ORDER_ITEM = "INSERT INTO order_instrument(id_order,id_instrument,quantity,price) VALUES(?,?,?,?)";
        String UPDATE_ORDER_STATUS = "UPDATE `order` SET `status`=? WHERE `id`=?";
    }

    interface SqlStatement {
        String SELECT_STATEMENT = "SELECT ";
        String FROM_STATEMENT = " FROM ";
        String INNER_JOIN_STATEMENT = " INNER JOIN ";
        String ON_STATEMENT = " ON ";
        String WHERE_STATEMENT = " WHERE ";
        String AND_STATEMENT = " AND ";
        String COUNT_STATEMENT = "COUNT(*)";
        String ORDER_BY_STATEMENT = " ORDER BY ";
        String ASC_SORTING_STATEMENT = "ASC";
        String DESC_SORTING_STATEMENT = "DESC";
        String LIMIT_STATEMENT = " LIMIT ";
        String OFFSET_STATEMENT = " OFFSET ";
        String IN_START_STATEMENT = " IN (";
        String IN_END_STATEMENT = ")";
        String LIKE_START_STATEMENT = " LIKE '%";
        String LIKE_END_STATEMENT = "%'";
        String NOT_LESS_THAN_STATEMENT = ">=";
        String NOT_MORE_THAN_STATEMENT = "<=";
        String PERCENT_STATEMENT = "%";
        String ALL_COLUMNS_STATEMENT = "*";
        String COMA_STATEMENT = ",";
        String WHITESPACE_STATEMENT = " ";
    }

    interface XmlElement {
        String CONSTRAINT_XML_ELEMENT = "constraint";
        String SECURITY_XML_ELEMENT = "security";
        String URK_PATTERN_ELEMENT = "url-pattern";
        String ROLE_ELEMENT = "role";
    }
}
