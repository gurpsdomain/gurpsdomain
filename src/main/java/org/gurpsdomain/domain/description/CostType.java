package org.gurpsdomain.domain.description;

import org.gurpsdomain.domain.calc.AdvantageCostAccumulator;

import java.util.HashMap;
import java.util.Map;

public enum CostType {
    PERCENTAGE("percentage") {
        @Override
        public void accumulateCost(AdvantageCostAccumulator accumulator, int value) {
            accumulator.addPercentage(value);
        }
    }, POINTS("points") {
        @Override
        public void accumulateCost(AdvantageCostAccumulator accumulator, int value) {
            accumulator.addPoints(value);
        }
    };


    static final private Map<String, CostType> descriptionToCostType = new HashMap<>();
    private final String description;

    static {
        for (CostType costType : CostType.values()) {
            descriptionToCostType.put(costType.description(), costType);
        }
    }

    public static CostType fromDescription(String description) {
        if (!descriptionToCostType.containsKey(description)) {
            throw new IllegalArgumentException(String.format("\"%s\" is not a CostType key", description));
        }
        return descriptionToCostType.get(description);
    }

    CostType(String description) {
        this.description = description;
    }

    public String description() {
        return description;
    }

    public abstract void accumulateCost(AdvantageCostAccumulator accumulator, int value);
}