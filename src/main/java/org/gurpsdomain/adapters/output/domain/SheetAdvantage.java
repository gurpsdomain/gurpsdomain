package org.gurpsdomain.adapters.output.domain;

import java.util.ArrayList;
import java.util.List;

public class SheetAdvantage {
    private final String name;
    private final int points;
    private final String pageReference;
    private final List<SheetModifier> modifiers;

    public SheetAdvantage(String name, int points, String pageReference) {
        this.name = name;
        this.points = points;
        this.pageReference = pageReference;
        this.modifiers = new ArrayList<SheetModifier>();
    }

    public void addModifier(SheetModifier sheetModifier) {
        this.modifiers.add(sheetModifier);
    }
}
