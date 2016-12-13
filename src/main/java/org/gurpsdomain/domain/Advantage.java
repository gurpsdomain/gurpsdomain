package org.gurpsdomain.domain;

import org.gurpsdomain.domain.calc.AdvantageCostAccumulator;

import java.util.ArrayList;
import java.util.List;

public class Advantage {
    private String name;
    private String pageReference;
    private int baseCost;
    private List<AdvantageLevel> levels;
    private List<Modifier> modifiers;
    private List<AttributeBonus> attributeBonuses;

    public Advantage(String name, int cost, String pageReference) {
        this(name, cost, pageReference, new ArrayList<Modifier>(), new ArrayList<AttributeBonus>(), new ArrayList<AdvantageLevel>());
    }

    public Advantage(String name, int baseCost, String pageReference, List<Modifier> modifiers, List<AttributeBonus> attributeBonuses, List<AdvantageLevel> levels) {
        this.name = name;
        this.baseCost = baseCost;
        this.pageReference = pageReference;
        this.modifiers = modifiers;
        this.attributeBonuses = attributeBonuses;
        this.levels = levels;
    }

    private int cost() {
        AdvantageCostAccumulator accumulator = new AdvantageCostAccumulator(baseCost);
        for (Modifier modifier : modifiers) {
            modifier.accumulateCost(accumulator);
        }
        for (AdvantageLevel level : levels) {
            level.accumulateCost(accumulator);
        }
        return accumulator.cost();
    }

    public void payCost(Points points) {
        points.addAdvantage(cost());
    }

    public void updateAttributes(Attributes attributes) {
        attributes.addHealthBonus(0);
    } //TODO implement correct logic
    // AdvantageDescription will need to contain info on bonuses so we can use these


}
