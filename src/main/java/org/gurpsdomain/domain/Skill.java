package org.gurpsdomain.domain;

public class Skill {
    private final int cost;
    private final String name;
    private final Attribute controllingAttribute;
    private final DifficultyLevel difficultyLevel;
    private final String pageReference;
    private int bonus = 0;

    public Skill(String name, int cost, Attribute controllingAttribute, DifficultyLevel difficultyLevel, String pageReference) {
        this.cost = cost;
        this.name = name;
        this.pageReference = pageReference;
        this.controllingAttribute = controllingAttribute;
        this.difficultyLevel = difficultyLevel;
    }

    void payCost(Points points) {
        points.addSkill(cost);
    }

    private int delta() {
        return difficultyLevel.determineDelta(cost);
    }

    public int level(Attributes attributes) {
        return attributes.level(controllingAttribute) + delta() + bonus;
    }

    public void addBonus(String bonus) {
        this.bonus += Integer.parseInt(bonus);
    }
}
