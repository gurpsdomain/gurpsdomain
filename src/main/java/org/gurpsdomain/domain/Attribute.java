package org.gurpsdomain.domain;

import java.util.EnumSet;

public enum Attribute {
    STRENGTH("ST"),
    DEXTERITY("DX"),
    HEALTH("HT"),
    INTELLIGENCE("IQ"),
    WILL("Will"),
    PERCEPTION("Per"),
    DAMAGE("Dmg"),
    BASIC_LIFT("BL"),
    HIT_POINTS("HP"),
    FATIGUE_POINTS("FP"),
    BASIC_SPEED("Basic Speed"),
    BASIC_MOVE("Basic Move");


    Attribute(String shorthand) {
        this.shorthand = shorthand;
    }

    public static EnumSet<Attribute> controllingValues() {
        return EnumSet.of(STRENGTH, DEXTERITY, HEALTH, INTELLIGENCE, WILL, PERCEPTION);
    }

    public static EnumSet<Attribute> primaryValues() {
        return EnumSet.of(STRENGTH, DEXTERITY, HEALTH, INTELLIGENCE);
    }

    private final String shorthand;

    public String shorthand() {
        return shorthand;
    }

}

