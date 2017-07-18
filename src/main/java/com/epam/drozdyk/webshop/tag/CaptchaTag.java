package com.epam.drozdyk.webshop.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

import static com.epam.drozdyk.webshop.constant.Constant.Parameter.CAPTCHA_ID_PARAMETER;

public class CaptchaTag extends SimpleTagSupport {
    private String captchaId;

    public void setCaptchaId(String captchaId) {
        this.captchaId = captchaId;
    }

    @Override
    public void doTag() throws JspException, IOException {
        StringBuilder sb = new StringBuilder("<img src=\"captcha");
        if (captchaId.isEmpty()) {
            sb.append("\"/>");
        } else {
            sb.append("?");
            sb.append(CAPTCHA_ID_PARAMETER);
            sb.append("=");
            sb.append(captchaId);
            sb.append("\"/>");

            sb.append("<input name=\"");
            sb.append(CAPTCHA_ID_PARAMETER);
            sb.append("\" type=\"hidden\" value=\"");
            sb.append(captchaId);
            sb.append("\"/>");
        }

        getJspContext().getOut().println(sb.toString());
    }
}
