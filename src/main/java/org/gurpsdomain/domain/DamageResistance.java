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
    },
    SKULL("Skull", "skull") {
        @Override
        public void addBonusTo(DamageResistances damageResistances, String bonus) {
            damageResistances.addSkullBonus(Integer.parseInt(bonus));
        }
    },
    FACE("Face", "face") {
        @Override
        public void addBonusTo(DamageResistances damageResistances, String bonus) {
            damageResistances.addFaceBonus(Integer.parseInt(bonus));
        }
    },
    RIGHT_LEG("Right Leg", "right leg", "R.Leg") {
        @Override
        public void addBonusTo(DamageResistances damageResistances, String bonus) {
            damageResistances.addRightLegBonus(Integer.parseInt(bonus));
        }
    },
    RIGHT_ARM("Right Arm", "right arm", "R.Arm") {
        @Override
        public void addBonusTo(DamageResistances damageResistances, String bonus) {
            damageResistances.addRightArmBonus(Integer.parseInt(bonus));
        }
    },
    TORSO("Torso", "torso") {
        @Override
        public void addBonusTo(DamageResistances damageResistances, String bonus) {
            damageResistances.addTorsoBonus(Integer.parseInt(bonus));
        }
    },
    GROIN("Groin", "groin") {
        @Override
        public void addBonusTo(DamageResistances damageResistances, String bonus) {
            damageResistances.addGroinBonus(Integer.parseInt(bonus));
        }
    },
    LEFT_ARM("Left Arm", "left arm", "L.Arm") {
        @Override
        public void addBonusTo(DamageResistances damageResistances, String bonus) {
            damageResistances.addLeftArmBonus(Integer.parseInt(bonus));
        }
    },
    LEFT_LEG("Left Leg", "left leg", "L.Leg") {
        @Override
        public void addBonusTo(DamageResistances damageResistances, String bonus) {
            damageResistances.addLeftLegBonus(Integer.parseInt(bonus));
        }
    },
    HAND("Hand", "hand") {
        @Override
        public void addBonusTo(DamageResistances damageResistances, String bonus) {
            damageResistances.addHandBonus(Integer.parseInt(bonus));
        }
    },
    FOOT("Foot", "foot", "Feet", "feet") {
        @Override
        public void addBonusTo(DamageResistances damageResistances, String bonus) {
            damageResistances.addFootBonus(Integer.parseInt(bonus));
        }
    },
    NECK("Neck", "neck") {
        @Override
        public void addBonusTo(DamageResistances damageResistances, String bonus) {
            damageResistances.addNeckBonus(Integer.parseInt(bonus));
        }
    },
    VITALS("Vitals", "vitals") {
        @Override
        public void addBonusTo(DamageResistances damageResistances, String bonus) {
            damageResistances.addVitalsBonus(Integer.parseInt(bonus));
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

    protected int defaultValue() {
        return 0;
    }

    public abstract void addBonusTo(DamageResistances damageResistances, String bonus);
}

