package org.gurpsdomain.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Equipment {
    private final String name;
    private final String pageReference;
    private final List<DamageResistanceBonus> damageResistanceBonuses;

    public Equipment(String name, String pageReference,List<DamageResistanceBonus> damageResistanceBonuses) {
        this.name = name;
        this.pageReference = pageReference;
        this.damageResistanceBonuses = damageResistanceBonuses;
    }

    protected void updateRegularDamageResistances(DamageResistances damageResistances, List<DamageResistanceBonus> bonuses) {
        for (DamageResistanceBonus damageResistanceBonus : bonuses) {
            damageResistanceBonus.applyTo(damageResistances);
        }
    }

    void updateDamageResistances(DamageResistances damageResistances) {
        List<DamageResistanceBonus> regularDamageResistanceBonuses = damageResistanceBonuses.stream().filter(b -> !b.isLeveled()).collect(Collectors.toList());
        updateRegularDamageResistances(damageResistances, regularDamageResistanceBonuses);
    }
}
