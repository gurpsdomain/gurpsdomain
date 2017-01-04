package org.gurpsdomain.domain;

import java.util.*;

import static org.gurpsdomain.domain.tables.DamageTable.swingingDamageForStrength;
import static org.gurpsdomain.domain.tables.DamageTable.thrustingDamageForStrength;

/* TODO Vision et. al. are missing */
public enum Attribute {
    STRENGTH("ST", "st") {
        @Override
        public void addBonusTo(Attributes attributes, int amount) {
            attributes.addStrengthBonus(amount);
        }
    },
    DEXTERITY("DX") {
        @Override
        public void addBonusTo(Attributes attributes, int amount) {
            attributes.addDexterityBonus(amount);
        }
    },
    HEALTH("HT") {
        @Override
        public void addBonusTo(Attributes attributes, int amount) {
            attributes.addHealthBonus(amount);
        }
    },
    INTELLIGENCE("IQ", "iq") {
        @Override
        public void addBonusTo(Attributes attributes, int amount) {
            attributes.addIntelligenceBonus(amount);
        }
    },
    WILL("Will", "will") {
        @Override
        public void addBonusTo(Attributes attributes, int amount) {
            attributes.addWillBonus(amount);
        }

        @Override
        public Object value(Attributes attributes, Object bonus) {
            return (int) attributes.value(Attribute.INTELLIGENCE) + (int) bonus;
        }
    },
    PERCEPTION("Per") {
        @Override
        public void addBonusTo(Attributes attributes, int amount) {
            attributes.addPerceptionBonus(amount);
        }

        @Override
        public Object value(Attributes attributes, Object bonus) {
            return (int) attributes.value(Attribute.INTELLIGENCE) + (int) bonus;
        }
    },
    VISION("Vision") {
        @Override
        public void addBonusTo(Attributes attributes, int amount) {
            attributes.addVisionBonus(amount);
        }

        @Override
        public Object value(Attributes attributes, Object bonus) {
            return (int) attributes.value(Attribute.PERCEPTION) + (int) bonus;
        }
    },
    HEARING("Hearing") {
        @Override
        public void addBonusTo(Attributes attributes, int amount) {
            attributes.addHearingBonus(amount);
        }

        @Override
        public Object value(Attributes attributes, Object bonus) {
            return (int) attributes.value(Attribute.PERCEPTION) + (int) bonus;
        }
    },
    TASTE_AND_SMELL("Taste & Smell") {
        @Override
        public void addBonusTo(Attributes attributes, int amount) {
            attributes.addTasteAndSmellBonus(amount);
        }

        @Override
        public Object value(Attributes attributes, Object bonus) {
            return (int) attributes.value(Attribute.PERCEPTION) + (int) bonus;
        }
    },
    TOUCH("Touch") {
        @Override
        public void addBonusTo(Attributes attributes, int amount) {
            attributes.addTouchBonus(amount);
        }

        @Override
        public Object value(Attributes attributes, Object bonus) {
            return (int) attributes.value(Attribute.PERCEPTION) + (int) bonus;
        }
    },
    HIT_POINTS("HP") {
        @Override
        public void addBonusTo(Attributes attributes, int amount) {
            attributes.addHitPointsBonus(amount);
        }

        @Override
        public Object value(Attributes attributes, Object bonus) {
            return (int) attributes.value(Attribute.STRENGTH) + (int) bonus;
        }
    },
    FATIGUE_POINTS("FP") {
        @Override
        public void addBonusTo(Attributes attributes, int amount) {
            attributes.addFatiguePointsBonus(amount);
        }

        @Override
        public Object value(Attributes attributes, Object bonus) {
            return (int) attributes.value(Attribute.HEALTH) + (int) bonus;
        }
    },
    BASIC_LIFT("BL") {
        @Override
        public void addBonusTo(Attributes attributes, int amount) {
            throw new RuntimeException("not implemented yet");
        }

        @Override
        public Object defaultBonus() {
            return new Weight(0);
        }

        @Override
        public Object value(Attributes attributes, Object bonus) {
            double strength = ((Integer) attributes.value(Attribute.STRENGTH)).doubleValue();
            double basicLift = (strength * strength) / 5.0 + ((Weight) bonus).poundsInImperialSystem();
            if (basicLift > 10) {
                basicLift = Math.round(basicLift);
            }
            return new Weight(basicLift);
        }
    },
    BASIC_SPEED("Basic Speed") {
        @Override
        public void addBonusTo(Attributes attributes, int amount) {
            throw new RuntimeException("not implemented yet");
        }

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
        public void addBonusTo(Attributes attributes, int amount) {
            throw new RuntimeException("not implemented yet");
        }

        @Override
        public Object value(Attributes attributes, Object bonus) {
            int health = (int) attributes.value(Attribute.HEALTH);
            int dexterity = (int) attributes.value(Attribute.DEXTERITY);
            int move = (health + dexterity) / 4;
            return move + (int) bonus;
        }
    },
    DODGE("Dodge", "dodge") {
        @Override
        public void addBonusTo(Attributes attributes, int amount) {
            attributes.addDodgeBonus(amount);
        }


        @Override
        public Object value(Attributes attributes, Object bonus) {
            double speed = (double) attributes.value(Attribute.BASIC_SPEED);
            int dodge = (int) (speed + 3);

            return dodge + (int) bonus;
        }
    },
    DAMAGE_THRUSTING("Dmg. Thrusting") {
        @Override
        public void addBonusTo(Attributes attributes, int amount) {
            throw new RuntimeException("not implemented yet");
        }

        @Override
        public Object defaultBonus() {
            return new Dice(0, 0);
        }

        @Override
        public Object value(Attributes attributes, Object bonus) {
            int strength = (int) attributes.value(Attribute.STRENGTH);
            Dice damage = thrustingDamageForStrength(strength);
            return Dice.sumDice(damage, (Dice) bonus);
        }
    },
    DAMAGE_SWINGING("Dmg. Swinging") {
        @Override
        public void addBonusTo(Attributes attributes, int amount) {
            throw new RuntimeException("not implemented yet");
        }

        @Override
        public Object defaultBonus() {
            return new Dice(0, 0);
        }

        @Override
        public Object value(Attributes attributes, Object bonus) {
            int strength = (int) attributes.value(Attribute.STRENGTH);
            Dice damage = swingingDamageForStrength(strength);
            return Dice.sumDice(damage, (Dice) bonus);
        }
    };

    static final private Map<String, Attribute> descriptionToAttribute = new HashMap<>();
    private final String shorthand;
    private final List<String> alternatives;

    static {
        for (Attribute attribute : Attribute.values()) {
            descriptionToAttribute.put(attribute.shorthand(), attribute);

            for (String alternativeDescription: attribute.alternativeDescriptions()) {
                descriptionToAttribute.put(alternativeDescription, attribute);
            }
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

    Attribute(String shorthand, String... alternatives) {
        this.shorthand = shorthand;
        this.alternatives = Arrays.asList(alternatives);
    }

    public String shorthand() {
        return shorthand;
    }

    private List<String> alternativeDescriptions() { return alternatives; }

    public Object defaultBonus() {
        return 0;
    }

    public Object value(Attributes attributes, Object bonus) {
        return 10 + (int) bonus;
    }

    public abstract void addBonusTo(Attributes attributes, int amount);
}

