package org.gurpsdomain.domain;

public class DamageResistanceBonus {
    public static DamageResistanceBonus damageResistanceBonus(DamageResistance damageResistance, String bonus) {
        return new DamageResistanceBonus(damageResistance, bonus);
    }

    private final DamageResistance damageResistance;
    private final String bonus;

    private DamageResistanceBonus(DamageResistance damageResistance, String bonus) {
        this.damageResistance = damageResistance;
        this.bonus = bonus;
    }

    public void applyTo(DamageResistances damageResistances) {
        damageResistance.addBonusTo(damageResistances, bonus);
    }
}
