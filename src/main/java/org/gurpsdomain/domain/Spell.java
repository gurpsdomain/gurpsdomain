package org.gurpsdomain.domain;

import static org.gurpsdomain.domain.Attribute.*;
import static org.gurpsdomain.domain.DifficultyLevel.*;

public class Spell {
    private final String name;
    private final int cost;
    private final String pageReference;
    private final DifficultyLevel difficultyLevel;
    private final String colleges;
    private final String powerSource;
    private final String spellClasses;
    private final String maintenanceCost;
    private final String castingTime;
    private final String duration;

    public Spell(String name, int cost, String pageReference, DifficultyLevel difficultyLevel, String colleges, String powerSource, String spellClasses, String maintenanceCost, String castingTime, String duration) {
        this.name = name;
        this.cost = cost;
        this.pageReference = pageReference;
        this.difficultyLevel = difficultyLevel;
        this.colleges = colleges;
        this.powerSource = powerSource;
        this.spellClasses = spellClasses;
        this.maintenanceCost = maintenanceCost;
        this.castingTime = castingTime;
        this.duration = duration;
        if (!(difficultyLevel.equals(HARD)) && !(difficultyLevel.equals(VERY_HARD))) {
            throw new IllegalArgumentException(String.format("Unexpected difficultyLevel %s for spell %s", difficultyLevel, name));
        }
    }

    void payCost(Points points) {
        points.addSpell(cost);
    }

    private int delta() {
        return difficultyLevel.determineDelta(cost);
    }

    public int level(Attributes attributes) {
        Attribute controllingAttribute = INTELLIGENCE;
        Attribute magery = MAGERY;
        return attributes.level(controllingAttribute) + delta() + attributes.level(magery);
    }

}
