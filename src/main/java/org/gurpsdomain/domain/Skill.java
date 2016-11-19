package org.gurpsdomain.domain;

public class Skill {
    private final int points;
    private final DifficultyLevel difficultyLevel;

    public Skill(int points, DifficultyLevel difficultyLevel) {
        this.points = points;
        this.difficultyLevel = difficultyLevel;
    }

    public int delta() {
        return 0;
    }
}
