package org.gurpsdomain.domain;

import org.gurpsdomain.domain.calc.AdvantageCostAccumulator;

import java.util.List;
import java.util.stream.Collectors;

public class Advantage {
    private final String name;
    private final String pageReference;
    private final int baseCost;
    private final List<Modifier> modifiers;
    private final List<AttributeBonus> attributeBonuses;
    private final List<SkillBonus> skillBonuses;
    private final List<DamageResistanceBonus> damageResistanceBonuses;

    public Advantage(String name, int baseCost, String pageReference, List<Modifier> modifiers, List<AttributeBonus> attributeBonuses, List<SkillBonus> skillBonuses, List<DamageResistanceBonus> damageResistanceBonuses) {
        this.name = name;
        this.baseCost = baseCost;
        this.pageReference = pageReference;
        this.modifiers = modifiers;
        this.attributeBonuses = attributeBonuses;
        this.skillBonuses = skillBonuses;
        this.damageResistanceBonuses = damageResistanceBonuses;
    }

    void payCost(Points points) {
        points.addAdvantage(cost());
    }

    protected int cost() {
        AdvantageCostAccumulator accumulator = new AdvantageCostAccumulator(baseCost);
        accumulateCost(accumulator);
        return accumulator.cost();
    }

    protected void accumulateCost(AdvantageCostAccumulator accumulator) {
        for (Modifier modifier : modifiers) {
            modifier.accumulateCost(accumulator);
        }
    }

    void updateAttributes(Attributes attributes) {
        List<AttributeBonus> regularAttributeBonuses = attributeBonuses.stream().filter(b -> !b.isLeveled()).collect(Collectors.toList());
        updateRegularAttributes(attributes, regularAttributeBonuses);
        List<AttributeBonus> leveledAttributeBonuses = attributeBonuses.stream().filter(AttributeBonus::isLeveled).collect(Collectors.toList());
        updateLeveledAttributes(attributes, leveledAttributeBonuses);
    }

    protected void updateRegularAttributes(Attributes attributes, List<AttributeBonus> bonuses) {
        for (AttributeBonus attributeBonus : bonuses) {
            attributeBonus.applyTo(attributes);
        }
    }

    protected void updateLeveledAttributes(Attributes attributes, List<AttributeBonus> regularAttributeBonuses) {
        /* should be overridden in LeveledAdvantage */
    }

    void updateSkills(List<Skill> skills) {
        for (Skill skill : skills) {
            for (SkillBonus skillBonus : skillBonuses) {
                skillBonus.applyTo(skill);
            }
        }
    }

    protected void updateRegularDamageResistances(DamageResistances damageResistances, List<DamageResistanceBonus> bonuses) {
        for (DamageResistanceBonus damageResistanceBonus : bonuses) {
            damageResistanceBonus.applyTo(damageResistances);
        }
    }

    protected void updateLeveledDamageResistances(DamageResistances damageResistances, List<DamageResistanceBonus> regularDamageResistanceBonuses) {
        /* should be overridden in LeveledAdvantage */
    }

    void updateDamageResistances(DamageResistances damageResistances) {
        List<DamageResistanceBonus> regularDamageResistanceBonuses = damageResistanceBonuses.stream().filter(b -> !b.isLeveled()).collect(Collectors.toList());
        updateRegularDamageResistances(damageResistances, regularDamageResistanceBonuses);
        List<DamageResistanceBonus> leveledDamageResistanceBonuses = damageResistanceBonuses.stream().filter(DamageResistanceBonus::isLeveled).collect(Collectors.toList());
        updateLeveledDamageResistances(damageResistances, leveledDamageResistanceBonuses);
    }
}
