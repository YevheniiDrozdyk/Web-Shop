package com.epam.drozdyk.webshop.security;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

import static com.epam.drozdyk.webshop.constant.Constant.XmlElement.*;
import static javax.xml.bind.annotation.XmlAccessType.FIELD;

@XmlRootElement(name = CONSTRAINT_XML_ELEMENT)
@XmlAccessorType(FIELD)
public class Constraint {
    @XmlElement(name = URK_PATTERN_ELEMENT)
    private String urlPattern;
    @XmlElement(name = ROLE_ELEMENT)
    private List<String> roles;

    public String getUrlPattern() {
        return urlPattern;
    }

    public void setUrlPattern(String urlPattern) {
        this.urlPattern = urlPattern;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
