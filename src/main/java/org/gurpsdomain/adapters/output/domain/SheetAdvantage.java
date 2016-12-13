package org.gurpsdomain.adapters.output.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SheetAdvantage {
    private final String name;
    private final int points;
    private final int level;
    private final String pageReference;
    private final List<SheetModifier> modifiers;

    public SheetAdvantage(String name, int points, int level, String pageReference, List<SheetModifier> modifiers) {
        this.name = name;
        this.points = points;
        this.level = level;
        this.pageReference = pageReference;
        this.modifiers = Collections.unmodifiableList(modifiers);
    }
}
