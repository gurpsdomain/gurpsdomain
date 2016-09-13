package org.gurpsdomain.domain.description;

import org.gurpsdomain.domain.Advantage;
import org.gurpsdomain.domain.PageReference;

public class AdvantageDescription implements PageReference {
    public String name;
    public String pageReference;
    public int cost;

    public AdvantageDescription(String name, int cost, String pageReference) {
        this.name = name;
        this.cost = cost;
        this.pageReference = pageReference;
    }

    public Advantage createAdvantage() {
        return new Advantage(name, cost, pageReference);
    }

    @Override
    public String getPageReference() {
        return this.pageReference;
    }
}
