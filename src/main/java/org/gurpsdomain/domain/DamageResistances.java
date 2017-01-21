package org.gurpsdomain.domain;

import java.util.HashMap;
import java.util.Map;

import static org.gurpsdomain.domain.DamageResistance.*;

public class DamageResistances {
    private final Map<DamageResistance, Object> bonuses = new HashMap<>();

    public Object value(DamageResistance damageResistance) {
        return damageResistance.value(this, bonuses.getOrDefault(damageResistance, damageResistance.defaultBonus()));
    }

    void addEyeBonus(int bonus) {
        addIntBonus(EYE, bonus);
    }

    private void addIntBonus(DamageResistance damageResistance, int aBonus) {
        int previous = (int) bonuses.getOrDefault(damageResistance, damageResistance.defaultBonus());
        int current = previous + aBonus;
        bonuses.put(damageResistance, current);
    }

    public int level(DamageResistance damageResistance) {
        return (int) value(damageResistance);
    }

    int eye() {
        return (int) value(EYE);
    }

}