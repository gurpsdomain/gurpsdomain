package org.gurpsdomain.domain;

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
}
