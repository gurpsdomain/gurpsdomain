package org.gurpsdomain.domain.description;

import org.gurpsdomain.domain.Advantage;
import org.gurpsdomain.domain.Modifier;
import org.gurpsdomain.domain.PageReference;

import java.util.ArrayList;
import java.util.List;

public class AdvantageDescription implements PageReference {
    public String name;
    public String pageReference;
    public int cost;
    public List <Modifier> modifiers;

    public AdvantageDescription(String name, int cost, String pageReference) {
        this(name, cost, pageReference, new ArrayList<Modifier>());
    }

    public AdvantageDescription(String name, int cost, String pageReference, List<Modifier> modifiers) {
        this.name = name;
        this.cost = cost;
        this.pageReference = pageReference;
        this.modifiers = modifiers != null ? modifiers : new ArrayList<Modifier>();
    }

    public Advantage createAdvantage() {
        return new Advantage(name, cost, pageReference);
    }

    @Override
    public String getPageReference() {
        return this.pageReference;
    }
}
