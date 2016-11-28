package org.gurpsdomain.domain.description;

import org.gurpsdomain.domain.Modifier;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "attribute_bonus")
@XmlAccessorType(XmlAccessType.FIELD)
public class AttributeBonusDescription {
    @XmlElement(name = "attribute")
    private String attribute;
    @XmlElement(name = "amount")
    private int amount;

    public AttributeBonusDescription() {
        /* needed by JAXB */
    }

    public AttributeBonusDescription(String attribute, int amount) {
        this.attribute = attribute;
        this.amount = amount;
    }

}
