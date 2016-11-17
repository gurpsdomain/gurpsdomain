package org.gurpsdomain.adapters.output.domain;

public class SheetModifier {
    private String name;
//    private Cost cost;
    private String pageReference;

    public SheetModifier(String name, SheetCost sheetCost, String pageReference) {
        this.name = name;
        //this.cost = cost;
        this.pageReference = pageReference;
    }
}
