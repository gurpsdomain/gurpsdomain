package org.gurpsdomain.repositories.xml;

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
