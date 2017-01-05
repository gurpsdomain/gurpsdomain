package org.gurpsdomain.adapters.output.domain;


import org.gurpsdomain.domain.DifficultyLevel;

public class SheetSpell {
    private final String name;
    private final int points;
    private final int level;
    private final DifficultyLevel difficultyLevel;
    private final String pageReference;

    public SheetSpell(String name, int points, int level, DifficultyLevel difficultyLevel, String pageReference) {
        this.name = name;
        this.points = points;
        this.level = level;
        this.difficultyLevel = difficultyLevel;
        this.pageReference = pageReference;
    }
}
