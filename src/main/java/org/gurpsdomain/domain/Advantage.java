package org.gurpsdomain.domain;

public class Advantage implements PageReference {
    public String name;
    public String pageReference;
    public int cost;

    public Advantage(String name, int cost) {
        this.name = name;
        this.cost = cost;
        this.pageReference = "B123";
    }

    public void payCost(Points points) {
        points.addAdvantage(cost);
    }

    @Override
    public String getPageReference() {
        return this.pageReference;
    }
}
