package org.gurpsdomain.domain;

import org.gurpsdomain.domain.calc.AdvantageCostAccumulator;

public class Modifier {
    private final String name;
    private final Cost cost;
    private final String reference;

    public Modifier(String name, Cost cost, String reference) {
        this.name = name;
        this.cost = cost;
        this.reference = reference;
    }

    public Cost getCost() {
        return cost;
    }

    public void accumulateCost(AdvantageCostAccumulator accumulator) {
        cost.accumulateCost(accumulator);
    }
}
