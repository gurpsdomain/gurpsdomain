package org.gurpsdomain.domain;

import java.util.ArrayList;
import java.util.List;

public class Sheet {
    private final Points points;
    private final List<Advantage> advantages = new ArrayList<Advantage>();

    public Sheet() {
        this.points = new Points(0);
    }

    public void award(int amount) {
        points.award(amount);
    }
    public void addAdvantage(Advantage advantage) {
        advantage.payCost(points);
        advantages.add(advantage);
    }

}
