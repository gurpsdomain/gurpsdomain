package org.gurpsdomain.domain;

import org.gurpsdomain.domain.calc.AdvantageCostAccumulator;

public class AdvantageLevel {
    private final Cost cost;
    public AdvantageLevel(Cost cost) {
        this.cost = cost;
    }

    public Cost getCost() {
        return cost;
    }

    public void accumulateCost(AdvantageCostAccumulator accumulator) {
        cost.accumulateCost(accumulator);
    }
}
