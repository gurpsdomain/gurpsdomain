package eu.luminis;

import java.util.List;

public class Cost {
    enum CostType {
        PER_LEVEL,
        FIXED,
        SET,
        SPECIAL;
    }

    CostType type;
    Integer[] values;

    Cost(CostType type, Integer[] values) {
        this.type = type;
        this.values = values;
    }
}
