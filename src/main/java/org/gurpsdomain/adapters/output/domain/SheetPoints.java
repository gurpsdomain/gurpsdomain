package org.gurpsdomain.adapters.output.domain;

public class SheetPoints {
    private final int total;
    private final int advantages;
    private final int skills;
    private final int unspent;

    public SheetPoints(int total, int advantages, int skills, int unspent) {
        this.total = total;
        this.advantages = advantages;
        this.skills = skills;
        this.unspent = unspent;
    }
}
