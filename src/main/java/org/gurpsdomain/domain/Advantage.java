package org.gurpsdomain.domain;

import java.util.ArrayList;
import java.util.List;

public class Advantage implements PageReference {
    private String name;
    private String pageReference;
    private int baseCost;
    private List<Modifier> modifiers;

    public Advantage(String name, int cost, String pageReference) {
        this(name, cost, pageReference, new ArrayList<Modifier>());
    }

    public Advantage(String name, int baseCost, String pageReference, List<Modifier> modifiers) {
        this.name = name;
        this.baseCost = baseCost;
        this.pageReference = pageReference;
        this.modifiers = modifiers;
    }

    private int cost() {
        int cost = baseCost;

        for (Modifier modifier : modifiers) {
            cost += modifier.getCost().getValue();
        }
        return cost;
    }

    public void payCost(Points points) {
        points.addAdvantage(cost());
    }

    @Override
    public String getPageReference() {
        return pageReference;
    }
}
