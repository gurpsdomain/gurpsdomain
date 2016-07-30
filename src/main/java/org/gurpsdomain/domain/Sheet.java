package org.gurpsdomain.domain;

public class Sheet {
    private final Points points;

    public Sheet() {
        this.points = new Points(0);
    }

    public void award(int amount) {
        points.award(amount);
    }
    public void addAdvantage(Advantage advantage) {
        advantage.payCost(points);
    }

}
