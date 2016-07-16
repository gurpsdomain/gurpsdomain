package org.gurpsdomain.domain;

public class Points {
    private int total;

    public Points(int total) {
        this.total = total;
    }

    public void award(int amount) {
        this.total += amount;
    }
}
