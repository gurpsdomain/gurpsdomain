package org.gurpsdomain.adapters.output.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SheetSheet {
    private final SheetPoints points;
    private final List<SheetAdvantage> advantages = new ArrayList<SheetAdvantage>();
    private final Map<String, String> metaData;
    private final String note;

    public SheetSheet(Map<String, String> metaData, SheetPoints sheetPoints, List<SheetAdvantage> sheetAdvantages, String note) {
        this.metaData = metaData;
        this.points = sheetPoints;
        this.advantages.addAll(sheetAdvantages);
        this.note = note;
    }
}
