package org.gurpsdomain.domain;

import org.gurpsdomain.domain.calc.AdvantageCostAccumulator;

public class Modifier {
    private String name;
    private Cost cost;

    public Modifier(String name, Cost cost) {
        this.name = name;
        this.cost = cost;
    }

    public Cost getCost() {
        return cost;
    }

    public void accumulateCost(AdvantageCostAccumulator accumulator) {
        cost.accumulateCost(accumulator);
    }
}
