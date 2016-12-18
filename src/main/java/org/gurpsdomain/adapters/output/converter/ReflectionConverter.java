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
    private Reflection readAttributes = withReflectionChain(read("attributes"));
    private Reflection callCost = withReflectionChain(call("cost"));
    private Reflection callHealth = withReflectionChain(call("health"));
    private Reflection callDexterity = withReflectionChain(call("dexterity"));
    private Reflection callIntelligence = withReflectionChain(call("intelligence"));
    private Reflection callStrength = withReflectionChain(call("strength"));
    private Reflection callWill = withReflectionChain(call("will"));
    private Reflection callPerception = withReflectionChain(call("perception"));
    private Reflection callBasicLift = withReflectionChain(call("basicLift"));
    private Reflection callHitPoints = withReflectionChain(call("hitPoints"));
    private Reflection callFatiguePoints = withReflectionChain(call("fatiguePoints"));
    private Reflection callBasicSpeed = withReflectionChain(call("basicSpeed"));
    private Reflection callBasicMove = withReflectionChain(call("basicMove"));
    private Sheet domainSheet;


    @Override
    public SheetSheet convert(Sheet sheet) {
        this.domainSheet = sheet;
        return new SheetSheet(metaData(), points(), advantages(), skills(), notes(), attributes(), secondaryCharacteristics());
    }

    private Map<String, String> metaData() {
        return readMetaData.from(domainSheet);
    }

    private SheetPoints points() {
        return new SheetPoints(
                withReflectionChain(read("points"), read("total")).from(domainSheet),
                withReflectionChain(read("points"), read("advantages")).from(domainSheet),
                withReflectionChain(read("points"), read("skills")).from(domainSheet));
    }

    private List<SheetAdvantage> advantages() {
        List<SheetAdvantage> sheetAdvantages = new ArrayList<>();
        List<Advantage> domainAdvantages = readAdvantages.from(domainSheet);
        for (Advantage domainAdvantage : domainAdvantages) {
            List<SheetModifier> sheetModifiers = new ArrayList<SheetModifier>();
            for (Modifier modifier : (List<Modifier>) readModifiers.from(domainAdvantage)) {
                Cost cost = readCost.from(modifier);
                SheetCost sheetCost = new SheetCost(readValue.from(cost), readType.from(cost));
                SheetModifier sheetModifier = new SheetModifier(
                        readName.from(modifier),
                        sheetCost,
                        readPageReference.from(modifier));
                sheetModifiers.add(sheetModifier);
            }
            SheetAdvantage sheetAdvantage = new SheetAdvantage(
                    readName.from(domainAdvantage),
                    callCost.from(domainAdvantage),
                    ((List<AdvantageLevel>) readLevels.from(domainAdvantage)).size(),
                    readPageReference.from(domainAdvantage),
                    sheetModifiers);
            sheetAdvantages.add(sheetAdvantage);
        }
        return sheetAdvantages;
    }

    private List<SheetSkill> skills() {
        List<SheetSkill> sheetSkills = new ArrayList<>();
        List<Skill> domainSkills = readSkills.from(domainSheet);
        Attributes domainAttributes = readAttributes.from(domainSheet);
        Reflection callLevel = withReflectionChain(call("level", domainAttributes));

        for (Skill domainSkill : domainSkills) {
            SheetSkill sheetSkill = new SheetSkill(
                    readName.from(domainSkill),
                    readCost.from(domainSkill),
                    readPageReference.from(domainSkill),
                    readControllingAttribute.from(domainSkill),
                    readDifficultyLevel.from(domainSkill),
                    callLevel.from(domainSkill));
            sheetSkills.add(sheetSkill);
        }
        return sheetSkills;
    }

    private List<SheetNote> notes() {
        List<SheetNote> sheetNotes = new ArrayList<>();
        List<Note> domainNotes = readNotes.from(domainSheet);
        for (Note domainNote : domainNotes) {
            SheetNote sheetNote = new SheetNote(readName.from(domainNote), readNote.from(domainNote));
            sheetNotes.add(sheetNote);
        }
        return sheetNotes;
    }

    private SheetAttributes attributes() {
        Attributes domainAttributes = readAttributes.from(domainSheet);
        return new SheetAttributes(
                callHealth.from(domainAttributes),
                callDexterity.from(domainAttributes),
                callIntelligence.from(domainAttributes),
                callStrength.from(domainAttributes));
    }

    private SheetSecondaryCharacteristics secondaryCharacteristics() {
        Attributes attributes = readAttributes.from(domainSheet);
        return new SheetSecondaryCharacteristics(
                callWill.from(attributes),
                callPerception.from(attributes),
                callBasicLift.from(attributes),
                callHitPoints.from(attributes),
                callFatiguePoints.from(attributes),
                callBasicSpeed.from(attributes),
                callBasicMove.from(attributes),
                withReflectionChain(read("attributes"), read("damageThrusting")).from(domainSheet).toString(),
                withReflectionChain(read("attributes"), read("damageSwinging")).from(domainSheet).toString());
    }
}
