package org.gurpsdomain.adapters.output.domain;

import org.gurpsdomain.domain.description.CostType;

public class SheetCost {
    private final CostType type;
    private final int value;

    public SheetCost(int value, CostType type) {
        this.value = value;
        this.type = type;
    }

}
