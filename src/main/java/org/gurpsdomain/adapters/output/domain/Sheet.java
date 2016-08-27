package org.gurpsdomain.adapters.output.domain;

import java.util.ArrayList;
import java.util.List;

public class Sheet {
    private final Points points;
    private final List<Object> advantages = new ArrayList<Object>();

    public Sheet(Points points) {
        this.points = points;
    }
}
