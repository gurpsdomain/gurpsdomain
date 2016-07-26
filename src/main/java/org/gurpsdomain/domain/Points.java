package org.gurpsdomain.domain;

public class Points {
    private int total;
    private int advantages = 20;

    public Points(int total) {
        this.total = total;
    }

    public void award(int amount) {
        this.total += amount;
    }

    public org.gurpsdomain.adapters.output.domain.Points output() {
        return new org.gurpsdomain.adapters.output.domain.Points(total, advantages);
    }
}
