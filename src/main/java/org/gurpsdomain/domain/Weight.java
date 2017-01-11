package org.gurpsdomain.domain;


public class Weight {
    private double weightInPounds;

    public static Weight sumWeights(Weight firstWeight, Weight secondWeight) {
        return new Weight(firstWeight.poundsInImperialSystem() + secondWeight.poundsInImperialSystem());
    }

    public static Weight parseWeightInPounds(String inputString) {
        String WeightAsString = inputString.split("lb")[0];
        Double WeightAsDouble = Double.parseDouble(WeightAsString);
        return new Weight(WeightAsDouble);
    }

    public Weight(double weightInPounds) {
        this.weightInPounds = weightInPounds;
    }

    public String toStringImperialSystemNotation() {
        return poundsInImperialSystem() + " lbs.";
    }

    public String toStringRealMetricSystemNotation() {
        return kilogramsInRealMetricSystem() + " kg";
    }

    public String toStringGameMetricSystemNotation() {
        return kilogramsInGameMetricSystem() + " kg";
    }

    public double poundsInImperialSystem() {
        return weightInPounds;
    }

    public double kilogramsInRealMetricSystem() {
        return weightInPounds * 0.454;
    }

    public double kilogramsInGameMetricSystem() {
        return weightInPounds * 0.5;
    }
}