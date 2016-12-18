package org.gurpsdomain.domain;

public class Points {
    private int total;
    private int advantages;
    private int skills;

    public Points(int total) {
        this.total = total;
    }

    void award(int amount) {
        this.total += amount;
    }

    void addAdvantage(int amount) {this.advantages += amount;}

    void addSkill(int amount) {this.skills += amount;}
}
