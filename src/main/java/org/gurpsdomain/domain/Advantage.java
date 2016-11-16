package org.gurpsdomain.domain;

import org.gurpsdomain.domain.calc.AdvantageCostAccumulator;

import java.util.ArrayList;
import java.util.List;

public class Advantage implements PageReference {
    private String name;
    private String pageReference;
    private int baseCost;
    private List<Modifier> modifiers;
    private Integer cost;

    public Advantage(String name, int cost, String pageReference) {
        this(name, cost, pageReference, new ArrayList<Modifier>());
    }

    public Advantage(String name, int baseCost, String pageReference, List<Modifier> modifiers) {
        this.name = name;
        this.baseCost = baseCost;
        this.pageReference = pageReference;
        this.modifiers = modifiers;
    }

    private int cost() {
        if (cost == null) {
            AdvantageCostAccumulator accumulator = new AdvantageCostAccumulator(baseCost);
            for (Modifier modifier : modifiers) {
                modifier.accumulateCost(accumulator);
            }
            cost = accumulator.cost();
        }
        return cost;
    }


    public void payCost(Points points) {
        points.addAdvantage(cost());
    }

    @Override
    public String getPageReference() {
        return pageReference;
    }
}
