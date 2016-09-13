package org.gurpsdomain.domain;

import java.util.List;

public class Advantage implements PageReference {
    public String name;
    public String pageReference;
    public int cost;
    public List<Modifier> modifiers;

    public Advantage(String name, int cost, String pageReference, List<Modifier> modifiers) {
        this.name = name;
        this.cost = cost;
        this.pageReference = pageReference;
        this.modifiers = modifiers;
    }

    public void payCost(Points points) {
        points.addAdvantage(cost);
    }

    @Override
    public String getPageReference() {
        return this.pageReference;
    }
}
