package org.gurpsdomain.domain;

public class Points {
    private int total;
    private int advantages;

    public Points(int total) {
        this.total = total;
    }

    public void award(int amount) {
        this.total += amount;
    }

    public void addAdvantage(int amount) {this.advantages += amount;}
}
