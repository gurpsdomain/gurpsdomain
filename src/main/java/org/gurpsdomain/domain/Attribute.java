package org.gurpsdomain.domain;

import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

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


    static final private Map<String, Attribute> descriptionToAttribute = new HashMap<String, Attribute>();

    static {
        for (Attribute attribute: controllingAttributes()) {
            descriptionToAttribute.put(attribute.shorthand(), attribute);
        }
    }

    private static Collection<Attribute> controllingAttributes() {
        return EnumSet.of(STRENGTH, DEXTERITY, HEALTH, INTELLIGENCE, WILL, PERCEPTION);
    }

    public static Attribute fromDescription(String description) {
        if (!descriptionToAttribute.containsKey(description)) {
            throw new IllegalArgumentException(String.format("\"%s\" is not a Attribute key", description));
        }
        return descriptionToAttribute.get(description);
    }

    Attribute(String shorthand) {
        this.shorthand = shorthand;
    }

    private final String shorthand;

    public String shorthand() {
        return shorthand;
    }

}

