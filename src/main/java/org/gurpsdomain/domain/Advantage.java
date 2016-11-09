package org.gurpsdomain.domain;

import java.util.ArrayList;
import java.util.List;

public class Advantage implements PageReference {
    public String name;
    public String pageReference;
    private int unmodifiedCost;
    public int cost;
    public List<Modifier> modifiers;

    public Advantage(String name, int cost, String pageReference) {
        this(name, cost, pageReference, new ArrayList<Modifier>());
    }

    public Advantage(String name, int unmodifiedCost, String pageReference, List<Modifier> modifiers) {
        this.name = name;
        this.unmodifiedCost = unmodifiedCost;
        this.pageReference = pageReference;
        this.modifiers = modifiers;
    }

    private int getCost(){
        //TODO determine actual cost by iterating over the active modifiers and calculate modified cost
        this.cost = this.unmodifiedCost;
        return this.cost;
    }

    public void payCost(Points points) {
        points.addAdvantage(getCost());
    }

    @Override
    public String getPageReference() {
        return this.pageReference;
    }
}
