package org.gurpsdomain.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sheet {
    private final Points points;
    private final List<Advantage> advantages = new ArrayList<Advantage>();
    private final Map<String, String> metaData = new HashMap<String, String>();

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

    public void setMetaDataProperty(String[] keys, String value) {
        this.metaData.put(keys[0], value);
    }
}
