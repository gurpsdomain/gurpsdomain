package org.gurpsdomain.domain;

import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/* TODO Vision et. al. are missing */
public enum Attribute {
    STRENGTH("ST"),
    DEXTERITY("DX"),
    HEALTH("HT"),
    INTELLIGENCE("IQ"),
    WILL("Will") {
        @Override
        public Object value(Attributes attributes, Object bonus) {
            return (int) attributes.value(Attribute.INTELLIGENCE) + (int) bonus;
        }
    },
    PERCEPTION("Per") {
        @Override
        public Object value(Attributes attributes, Object bonus) {
            return (int) attributes.value(Attribute.INTELLIGENCE) + (int) bonus;
        }
    },
    HIT_POINTS("HP") {
        @Override
        public Object value(Attributes attributes, Object bonus) {
            return (int) attributes.value(Attribute.STRENGTH) + (int) bonus;
        }
    },
    FATIGUE_POINTS("FP") {
        @Override
        public Object value(Attributes attributes, Object bonus) {
            return (int) attributes.value(Attribute.HEALTH) + (int) bonus;
        }
    },
    BASIC_LIFT("BL") {
        @Override
        public Object defaultBonus() {
            return 0.0;
        }

        @Override
        public Object value(Attributes attributes, Object bonus) {
            double strength = ((Integer) attributes.value(Attribute.STRENGTH)).doubleValue();
            double basicLift = (strength * strength) / 5.0 + (double) bonus;
            if (basicLift > 10) {
                basicLift = Math.round(basicLift);
            }
            return basicLift;
        }
    },
    BASIC_SPEED("Basic Speed") {
        @Override
        public Object defaultBonus() {
            return 0.0;
        }

        @Override
        public Object value(Attributes attributes, Object bonus) {
            int health = (int) attributes.value(Attribute.HEALTH);
            int dexterity = (int) attributes.value(Attribute.DEXTERITY);
            double speed = (health + dexterity) / 4.0;
            return speed + (double) bonus;
        }
    },
    BASIC_MOVE("Basic Move") {
        @Override
        public Object value(Attributes attributes, Object bonus) {
            int health = (int) attributes.value(Attribute.HEALTH);
            int dexterity = (int) attributes.value(Attribute.DEXTERITY);
            int move = (health + dexterity) / 4;
            return move + (int) bonus;
        }
    },
    DAMAGE("Dmg")/* FIXME this is not implemented in attributes, copying */;


    static final private Map<String, Attribute> descriptionToAttribute = new HashMap<>();

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

    public Object defaultBonus() {
        return 0;
    }

    public Object value(Attributes attributes, Object bonus) {
        return 10 + (int) bonus;
    }
}

