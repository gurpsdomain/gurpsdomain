package org.gurpsdomain.adapters.output.domain;

public class SheetModifier {
    private final String name;
    private final String pageReference;
    private final String note;

    public SheetModifier(String name, SheetCost sheetCost, String pageReference, String note) {
        this.name = name;
        this.pageReference = pageReference;
        this.note = note;
    }
}
