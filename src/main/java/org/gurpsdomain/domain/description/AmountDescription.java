package org.gurpsdomain.domain.description;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "amount")
@XmlAccessorType(XmlAccessType.FIELD)
public class AmountDescription {
    @XmlAttribute(name = "per_level")
    private String perLevel;
    @XmlValue
    public String bonus;

    public AmountDescription() {
        /* Needed by JAXB */
    }

    public AmountDescription(String bonus) {
        this.bonus = bonus;
    }

    public boolean isLeveled() {
        return perLevel != null && perLevel.equals("yes");
    }
}
