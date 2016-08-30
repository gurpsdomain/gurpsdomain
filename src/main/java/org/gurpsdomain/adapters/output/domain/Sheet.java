package org.gurpsdomain.adapters.output.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sheet {
    private final Points points;
    private final List<Advantage> advantages = new ArrayList<Advantage>();
    private final Map<String, String> metaData;

    public Sheet(Map<String, String> metaData, Points points, List<Advantage> advantages) {
        this.metaData = metaData;
        this.points = points;
        this.advantages.addAll(advantages);
    }
}
