package org.gurpsdomain.domain.description;

import org.gurpsdomain.domain.Cost;

import javax.xml.bind.annotation.*;

@XmlRootElement(name="cost")
@XmlAccessorType(XmlAccessType.FIELD)
public class CostDescription {
    @XmlAttribute(name = "type")
    private CostType type;

    @XmlValue
    private int value;

    public CostDescription() {
        /* needed by JAXB */
    }

    public CostDescription(int value, CostType type) {
        this.value = value;
        this.type = type;
    }

    public Cost createCost() {
        return new Cost(value, type);
    }
}

