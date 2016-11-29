package org.gurpsdomain.adapters.output.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SheetAdvantage {
    private final String name;
    private final int points;
    private final String pageReference;
    private final List<SheetModifier> modifiers;

    public SheetAdvantage(String name, int points, String pageReference, List<SheetModifier> modifiers) {
        this.name = name;
        this.points = points;
        this.pageReference = pageReference;
        this.modifiers = Collections.unmodifiableList(modifiers);
    }
}
