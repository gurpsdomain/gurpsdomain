package org.gurpsdomain.adapters.output.domain;


import org.gurpsdomain.domain.DifficultyLevel;

public class SheetSpell {
    private final String name;
    private final int points;
    private final int level;
    private final String difficultyLevel;
    private final String pageReference;
    private final String colleges;
    private final String powerSource;
    private final String spellClasses;
    private final String maintenanceCost;
    private final String castingTime;
    private final String duration;

    public SheetSpell(String name, int points, int level, DifficultyLevel difficultyLevel, String pageReference, String colleges, String powerSource, String spellClasses, String maintenanceCost, String castingTime, String duration) {
        this.name = name;
        this.points = points;
        this.level = level;
        this.difficultyLevel = difficultyLevel.shorthand();
        this.pageReference = pageReference;
        this.colleges = colleges;
        this.powerSource = powerSource;
        this.spellClasses = spellClasses;
        this.maintenanceCost = maintenanceCost;
        this.castingTime = castingTime;
        this.duration = duration;
    }
}
