package org.gurpsdomain.repositories.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Advantage {
    private String name;
    @XmlElement(name = "base_points")
    private int basePoints;
    @XmlElement(name = "points_per_level", required=false)
    private Integer pointsPerLevel;
    private String reference;
    @XmlElement(name = "modifier", required=false)
    private List<Modifier> modifiers;

    public Advantage() {
        /* Needed for Jaxb */
    }

    public String getName() {
        return name;
    }

    public int getBasePoints() {
        return basePoints;
    }

    public String getPageReference() {
        return reference;
    }

    public List<Modifier> getModifiers() {
        return modifiers;
    }
}
