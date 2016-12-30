package org.gurpsdomain.domain;

import org.gurpsdomain.domain.calc.AdvantageCostAccumulator;
import org.gurpsdomain.domain.description.CostType;

public class Cost {
    private final CostType type;
    private final int value;

    public Cost(int value, CostType type) {
        this.type = type;
        this.value = value;
    }

    void accumulateCost(AdvantageCostAccumulator accumulator) {
        type.accumulateCost(accumulator, value);
    }
}
