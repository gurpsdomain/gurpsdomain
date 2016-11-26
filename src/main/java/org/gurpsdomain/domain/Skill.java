package org.gurpsdomain.domain;

public class Skill {
    private final int cost;
    private final String name;
    private final Attribute controllingAttribute;
    private final DifficultyLevel difficultyLevel;
    private final String pageReference;

    public Skill(String name, int cost, Attribute controllingAttribute, DifficultyLevel difficultyLevel, String pageReference) {
        this.cost = cost;
        this.name = name;
        this.pageReference = pageReference;

        if (!controllingAttribute.isControlling()) {
            throw new IllegalArgumentException("Attribute" + controllingAttribute + "needs to be a controlling attribute.");
        }

        this.controllingAttribute = controllingAttribute;
        this.difficultyLevel = difficultyLevel;
    }

    public void payCost(Points points) {
        points.addSkill(cost);
    }

    public int delta() {
        return difficultyLevel.determineDelta(cost);
    }

}
