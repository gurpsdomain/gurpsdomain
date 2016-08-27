package org.gurpsdomain.adapters.output.domain;

import java.util.ArrayList;
import java.util.List;

public class Sheet {
    private final Points points;
    private final List<Advantage> advantages = new ArrayList<Advantage>();

    public Sheet(Points points, List<Advantage> advantages) {
        this.points = points;
        this.advantages.addAll(advantages);
    }
}
