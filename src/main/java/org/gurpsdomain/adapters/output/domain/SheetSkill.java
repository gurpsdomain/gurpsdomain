package org.gurpsdomain.adapters.output.domain;

import org.gurpsdomain.domain.Attribute;
import org.gurpsdomain.domain.DifficultyLevel;

public class SheetSkill {
    private final String name;
    private final int points;
    private final String pageReference;
    private final String controllingAttribute;
    private final DifficultyLevel difficultyLevel;
    private final int level;

    public SheetSkill(String name, int points, String pageReference, Attribute controllingAttribute, DifficultyLevel difficultyLevel, int level) {
        this.name = name;
        this.points = points;
        this.pageReference = pageReference;
        this.controllingAttribute = controllingAttribute.shorthand();
        this.difficultyLevel = difficultyLevel;
        this.level = level;
    }
}
