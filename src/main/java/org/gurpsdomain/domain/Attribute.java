package org.gurpsdomain.domain;

public enum Attribute {
    STRENGTH("ST"),
    DEXTERITY("DX"),
    HEALTH("HT"),
    INTELLIGENCE("IQ"),
    WILL("Will"),
    PERCEPTION("Per");

    Attribute(String shorthand) {
        this.shorthand = shorthand;
    }

    private final String shorthand;

    public String getShorthand() {
        return shorthand;
    }
}

