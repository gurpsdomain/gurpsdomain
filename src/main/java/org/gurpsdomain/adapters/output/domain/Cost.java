package org.gurpsdomain.adapters.output.domain;

import org.gurpsdomain.domain.description.CostType;

public class Cost {
    private CostType type;
    private int value;

    public Cost(int value, CostType type) {
        this.value = value;
        this.type = type;
    }

}
