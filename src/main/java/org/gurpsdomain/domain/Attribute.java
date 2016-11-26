package org.gurpsdomain.domain;

public enum Attribute {
    STRENGTH("ST", true, true),
    DEXTERITY("DX", true, true),
    HEALTH("HT", true, true),
    INTELLIGENCE("IQ", true, true),
    WILL("Will", false, true),
    PERCEPTION("Per", false, true),
    DAMAGE("Dmg", false, false),
    BASIC_LIFT("BL", false, false),
    HIT_POINTS("HP", false, false),
    FATIGUE_POINTS("FP", false, false),
    BASIC_SPEED("Basic Speed", false, false),
    BASIC_MOVE("Basic Move", false, false);


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

