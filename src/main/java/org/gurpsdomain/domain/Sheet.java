package org.gurpsdomain.domain;

import org.gurpsdomain.domain.Points;

public class Sheet {
    private final Points points;

    public Sheet(int earned) {
        this.points = new Points(earned);
    }

    public void award(int amount) {
        points.award(amount);
    }

    public org.gurpsdomain.adapters.output.domain.Sheet output() {
        return new org.gurpsdomain.adapters.output.domain.Sheet(
                points.output()
        );
    }
}
