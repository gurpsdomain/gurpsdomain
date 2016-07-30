package org.gurpsdomain.domain;

public class Advantage {
    public String name;
    public int cost;

    public Advantage(String name) {
        this.name = name;
        this.cost = 20;
    }

    public void payCost(Points points) {
        points.addAdvantage(cost);
    }
}
