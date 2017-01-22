package org.gurpsdomain.adapters.output.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SheetSheet {
    private final SheetPoints points;
    private final List<SheetAdvantage> advantages = new ArrayList<>();
    private final List<SheetAdvantage> disadvantages = new ArrayList<>();
    private final List<SheetSkill> skills = new ArrayList<>();
    private final List<SheetSpell> spells = new ArrayList<>();
    private final Map<String, String> metaData;
    private final List<SheetNote> notes = new ArrayList<>();
    private final SheetAttributes attributes;
    private final SheetSecondaryCharacteristics secondaryCharacteristics;
    private final SheetDamageResistances damageResistances;

    public SheetSheet(Map<String, String> metaData, SheetPoints sheetPoints, List<SheetAdvantage> sheetAdvantages, List<SheetAdvantage> sheetDisadvantages, List<SheetSkill> sheetSkills, List<SheetSpell> sheetSpells, List<SheetNote> sheetNotes, SheetAttributes sheetAttributes, SheetSecondaryCharacteristics sheetSecondaryCharacteristics, SheetDamageResistances sheetDamageResistances) {
        this.metaData = metaData;
        this.points = sheetPoints;
        this.advantages.addAll(sheetAdvantages);
        this.disadvantages.addAll(sheetDisadvantages);
        this.skills.addAll(sheetSkills);
        this.spells.addAll(sheetSpells);
        this.notes.addAll(sheetNotes);
        this.attributes = sheetAttributes;
        this.secondaryCharacteristics = sheetSecondaryCharacteristics;
        this.damageResistances = sheetDamageResistances;
    }
}
