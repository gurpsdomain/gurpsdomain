package org.gurpsdomain.domain.description;

import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Cost {
    @XmlAttribute(name = "type")
    private CostType type;

    @XmlValue
    private int value;

}
