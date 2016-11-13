package org.gurpsdomain.domain;

import org.gurpsdomain.domain.description.Cost;

public class Modifier {
    private String name;
    private Cost cost;

    public Modifier(String name, Cost cost) {
        this.name = name;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Cost getCost() { return cost; }
}
