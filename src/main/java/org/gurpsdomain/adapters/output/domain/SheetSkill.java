package org.gurpsdomain.adapters.output.domain;

import org.gurpsdomain.domain.DifficultyLevel;

import java.util.ArrayList;
import java.util.List;

public class SheetSkill {
    private String name;
    private int points;
    private String pageReference;
    private DifficultyLevel difficultyLevel;

    public SheetSkill(String name, int points, String pageReference, DifficultyLevel difficultyLevel) {
        this.name = name;
        this.points = points;
        this.pageReference = pageReference;
        this.difficultyLevel = difficultyLevel;
    }
}
