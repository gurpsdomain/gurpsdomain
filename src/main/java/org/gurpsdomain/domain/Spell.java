package org.gurpsdomain.domain;

public class Spell {
    private final String name;
    private final int cost;
    private final String pageReference;

    public Spell(String name, int cost, String pageReference) {
        this.name = name;
        this.cost = cost;
        this.pageReference = pageReference;
    }

    void payCost(Points points) {
        points.addSpell(cost);
    }

    //TODO
//    private int delta() {
//        return difficultyLevel.determineDelta(cost);
//    }
//
//    public int level(Attributes attributes) {
//        return attributes.level(controllingAttribute) + delta();
//    }

}
