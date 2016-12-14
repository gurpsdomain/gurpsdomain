package org.gurpsdomain.adapters.output.converter;

import org.gurpsdomain.adapters.output.SheetConverter;
import org.gurpsdomain.adapters.output.domain.*;
import org.gurpsdomain.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.gurpsdomain.adapters.output.converter.Reflection.call;
import static org.gurpsdomain.adapters.output.converter.Reflection.read;
import static org.gurpsdomain.adapters.output.converter.Reflection.withReflectionChain;

public class ReflectionConverter implements SheetConverter {

    private Reflection readName = withReflectionChain(read("name"));
    private Reflection readCost = withReflectionChain(read("cost"));
    private Reflection readLevels = withReflectionChain(read("levels"));
    private Reflection readPageReference = withReflectionChain(read("pageReference"));
    private Reflection readModifiers = withReflectionChain(read("modifiers"));
    private Reflection readValue = withReflectionChain(read("value"));
    private Reflection readType = withReflectionChain(read("type"));
    private Reflection readControllingAttribute = withReflectionChain(read("controllingAttribute"));
    private Reflection readDifficultyLevel = withReflectionChain(read("difficultyLevel"));
    private Reflection readAdvantages = withReflectionChain(read("advantages"));
    private Reflection readSkills = withReflectionChain(read("skills"));
    private Reflection readMetaData = withReflectionChain(read("metaData"));
    private Reflection readNote = withReflectionChain(read("note"));
    private Reflection readNotes = withReflectionChain(read("notes"));
    private Reflection callCost = withReflectionChain(call("cost"));
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
        return new SheetPoints(withReflectionChain(read("points"),read("total")).from(sheet), withReflectionChain(read("points"),read( "advantages")).from(sheet), withReflectionChain(read("points"),read( "skills")).from(sheet));
    }

    private SheetAttributes attributes() {
        Attributes attributes = withReflectionChain(read("attributes")).from(sheet);
        return new SheetAttributes(
                withReflectionChain(call("health")).from(attributes),
                withReflectionChain(call("dexterity")).from(attributes),
                withReflectionChain(call("intelligence")).from(attributes),
                withReflectionChain(call("strength")).from(attributes));
    }

    private SheetSecondaryCharacteristics secondaryCharacteristics() {
        Attributes attributes = withReflectionChain(read("attributes")).from(sheet);
        return new SheetSecondaryCharacteristics(
                withReflectionChain(call("will")).from(attributes),
                withReflectionChain(call("perception")).from(attributes),
                withReflectionChain(call("basicLift")).from(attributes),
                withReflectionChain(call("hitPoints")).from(attributes),
                withReflectionChain(call("fatiguePoints")).from(attributes),
                withReflectionChain(call("basicSpeed")).from(attributes),
                withReflectionChain(call("basicMove")).from(attributes),
                withReflectionChain(read("attributes"),read( "damageThrusting")).from(sheet).toString(),
                withReflectionChain(read("attributes"),read( "damageSwinging")).from(sheet).toString());
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
