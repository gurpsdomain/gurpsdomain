package org.gurpsdomain.domain;

public class SkillBonus {
    public static SkillBonus skillBonus(String skillName, String bonus) {
        return new SkillBonus(skillName, bonus);
    }

    private final String skillName;
    private final String bonus;

    private SkillBonus(String skillName, String bonus) {
        this.skillName = skillName;
        this.bonus = bonus;
    }

    public void applyTo(Skill skill) {
        skill.addBonus(bonus);
    }

}
