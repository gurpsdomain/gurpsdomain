package org.gurpsdomain.adapters.output.domain;

public class SheetBasicLift {
    private final String realMetric;
    private final String gameMetric;
    private final String imperial;

    public SheetBasicLift(String realMetric, String gameMetric, String imperial) {
        this.realMetric = realMetric;
        this.gameMetric = gameMetric;
        this.imperial = imperial;
    }
}
