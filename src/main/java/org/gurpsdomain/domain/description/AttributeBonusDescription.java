package org.gurpsdomain.domain.description;

import org.gurpsdomain.domain.Attribute;
import org.gurpsdomain.domain.AttributeBonus;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "attribute_bonus")
@XmlAccessorType(XmlAccessType.FIELD)
public class AttributeBonusDescription {
    @XmlElement(name = "attribute")
    private String attribute;
    private AmountDescription amount;

    public AttributeBonusDescription() {
        /* needed by JAXB */
    }

    public AttributeBonusDescription(String attribute, String bonus) {
        this.attribute = attribute;
        this.amount = new AmountDescription(bonus);
    }

    public AttributeBonus createAttributeBonus() {
        if (amount.isLeveled()) {
            return new AttributeBonus(Attribute.fromDescription(attribute), amount.bonus);
        } else {
            return new AttributeBonus(Attribute.fromDescription(attribute), amount.bonus);
        }
    }

}
