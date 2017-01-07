package org.gurpsdomain.domain;

public class Spell {
    private final String name;
    private final int cost;
    private final String pageReference;
    private final DifficultyLevel difficultyLevel;

    public Spell(String name, int cost, String pageReference, DifficultyLevel difficultyLevel) {
        this.name = name;
        this.cost = cost;
        this.pageReference = pageReference;
        this.difficultyLevel = difficultyLevel;
        if (!(difficultyLevel.equals(DifficultyLevel.HARD)) && !(difficultyLevel.equals(DifficultyLevel.VERY_HARD))) {
            throw new IllegalArgumentException("Unexpected difficultyLevel " + difficultyLevel + " for spell " + name);
        }
    }

    void payCost(Points points) {
        points.addSpell(cost);
    }

    private int delta() {
        return difficultyLevel.determineDelta(cost);
    }

    //TODO assume magery 0 now. To be implemented as attribute?
    public int level(Attributes attributes) {
        Attribute controllingAttribute = Attribute.INTELLIGENCE;
        return attributes.level(controllingAttribute) + delta() + 0;
    }

}
