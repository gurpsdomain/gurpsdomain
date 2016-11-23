package org.gurpsdomain.adapters.output.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SheetSheet {
    private final SheetPoints points;
    private final List<SheetAdvantage> advantages = new ArrayList<SheetAdvantage>();
    private final List<SheetSkill> skills = new ArrayList<SheetSkill>();
    private final Map<String, String> metaData;
    private final String note;
    private final SheetAttributes attributes;

    public SheetSheet(Map<String, String> metaData, SheetPoints sheetPoints, List<SheetAdvantage> sheetAdvantages, List<SheetSkill> sheetSkills, String note, SheetAttributes sheetAttributes) {
        this.metaData = metaData;
        this.points = sheetPoints;
        this.advantages.addAll(sheetAdvantages);
        this.skills.addAll(sheetSkills);
        this.note = note;
        this.attributes = sheetAttributes;
    }
}
