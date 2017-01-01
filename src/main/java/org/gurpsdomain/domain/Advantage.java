package org.gurpsdomain.domain;

import org.gurpsdomain.domain.calc.AdvantageCostAccumulator;

import java.util.List;

public class Advantage {
    private final String name;
    private final String pageReference;
    private final int baseCost;
    private final List<Modifier> modifiers;
    private final List<AttributeBonus> attributeBonuses;

    public Advantage(String name, int baseCost, String pageReference, List<Modifier> modifiers, List<AttributeBonus> attributeBonuses) {
        this.name = name;
        this.baseCost = baseCost;
        this.pageReference = pageReference;
        this.modifiers = modifiers;
        this.attributeBonuses = attributeBonuses;
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
        attributes.addHealthBonus(0);
    } //TODO implement correct logic
    // AdvantageDescription will need to contain info on bonuses so we can use these
}
