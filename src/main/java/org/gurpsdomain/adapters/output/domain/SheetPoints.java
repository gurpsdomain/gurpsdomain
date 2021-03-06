package org.gurpsdomain.adapters.output.domain;

public class SheetPoints {
    private final int total;
    private final int advantages;
    private final int disadvantages;
    private final int skills;
    private final int spells;
    private final int unspent;

    public SheetPoints(int total, int advantages, int disadvantages, int skills, int spells, int unspent) {
        this.total = total;
        this.advantages = advantages;
        this.disadvantages = disadvantages;
        this.skills = skills;
        this.spells = spells;
        this.unspent = unspent;
    }
}
