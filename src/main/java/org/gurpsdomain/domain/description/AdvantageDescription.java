package org.gurpsdomain.domain.description;

import org.gurpsdomain.domain.Advantage;
import org.gurpsdomain.domain.Modifier;
import org.gurpsdomain.domain.PageReference;
import org.gurpsdomain.repositories.InMemoryAdvantageDescriptionRepository;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;

@XmlRootElement(name="advantage")
@XmlAccessorType(XmlAccessType.FIELD)
public class AdvantageDescription implements PageReference {
    private String name;
    @XmlElement(name="base_points")
    private int basePoints;
    @XmlElement(name="points_per_level", required=false)
    private Integer pointsPerLevel;
    private String reference;
    @XmlElement(name="modifier", required=false)
    private List <ModifierDescription> modifiers;

    private AdvantageDescription() {
        /* needed for JAXB */
    }

    public AdvantageDescription(String name, int basePoints, String pageReference, List<ModifierDescription> modifiers) {
        this.name = name;
        this.basePoints = basePoints;
        this.reference = pageReference;
        this.modifiers = modifiers != null ? modifiers : Collections.emptyList();
    }

    public Advantage createAdvantage() {
        return new Advantage(name, basePoints, reference);
    }

    @Override
    public String getPageReference() {
        return reference;
    }

    public void registerIn(InMemoryAdvantageDescriptionRepository repository) {
        repository.register(name, this);
    }

    public boolean hasModifier(String modifierName) {
        return modifiers.stream().anyMatch(m -> m.getName().equals(modifierName));
    }

    public Modifier createModifier(String modifierName) {
        return modifiers.stream().filter(m -> m.getName().equals(modifierName)).findAny().get().createModifier();
    }
}
