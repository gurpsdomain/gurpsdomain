package org.gurpsdomain.domain;

import org.gurpsdomain.domain.calc.AdvantageCostAccumulator;
import org.gurpsdomain.domain.description.CostType;

public class Cost {
    private CostType type;
    private int value;

    public Cost(int value, CostType type) {
        this.type = type;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public CostType getCostType() {
        return type;
    }

    public void accumulateCost(AdvantageCostAccumulator accumulator) {
        type.accumulateCost(accumulator, value);
    }
}
