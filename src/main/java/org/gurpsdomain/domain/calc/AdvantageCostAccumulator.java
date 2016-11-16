package org.gurpsdomain.domain.calc;

public class AdvantageCostAccumulator {
    private int points = 0;
    private int percentage = 0;

    public AdvantageCostAccumulator(int baseCost) {
        this.points = baseCost;
    }

    public void addPoints(int value) {
        this.points += value;
    }

    public void addPercentage(int value) {
        this.percentage += value;
    }

    public int cost() {
        int actualPercentage = Math.max(percentage, -80);
        int delta = points * actualPercentage;
        int correction = (percentage > 0) && (delta % 100 != 0) ? 1: 0;
        int cost = points + delta/100 + correction;

        return cost;
    }
}
