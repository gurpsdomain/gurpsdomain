package org.gurpsdomain.domain;


public class Weight {
    private double weightInPounds;

    public static Weight sumWeights(Weight firstWeight, Weight secondWeight) {
        return new Weight(firstWeight.poundsInImperialSystem() + secondWeight.poundsInImperialSystem());
    }

    public Weight(double weightInPounds) {
        this.weightInPounds = weightInPounds;
    }

    @Override
    public String toString() {
        return poundsInImperialSystem() + " lbs.";
    }

    public String toStringMetricSystemNotation() {
        return kilogramsInMetricSystem() + " kg";
    }

    public double poundsInImperialSystem() {
        return weightInPounds;
    }

    public double kilogramsInMetricSystem() {
        return weightInPounds * 0.454;
    }
}