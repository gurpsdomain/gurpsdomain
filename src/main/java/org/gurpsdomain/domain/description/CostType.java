package org.gurpsdomain.domain.description;

import org.gurpsdomain.domain.calc.AdvantageCostAccumulator;

public enum CostType {
    percentage {
        @Override
        public void accumulateCost(AdvantageCostAccumulator accumulator, int value) {
            accumulator.addPercentage(value);
        }
    }, points {
        @Override
        public void accumulateCost(AdvantageCostAccumulator accumulator, int value) {
            accumulator.addPoints(value);
        }
    };

    public abstract void accumulateCost(AdvantageCostAccumulator accumulator, int value);
}
