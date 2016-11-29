package org.gurpsdomain.adapters.output.domain;

public class SheetModifier {
    private final String name;
    private final String pageReference;

    public SheetModifier(String name, SheetCost sheetCost, String pageReference) {
        this.name = name;
        this.pageReference = pageReference;
    }
}
