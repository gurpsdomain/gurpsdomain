package org.gurpsdomain.adapters.output.domain;

import org.gurpsdomain.domain.Attribute;
import org.gurpsdomain.domain.DifficultyLevel;

import java.util.ArrayList;
import java.util.List;

public class SheetSkill {
    private String name;
    private int points;
    private String pageReference;
    private Attribute controllingAttribute;
    private DifficultyLevel difficultyLevel;

    public SheetSkill(String name, int points, String pageReference, Attribute controllingAttribute, DifficultyLevel difficultyLevel) {
        this.name = name;
        this.points = points;
        this.pageReference = pageReference;
        this.controllingAttribute = controllingAttribute;
        this.difficultyLevel = difficultyLevel;
    }
}
