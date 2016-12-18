package org.gurpsdomain.domain;

import org.gurpsdomain.domain.calc.AdvantageCostAccumulator;

public class Modifier {
    private final String name;
    private final Cost cost;
    private final String pageReference;

    public Modifier(String name, Cost cost, String pageReference) {
        this.name = name;
        this.cost = cost;
        this.pageReference = pageReference;
    }

    void accumulateCost(AdvantageCostAccumulator accumulator) {
        cost.accumulateCost(accumulator);
    }
}
