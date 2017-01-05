package org.gurpsdomain.domain;

import org.gurpsdomain.domain.calc.AdvantageCostAccumulator;

import java.util.List;

public class LeveledAdvantage extends Advantage {
    private final int level;
    private final int pointsPerLevel;

    public LeveledAdvantage(String name, int baseCost, String pageReference, List<Modifier> modifiers, List<AttributeBonus> attributeBonuses, int level, int pointsPerLevel) {
        super(name, baseCost, pageReference, modifiers, attributeBonuses);
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


    void updateAttributes(Attributes attributes) {
        // TODO do not assume that all bonuses are leveled
        for (int index = 0; index < level; index++) {
            super.updateAttributes(attributes);
        }
    }
}
