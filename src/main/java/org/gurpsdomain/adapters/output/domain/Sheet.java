package org.gurpsdomain.adapters.output.domain;

import java.util.ArrayList;
import java.util.List;

public class Sheet {
    private final Points points;
    private final List<Advantage> advantages = new ArrayList<Advantage>();

    public Sheet(Points points) {
        this.points = points;
        advantages.add(new org.gurpsdomain.adapters.output.domain.Advantage("Absolute Direction", 5));
    }
}
