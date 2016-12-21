package org.gurpsdomain.domain;

import org.gurpsdomain.domain.calc.AdvantageCostAccumulator;

import java.util.List;

public class LeveledAdvantage extends Advantage {
    private int level;
    private int pointsPerLevel;

    public LeveledAdvantage(String name, int baseCost, String pageReference, List<Modifier> modifiers, List<AttributeBonus> attributeBonuses, int level, int pointsPerLevel) {
        super(name, baseCost, pageReference, modifiers, attributeBonuses);
        this.level = level;
        this.pointsPerLevel = pointsPerLevel;
    }


    protected int cost() {
        return super.cost();
    }


    @Override
    protected void accumulateCost(AdvantageCostAccumulator accumulator) {
        super.accumulateCost(accumulator);
        accumulator.addPoints(level * pointsPerLevel);
    }
}
