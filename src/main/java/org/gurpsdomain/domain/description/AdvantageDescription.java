package org.gurpsdomain.domain.description;

import org.gurpsdomain.domain.*;
import org.gurpsdomain.domain.description.predicate.ModifierDescriptionPredicate;
import org.gurpsdomain.domain.description.predicate.Name;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@XmlRootElement(name = "advantage")
@XmlAccessorType(XmlAccessType.FIELD)
public class AdvantageDescription implements Registerable<AdvantageDescription> {
    private String name;
    @XmlElement(name = "base_points")
    private int basePoints;
    @XmlElement(name = "levels", required = false)
    private Integer levels;
    @XmlElement(name = "points_per_level", required = false)
    private Integer pointsPerLevel;
    private String reference;
    @XmlElement(name = "modifier", required = false)
    private List<ModifierDescription> modifiers;
    @XmlElement(name = "attribute_bonus", required = false)
    private List<AttributeBonusDescription> attributeBonuses;

    private AdvantageDescription() {
        /* needed for JAXB */
    }

    public AdvantageDescription(String name, int basePoints, Integer levels, Integer pointsPerLevel, String pageReference, List<ModifierDescription> modifiers, List<AttributeBonusDescription> attributeBonuses) {
        this.name = name;
        this.basePoints = basePoints;
        this.levels = levels;
        this.pointsPerLevel = pointsPerLevel;
        this.reference = pageReference;
        this.modifiers = modifiers != null ? modifiers : Collections.emptyList();
        this.attributeBonuses = attributeBonuses != null ? attributeBonuses : Collections.emptyList();
    }

    public Advantage createAdvantage(List<String> modifierNames, List<String> attributeBonusAttributes, int levelAmount) {
        List<Modifier> modifiers = new ArrayList<>();
        for (String modifierName : modifierNames) {
            ModifierDescriptionPredicate predicate = Name.name(modifierName);
            if (hasModifier(predicate)) {
                modifiers.add(createModifier(predicate));
            }
        }
        List<AttributeBonus> attributeBonuses = new ArrayList<>();
        for (String attributeBonusAttribute : attributeBonusAttributes) {
//TODO
        }
        List<AdvantageLevel> advantageLevels = new ArrayList<>();
        for (int i = 0; i < levelAmount; i++) {
            advantageLevels.add(new AdvantageLevel(new Cost(pointsPerLevel, CostType.points)));
        }
        return new Advantage(name, basePoints, reference, modifiers, attributeBonuses, advantageLevels);
    }

    public void registerIn(Repository<AdvantageDescription> repository) {
        repository.register(name, this);
    }

    private boolean hasModifier(ModifierDescriptionPredicate predicate) {
        return modifiers.stream().anyMatch(predicate::isFulfilledBy);
    }

    private Modifier createModifier(ModifierDescriptionPredicate predicate) {
        return modifiers.stream().filter(predicate::isFulfilledBy).findAny().get().createModifier();
    }
}
