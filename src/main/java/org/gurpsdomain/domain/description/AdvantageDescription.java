package org.gurpsdomain.domain.description;

import org.gurpsdomain.domain.Advantage;
import org.gurpsdomain.domain.PageReference;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name="advantage")
@XmlAccessorType(XmlAccessType.FIELD)
public class AdvantageDescription implements PageReference {
    public String name;
    @XmlElement(name="base_points")
    public int basePoints;
    @XmlElement(name="points_per_level", required=false)
    public Integer pointsPerLevel;
    public String reference;
    @XmlElement(name="modifier", required=false)
    public List <ModifierDescription> modifiers;

    private AdvantageDescription() {
        /* needed for JAXB */
    }

    public AdvantageDescription(String name, int basePoints, String pageReference) {
        this(name, basePoints, pageReference, new ArrayList<ModifierDescription>());
    }

    public AdvantageDescription(String name, int basePoints, String pageReference, List<ModifierDescription> modifiers) {
        this.name = name;
        this.basePoints = basePoints;
        this.reference = pageReference;
        this.modifiers = modifiers != null ? modifiers : new ArrayList<ModifierDescription>();
    }

    public Advantage createAdvantage() {
        return new Advantage(name, basePoints, reference);
    }

    @Override
    public String getPageReference() {
        return reference;
    }
}
