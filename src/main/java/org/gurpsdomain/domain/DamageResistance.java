package org.gurpsdomain.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum DamageResistance {
    EYE("Eye", "eye") {
        @Override
        public void addBonusTo(DamageResistances damageResistances, String bonus) {
            damageResistances.addEyeBonus(Integer.parseInt(bonus));
        }
    };

    static final private Map<String, DamageResistance> descriptionToDamageResistance = new HashMap<>();
    private final String shorthand;
    private final List<String> alternatives;

    static {
        for (DamageResistance damageResistance : DamageResistance.values()) {
            descriptionToDamageResistance.put(damageResistance.shorthand(), damageResistance);

            for (String alternativeDescription : damageResistance.alternativeDescriptions()) {
                descriptionToDamageResistance.put(alternativeDescription, damageResistance);
            }
        }
    }

    public static DamageResistance fromDescription(String description) {
        if (!descriptionToDamageResistance.containsKey(description)) {
            throw new IllegalArgumentException(String.format("\"%s\" is not a DamageResistance key", description));
        }
        return descriptionToDamageResistance.get(description);
    }

    DamageResistance(String shorthand, String... alternatives) {
        this.shorthand = shorthand;
        this.alternatives = Arrays.asList(alternatives);
    }

    public String shorthand() {
        return shorthand;
    }

    private List<String> alternativeDescriptions() {
        return alternatives;
    }

    public Object defaultBonus() {
        return 0;
    }

    public Object value(DamageResistances damageResistances, Object bonus) {
        return defaultValue() + (int) bonus;
    }

    protected int defaultValue(){return 0;}

    public abstract void addBonusTo(DamageResistances damageResistances, String bonus);
}

