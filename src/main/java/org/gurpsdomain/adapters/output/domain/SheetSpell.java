package org.gurpsdomain.adapters.output.domain;


public class SheetSpell {
    private final String name;
    private final int points;
    private final String pageReference;

    public SheetSpell(String name, int points, String pageReference) {
        this.name = name;
        this.points = points;
        this.pageReference = pageReference;
    }
}
