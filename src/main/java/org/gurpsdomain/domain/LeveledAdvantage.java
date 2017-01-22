package org.gurpsdomain.domain;

import org.gurpsdomain.domain.calc.AdvantageCostAccumulator;

import java.util.List;

public class LeveledAdvantage extends Advantage {
    private final int level;
    private final int pointsPerLevel;

    public LeveledAdvantage(String name, int baseCost, String pageReference, List<Modifier> modifiers, List<AttributeBonus> attributeBonuses, List<SkillBonus> skillBonuses, List<DamageResistanceBonus> damageResistanceBonuses, int level, int pointsPerLevel) {
        super(name, baseCost, pageReference, modifiers, attributeBonuses, skillBonuses, damageResistanceBonuses);
        if (level < 1) {
            throw new IllegalArgumentException("Advantage level for " + name + " should be greater than 0 but was " + level);
        }
        this.level = level;
        this.pointsPerLevel = pointsPerLevel;
    }

    @Override
    protected void accumulateCost(AdvantageCostAccumulator accumulator) {
        super.accumulateCost(accumulator);
        accumulator.addPoints(level * pointsPerLevel);
    }

    @Override
    protected void updateLeveledAttributes(Attributes attributes, List<AttributeBonus> bonuses) {
        for (int index = 0; index < level; index++) {
            super.updateRegularAttributes(attributes, bonuses);
        }
    }

    @Override
    protected void updateLeveledDamageResistances(DamageResistances damageResistances, List<DamageResistanceBonus> bonuses) {
        for (int index = 0; index < level; index++) {
            super.updateRegularDamageResistances(damageResistances, bonuses);
        }
    }
}
