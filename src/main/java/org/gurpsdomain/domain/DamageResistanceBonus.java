package org.gurpsdomain.domain;

public class DamageResistanceBonus {
    public static DamageResistanceBonus damageResistanceBonus(DamageResistance damageResistance, String bonus) {
        return new DamageResistanceBonus(damageResistance, bonus, false);
    }

    public static DamageResistanceBonus leveledDamageResistanceBonus(DamageResistance damageResistance, String bonus) {
        return new DamageResistanceBonus(damageResistance, bonus, true);
    }

    private final DamageResistance damageResistance;
    private final String bonus;
    private final boolean leveled;

    private DamageResistanceBonus(DamageResistance damageResistance, String bonus, boolean leveled) {
        this.damageResistance = damageResistance;
        this.bonus = bonus;
        this.leveled = leveled;
    }

    public void applyTo(DamageResistances damageResistances) {
        damageResistance.addBonusTo(damageResistances, bonus);
    }

    public boolean isLeveled() {
        return leveled;
    }
}
