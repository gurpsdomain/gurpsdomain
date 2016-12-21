package org.gurpsdomain.adapters.output.domain;

import java.util.List;

public class SheetLeveledAdvantage extends SheetAdvantage {
    private final int level;

    public SheetLeveledAdvantage(String name, int points, int level, String pageReference, List<SheetModifier> modifiers) {
        super(name, points, pageReference, modifiers);
        this.level = level;
    }
}
