package org.gurpsdomain.domain.description;

import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Cost {
    @XmlAttribute(name = "type")
    CostType type;

    @XmlValue
    int value;
}

enum CostType {
    percentage, points;
}
