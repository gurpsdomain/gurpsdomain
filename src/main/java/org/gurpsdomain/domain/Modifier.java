package org.gurpsdomain.domain;

import org.gurpsdomain.domain.calc.AdvantageCostAccumulator;

public class Modifier {
    private final String name;
    private final Cost cost;
    private final String pageReference;
    private final String note;

    public Modifier(String name, Cost cost, String pageReference, String note) {
        this.name = name;
        this.cost = cost;
        this.pageReference = pageReference;
        this.note = note;
    }

    void accumulateCost(AdvantageCostAccumulator accumulator) {
        cost.accumulateCost(accumulator);
    }
}
