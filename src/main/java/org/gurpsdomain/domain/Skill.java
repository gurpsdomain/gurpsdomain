package org.gurpsdomain.domain;

public class Skill {
    private final int cost;
    private final String name;
    private final DifficultyLevel difficultyLevel;
    private final String pageReference;

    public Skill(String name, int cost, DifficultyLevel difficultyLevel, String pageReference) {
        this.cost = cost;
        this.name = name;
        this.pageReference = pageReference;
        this.difficultyLevel = difficultyLevel;
    }

    public void payCost(Points points) {
        points.addSkill(cost);
    }

    public int delta() {
        return 0;
    }
}
