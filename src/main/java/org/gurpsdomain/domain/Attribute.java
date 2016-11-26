package org.gurpsdomain.domain;

public enum Attribute {
    STRENGTH("ST", true, true),
    DEXTERITY("DX", true, true),
    HEALTH("HT", true, true),
    INTELLIGENCE("IQ", true, true),
    WILL("Will", false, true),
    PERCEPTION("Per", false, true);

    Attribute(String shorthand, boolean isPrimary, boolean isControlling) {
        this.shorthand = shorthand;
        this.isPrimary = isPrimary;
        this.isControlling = isControlling;
    }

    private final String shorthand;
    private final boolean isPrimary;
    private final boolean isControlling;

    public String getShorthand() {
        return shorthand;
    }

    public boolean isPrimary() {
        return isPrimary;
    }

    public boolean isControlling() {
        return isControlling;
    }

}

