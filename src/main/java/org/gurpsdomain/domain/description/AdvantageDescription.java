package org.gurpsdomain.domain.description;

import org.gurpsdomain.domain.*;
import org.gurpsdomain.domain.description.predicate.ModifierDescriptionPredicate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@XmlRootElement(name = "advantage")
@XmlAccessorType(XmlAccessType.FIELD)
public class AdvantageDescription implements Registerable<AdvantageDescription> {
    @XmlElement(name = "name", required = true)
    private String name;
    @XmlElement(name = "base_points")
    private int basePoints;
    @XmlElement(name = "levels")
    private Integer levels;
    @XmlElement(name = "points_per_level")
    private Integer pointsPerLevel;
    private String reference;
    @XmlElement(name = "modifier")
    private List<ModifierDescription> modifiers;
    @XmlElement(name = "attribute_bonus")
    private List<AttributeBonusDescription> attributeBonusDescriptions;
    @XmlElement(name = "skill_bonus")
    private List<SkillBonusDescription> skillBonusDescriptions;
    @XmlElement(name = "dr_bonus")
    private List<DamageResistanceBonusDescription> damageResistanceBonusDescriptions;

    private AdvantageDescription() {
        /* needed for JAXB */
    }

    public AdvantageDescription(String name, int basePoints, Integer levels, Integer pointsPerLevel, String pageReference, List<ModifierDescription> modifiers, List<AttributeBonusDescription> attributeBonusDescriptions, List<SkillBonusDescription> skillBonusDescriptions, List<DamageResistanceBonusDescription> damageResistanceBonusDescriptions) {
        this.name = name;
        this.basePoints = basePoints;
        this.levels = levels;
        this.pointsPerLevel = pointsPerLevel;
        this.reference = pageReference;
        this.modifiers = modifiers != null ? modifiers : Collections.emptyList();
        this.attributeBonusDescriptions = attributeBonusDescriptions != null ? attributeBonusDescriptions : Collections.emptyList();
        this.skillBonusDescriptions = skillBonusDescriptions != null ? skillBonusDescriptions : Collections.emptyList();
        this.damageResistanceBonusDescriptions = damageResistanceBonusDescriptions != null ? damageResistanceBonusDescriptions : Collections.emptyList();

    }

    public Advantage createAdvantage(List<ModifierDescriptionPredicate> modifierDescriptionPredicates, int levelAmount) {
        List<Modifier> modifiers = modifierDescriptionPredicates
                .stream()
                .filter(this::hasModifier)
                .map(this::createModifier)
                .collect(Collectors.toList());

        List<AttributeBonus> attributeBonuses = attributeBonusDescriptions()
                .stream()
                .map(AttributeBonusDescription::createAttributeBonus)
                .collect(Collectors.toList());

        List<SkillBonus> skillBonuses = skillBonusDescriptions()
                .stream()
                .map(SkillBonusDescription::createSkillBonus)
                .collect(Collectors.toList());

        List<DamageResistanceBonus> damageResistanceBonuses = damageResistanceBonusDescriptions()
                .stream()
                .map(DamageResistanceBonusDescription::createDamageResistanceBonus)
                .collect(Collectors.toList());

        if (pointsPerLevel != null) {
            return new LeveledAdvantage(name, basePoints, reference, modifiers, attributeBonuses, skillBonuses, damageResistanceBonuses, levelAmount, pointsPerLevel);
        } else {
            return new Advantage(name, basePoints, reference, modifiers, attributeBonuses, skillBonuses, damageResistanceBonuses);
        }
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

    private List<AttributeBonusDescription> attributeBonusDescriptions() {
        if (attributeBonusDescriptions != null) {
            return attributeBonusDescriptions;
        } else {
            return Collections.emptyList();
        }
    }

    private List<SkillBonusDescription> skillBonusDescriptions() {
        if (skillBonusDescriptions != null) {
            return skillBonusDescriptions;
        } else {
            return Collections.emptyList();
        }
    }

    private List<DamageResistanceBonusDescription> damageResistanceBonusDescriptions() {
        if (damageResistanceBonusDescriptions != null) {
            return damageResistanceBonusDescriptions;
        } else {
            return Collections.emptyList();
        }
    }
}
