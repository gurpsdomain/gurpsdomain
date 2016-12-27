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

    private Sheet domainSheet;
    private List<Advantage> domainAdvantages;
    private List<Skill> domainSkills;
    private Reflection name = withReflectionChain(read("name"));
    private Reflection cost = withReflectionChain(read("cost"));
    private Reflection levels = withReflectionChain(read("levels"));
    private Reflection level = withReflectionChain(read("level"));
    private Reflection pageReference = withReflectionChain(read("pageReference"));
    private Reflection modifiers = withReflectionChain(read("modifiers"));
    private Reflection value = withReflectionChain(read("value"));
    private Reflection type = withReflectionChain(read("type"));
    private Reflection controllingAttribute = withReflectionChain(read("controllingAttribute"));
    private Reflection difficultyLevel = withReflectionChain(read("difficultyLevel"));
    private Reflection advantages = withReflectionChain(read("advantages"));
    private Reflection skills = withReflectionChain(read("skills"));
    private Reflection metaData = withReflectionChain(read("metaData"));
    private Reflection note = withReflectionChain(read("note"));
    private Reflection notes = withReflectionChain(read("notes"));
    private Reflection attributes = withReflectionChain(read("attributes"));
    private Reflection pointsTotal = withReflectionChain(read("points"), read("total"));
    private Reflection pointsAdvantages = withReflectionChain(read("points"), read("advantages"));
    private Reflection pointsSkills = withReflectionChain(read("points"), read("skills"));
    private Reflection pointsUnspent = withReflectionChain(read("points"), read("unspent"));
    private Reflection callCost = withReflectionChain(call("cost"));
    private Reflection health = withReflectionChain(call("health"));
    private Reflection dexterity = withReflectionChain(call("dexterity"));
    private Reflection intelligence = withReflectionChain(call("intelligence"));
    private Reflection strength = withReflectionChain(call("strength"));
    private Reflection will = withReflectionChain(call("will"));
    private Reflection perception = withReflectionChain(call("perception"));
    private Reflection basicLift = withReflectionChain(call("basicLift"));
    private Reflection hitPoints = withReflectionChain(call("hitPoints"));
    private Reflection fatiguePoints = withReflectionChain(call("fatiguePoints"));
    private Reflection basicSpeed = withReflectionChain(call("basicSpeed"));
    private Reflection basicMove = withReflectionChain(call("basicMove"));

    @Override
    public SheetSheet convert(Sheet sheet) {
        setDomainSheetData(sheet);
        return new SheetSheet(metaData(), points(), advantages(), skills(), notes(), attributes(), secondaryCharacteristics());
    }

    private void setDomainSheetData(Sheet sheet) {
        this.domainSheet = sheet;
        this.domainAdvantages = advantages.from(domainSheet);
        this.domainSkills = skills.from(domainSheet);
    }

    private Map<String, String> metaData() {
        return metaData.from(domainSheet);
    }

    private SheetPoints points() {
        return new SheetPoints(
                pointsTotal.from(domainSheet),
                pointsAdvantages.from(domainSheet),
                pointsSkills.from(domainSheet),
                pointsUnspent.from(domainSheet));
    }

    private List<SheetAdvantage> advantages() {
        List<SheetAdvantage> sheetAdvantages = new ArrayList<>();
        for (Advantage domainAdvantage : domainAdvantages) {
            sheetAdvantages.add(sheetAdvantageFromDomainAdvantage(domainAdvantage));
        }
        return sheetAdvantages;
    }

    private SheetAdvantage sheetAdvantageFromDomainAdvantage(Advantage domainAdvantage) {
        List<Modifier> domainModifiers = modifiers.from(domainAdvantage);
        List<SheetModifier> sheetModifiers = sheetModifiersFromDomainModifiers(domainModifiers);
        if (domainAdvantage instanceof LeveledAdvantage) {
            return new SheetLeveledAdvantage(
                    name.from(domainAdvantage),
                    callCost.from(domainAdvantage),
                    level.from(domainAdvantage),
                    pageReference.from(domainAdvantage),
                    sheetModifiers);
        } else {
            return new SheetAdvantage(
                    name.from(domainAdvantage),
                    callCost.from(domainAdvantage),
                    pageReference.from(domainAdvantage),
                    sheetModifiers);
        }
    }

    private List<SheetModifier> sheetModifiersFromDomainModifiers(List<Modifier> domainModifiers) {
        List<SheetModifier> sheetModifiers = new ArrayList<>();
        for (Modifier domainModifier : domainModifiers) {
            sheetModifiers.add(sheetModifierFromDomainModifier(domainModifier));
        }
        return sheetModifiers;
    }

    private SheetModifier sheetModifierFromDomainModifier(Modifier domainModifier) {
        Cost domainCost = cost.from(domainModifier);
        SheetCost sheetCost = new SheetCost(value.from(domainCost), type.from(domainCost));
        return new SheetModifier(
                name.from(domainModifier),
                sheetCost,
                pageReference.from(domainModifier),
                note.from(domainModifier));
    }

    private List<SheetSkill> skills() {
        List<SheetSkill> sheetSkills = new ArrayList<>();
        for (Skill domainSkill : domainSkills) {
            sheetSkills.add(sheetSkillFromDomainSkill(domainSkill));
        }
        return sheetSkills;
    }

    private SheetSkill sheetSkillFromDomainSkill(Skill domainSkill) {
        Attributes domainAttributes = attributes.from(domainSheet);
        Reflection level = withReflectionChain(call("level", domainAttributes));
        return new SheetSkill(
                name.from(domainSkill),
                cost.from(domainSkill),
                pageReference.from(domainSkill),
                controllingAttribute.from(domainSkill),
                difficultyLevel.from(domainSkill),
                level.from(domainSkill));
    }

    private List<SheetNote> notes() {
        List<SheetNote> sheetNotes = new ArrayList<>();
        List<Note> domainNotes = notes.from(domainSheet);
        for (Note domainNote : domainNotes) {
            SheetNote sheetNote = new SheetNote(name.from(domainNote), note.from(domainNote));
            sheetNotes.add(sheetNote);
        }
        return sheetNotes;
    }

    private SheetAttributes attributes() {
        Attributes domainAttributes = attributes.from(domainSheet);
        return new SheetAttributes(
                health.from(domainAttributes),
                dexterity.from(domainAttributes),
                intelligence.from(domainAttributes),
                strength.from(domainAttributes));
    }

    private SheetSecondaryCharacteristics secondaryCharacteristics() {
        Attributes attributes = this.attributes.from(domainSheet);
        return new SheetSecondaryCharacteristics(
                will.from(attributes),
                perception.from(attributes),
                basicLift.from(attributes),
                hitPoints.from(attributes),
                fatiguePoints.from(attributes),
                basicSpeed.from(attributes),
                basicMove.from(attributes),
                //TODO statements below to be retrieved from attributes as above
                withReflectionChain(read("attributes"), read("damageThrusting")).from(domainSheet).toString(),
                withReflectionChain(read("attributes"), read("damageSwinging")).from(domainSheet).toString());
    }
}
