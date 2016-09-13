package org.gurpsdomain.adapters.output.domain;

import java.util.ArrayList;
import java.util.List;

public class Advantage {
    private String name;
    private int points;
    private List<Modifier> modifiers;

    public Advantage(String name, int points) {
        this.name = name;
        this.points = points;
        this.modifiers = new ArrayList<Modifier>();
    }

    public void addModifier(Modifier modifier) {
        this.modifiers.add(modifier);
    }
}
