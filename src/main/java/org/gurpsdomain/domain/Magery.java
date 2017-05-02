package org.gurpsdomain.domain;

import java.util.Collections;
import java.util.List;

public class Magery extends LeveledAdvantage {
    public Magery(String name, int baseCost, String pageReference, List<Modifier> modifiers, List<AttributeBonus> attributeBonuses, List<SkillBonus> skillBonuses, List<DamageResistanceBonus> damageResistanceBonuses, int level, int pointsPerLevel) {
        super("Magery", 5, "B66", Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), level, 10);
    }
}
