package org.gurpsdomain.domain.description;

import org.gurpsdomain.domain.Attribute;
import org.gurpsdomain.domain.AttributeBonus;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "attribute_bonus")
@XmlAccessorType(XmlAccessType.FIELD)
public class AttributeBonusDescription {
    @XmlElement(name = "attribute")
    private String attribute;
    @XmlElement(name = "amount")
    private String bonus;
    @XmlAttribute(name = "per_level")
    private String perLevelString;

    public AttributeBonusDescription() {
        /* needed by JAXB */
    }

    public AttributeBonusDescription(String attribute, String bonus) {
        this.attribute = attribute;
        this.bonus = bonus;
    }

    public AttributeBonus createAttributeBonus() {
        return new AttributeBonus(Attribute.fromDescription(attribute), bonus);
    }

}
