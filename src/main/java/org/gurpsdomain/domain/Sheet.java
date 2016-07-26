package org.gurpsdomain.domain;

public class Sheet {
    private final Points points;

    public Sheet(int earned) {
        this.points = new Points(earned);
    }

    public void award(int amount) {
        points.award(amount);
    }
    public void addAdvantage(int amount) {
        //TODO derive point
        points.addAdvantage(amount);
    }

}
