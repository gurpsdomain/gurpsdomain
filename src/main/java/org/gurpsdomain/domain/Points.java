package org.gurpsdomain.domain;

public class Points {
    private int total;
    private int advantages;
    private int disadvantages;
    private int skills;
    private int spells;
    private int unspent;

    public Points(int total) {
        this.total = total;
        this.unspent = total;
    }

    void award(int amount) {
        this.total += amount;
        this.unspent += amount;
    }

    void addAdvantage(int amount) {
        if (amount >= 0) {
            this.advantages += amount;
        } else {
            this.disadvantages += amount;
        }
        this.unspent -= amount;
    }

    void addSkill(int amount) {
        this.skills += amount;
        this.unspent -= amount;
    }

    void addSpell(int amount) {
        this.spells += amount;
        this.unspent -= amount;
    }
}
