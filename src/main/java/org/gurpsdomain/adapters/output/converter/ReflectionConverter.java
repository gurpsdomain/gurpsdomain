package org.gurpsdomain.adapters.output.converter;

import org.gurpsdomain.adapters.output.SheetConverter;
import org.gurpsdomain.adapters.output.domain.*;
import org.gurpsdomain.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.gurpsdomain.adapters.output.converter.Reflection.call;
import static org.gurpsdomain.adapters.output.converter.Reflection.read;
import static org.gurpsdomain.adapters.output.converter.Reflection.withReflection;

public class ReflectionConverter implements SheetConverter {

    private Reflection readName = withReflection(read("name"));
    private Reflection readCost = withReflection(read("cost"));
    private Reflection readLevels = withReflection(read("levels"));
    private Reflection readPageReference = withReflection(read("pageReference"));
    private Reflection readModifiers = withReflection(read("modifiers"));
    private Reflection readValue = withReflection(read("value"));
    private Reflection readType = withReflection(read("type"));
    private Reflection readControllingAttribute = withReflection(read("controllingAttribute"));
    private Reflection readDifficultyLevel = withReflection(read("difficultyLevel"));
    private Reflection readAdvantages = withReflection(read("advantages"));
    private Reflection readSkills = withReflection(read("skills"));
    private Reflection readMetaData = withReflection(read("metaData"));
    private Reflection readNote = withReflection(read("note"));
    private Reflection readNotes = withReflection(read("notes"));
    private Reflection callCost = withReflection(call("cost"));
    private Sheet sheet;


    @Override
    public SheetSheet convert(Sheet sheet) {
        this.sheet = sheet;
        return new SheetSheet(metaData(), points(), advantages(), skills(), notes(), attributes(), secondaryCharacteristics());
    }

    private Map<String, String> metaData() {
        return readMetaData.from(sheet);
    }

    private SheetPoints points() {
        return new SheetPoints(withReflection(read("points"),read("total")).from(sheet), withReflection(read("points"),read( "advantages")).from(sheet), withReflection(read("points"),read( "skills")).from(sheet));
    }

    private SheetAttributes attributes() {
        Attributes attributes = withReflection(read("attributes")).from(sheet);
        return new SheetAttributes(
                withReflection(call("health")).from(attributes),
                withReflection(call("dexterity")).from(attributes),
                withReflection(call("intelligence")).from(attributes),
                withReflection(call("strength")).from(attributes));
    }

    private SheetSecondaryCharacteristics secondaryCharacteristics() {
        Attributes attributes = withReflection(read("attributes")).from(sheet);
        return new SheetSecondaryCharacteristics(
                withReflection(call("will")).from(attributes),
                withReflection(call("perception")).from(attributes),
                withReflection(call("basicLift")).from(attributes),
                withReflection(call("hitPoints")).from(attributes),
                withReflection(call("fatiguePoints")).from(attributes),
                withReflection(call("basicSpeed")).from(attributes),
                withReflection(call("basicMove")).from(attributes),
                withReflection(read("attributes"),read( "damageThrusting")).from(sheet).toString(),
                withReflection(read("attributes"),read( "damageSwinging")).from(sheet).toString());
    }

    private List<SheetAdvantage> advantages() {
        List<SheetAdvantage> sheetAdvantages = new ArrayList<>();
        List<Advantage> domainAdvantages = readAdvantages.from(sheet);

        for (Advantage domainAdvantage : domainAdvantages) {

            List<SheetModifier> sheetModifiers = new ArrayList<SheetModifier>();

            for (Modifier modifier : (List<Modifier>) readModifiers.from(domainAdvantage)) {
                Cost cost = readCost.from(modifier);
                SheetCost sheetCost = new SheetCost(readValue.from(cost), readType.from(cost));
                SheetModifier sheetModifier = new SheetModifier(readName.from(modifier), sheetCost, readPageReference.from(modifier));
                sheetModifiers.add(sheetModifier);
            }

            SheetAdvantage sheetAdvantage = new SheetAdvantage(readName.from(domainAdvantage), callCost.from(domainAdvantage), ((List<AdvantageLevel>) readLevels.from(domainAdvantage)).size(), readPageReference.from(domainAdvantage), sheetModifiers);

            sheetAdvantages.add(sheetAdvantage);
        }
        return sheetAdvantages;
    }

    private List<SheetSkill> skills() {
        List<SheetSkill> sheetSkills = new ArrayList<>();
        List<Skill> domainSkills = readSkills.from(sheet);

        for (Skill domainSkill : domainSkills) {
            SheetSkill sheetSkill = new SheetSkill(readName.from(domainSkill), readCost.from(domainSkill), readPageReference.from(domainSkill), readControllingAttribute.from(domainSkill), readDifficultyLevel.from(domainSkill));
            sheetSkills.add(sheetSkill);
        }
        return sheetSkills;
    }

    private List<SheetNote> notes() {
        List<SheetNote> sheetNotes = new ArrayList<>();
        List<Note> domainNotes = readNotes.from(sheet);

        for (Note domainNote : domainNotes) {
            SheetNote sheetNote = new SheetNote(readName.from(domainNote), readNote.from(domainNote));
            sheetNotes.add(sheetNote);
        }
        return sheetNotes;
    }
}
