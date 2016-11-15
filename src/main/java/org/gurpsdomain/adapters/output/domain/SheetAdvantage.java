package org.gurpsdomain.adapters.output.domain;

import java.util.ArrayList;
import java.util.List;

public class SheetAdvantage {
    private String name;
    private int points;
    private List<SheetModifier> modifiers;

    public SheetAdvantage(String name, int points) {
        this.name = name;
        this.points = points;
        this.modifiers = new ArrayList<SheetModifier>();
    }

    public void addModifier(SheetModifier sheetModifier) {
        this.modifiers.add(sheetModifier);
    }
}
