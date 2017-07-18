package com.epam.drozdyk.webshop.security;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

import static com.epam.drozdyk.webshop.constant.Constant.XmlElement.CONSTRAINT_XML_ELEMENT;
import static com.epam.drozdyk.webshop.constant.Constant.XmlElement.SECURITY_XML_ELEMENT;
import static javax.xml.bind.annotation.XmlAccessType.FIELD;

@XmlRootElement(name = SECURITY_XML_ELEMENT)
@XmlAccessorType(FIELD)
public class Constraints {
    @XmlElement(name = CONSTRAINT_XML_ELEMENT)
    private List<Constraint> constraints;

    public List<Constraint> getConstraints() {
        return constraints;
    }

    public void setConstraints(List<Constraint> constraints) {
        this.constraints = constraints;
    }
}
