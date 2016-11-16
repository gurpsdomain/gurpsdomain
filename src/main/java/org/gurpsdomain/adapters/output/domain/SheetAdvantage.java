package org.gurpsdomain.adapters.output.domain;

import java.util.ArrayList;
import java.util.List;

public class SheetAdvantage {
    private String name;
    private int points;
    private String pageReference;
    private List<SheetModifier> modifiers;

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
