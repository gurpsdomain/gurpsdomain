package org.gurpsdomain.repositories.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Modifier {
    @XmlElement(name = "name")
    private String name;
    @XmlElement(name = "cost", required=false)
    private Cost cost;

    public String getName() {
        return name;
    }
}
