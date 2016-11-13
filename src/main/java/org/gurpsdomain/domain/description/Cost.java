package org.gurpsdomain.domain.description;

import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Cost {
    @XmlAttribute(name = "type")
    private CostType type;

    @XmlValue
    private int value;

    public Cost() {
        /* needed by JAXB */
    }

    public Cost(int value, CostType type) {
        this.value = value;
        this.type = type;
    }
}

