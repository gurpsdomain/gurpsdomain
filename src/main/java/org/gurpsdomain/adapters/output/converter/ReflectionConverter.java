package org.gurpsdomain.adapters.output.converter;

import org.gurpsdomain.adapters.output.SheetConverter;
import org.gurpsdomain.adapters.output.domain.*;
import org.gurpsdomain.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.gurpsdomain.adapters.output.converter.Reflection.call;
import static org.gurpsdomain.adapters.output.converter.Reflection.read;
import static org.gurpsdomain.adapters.output.converter.Reflection.withReflectionChain;

public class ReflectionConverter implements SheetConverter {

    private Sheet domainSheet;
    private List<Advantage> domainAdvantages;
    private List<Skill> domainSkills;
    private List<Spell> domainSpells;
    private Reflection name = withReflectionChain(read("name"));
    private Reflection cost = withReflectionChain(read("cost"));
    private Reflection level = withReflectionChain(read("level"));
    private Reflection pageReference = withReflectionChain(read("pageReference"));
    private Reflection colleges = withReflectionChain(read("colleges"));
    private Reflection powerSource = withReflectionChain(read("powerSource"));
    private Reflection spellClasses = withReflectionChain(read("spellClasses"));
    private Reflection maintenanceCost = withReflectionChain(read("maintenanceCost"));
    private Reflection castingTime = withReflectionChain(read("castingTime"));
    private Reflection duration = withReflectionChain(read("duration"));
    private Reflection modifiers = withReflectionChain(read("modifiers"));
    private Reflection value = withReflectionChain(read("value"));
    private Reflection type = withReflectionChain(read("type"));
    private Reflection controllingAttribute = withReflectionChain(read("controllingAttribute"));
    private Reflection difficultyLevel = withReflectionChain(read("difficultyLevel"));
    private Reflection advantages = withReflectionChain(read("advantages"));
    private Reflection skills = withReflectionChain(read("skills"));
    private Reflection spells = withReflectionChain(read("spells"));
    private Reflection metaData = withReflectionChain(read("metaData"));
    private Reflection note = withReflectionChain(read("note"));
    private Reflection notes = withReflectionChain(read("notes"));
    private Reflection attributes = withReflectionChain(read("attributes"));
    private Reflection pointsTotal = withReflectionChain(read("points"), read("total"));
    private Reflection pointsAdvantages = withReflectionChain(read("points"), read("advantages"));
    private Reflection pointsDisadvantages = withReflectionChain(read("points"), read("disadvantages"));
    private Reflection pointsSkills = withReflectionChain(read("points"), read("skills"));
    private Reflection pointsSpells = withReflectionChain(read("points"), read("spells"));
    private Reflection pointsUnspent = withReflectionChain(read("points"), read("unspent"));
    private Reflection callCost = withReflectionChain(call("cost"));
    private Reflection health = withReflectionChain(call("health"));
    private Reflection dexterity = withReflectionChain(call("dexterity"));
    private Reflection intelligence = withReflectionChain(call("intelligence"));
    private Reflection strength = withReflectionChain(call("strength"));
    private Reflection will = withReflectionChain(call("will"));
    private Reflection perception = withReflectionChain(call("perception"));
    private Reflection vision = withReflectionChain(call("vision"));
    private Reflection hearing = withReflectionChain(call("hearing"));
    private Reflection tasteAndSmell = withReflectionChain(call("tasteAndSmell"));
    private Reflection touch = withReflectionChain(call("touch"));
    private Reflection basicLiftImperialSystem = withReflectionChain(call("basicLiftAsStringInImperialSystem"));
    private Reflection basicLiftRealMetricSystem = withReflectionChain(call("basicLiftAsStringInRealMetricSystem"));
    private Reflection basicLiftGameMetricSystem = withReflectionChain(call("basicLiftAsStringInGameMetricSystem"));
    private Reflection hitPoints = withReflectionChain(call("hitPoints"));
    private Reflection fatiguePoints = withReflectionChain(call("fatiguePoints"));
    private Reflection basicSpeed = withReflectionChain(call("basicSpeed"));
    private Reflection basicMove = withReflectionChain(call("basicMove"));
    private Reflection damageThrusting = withReflectionChain(call("damageThrustingAsString"));
    private Reflection damageSwinging = withReflectionChain(call("damageSwingingAsString"));
    private Reflection frightCheck = withReflectionChain(call("frightCheck"));

    @Override
    public SheetSheet convert(Sheet sheet) {
        setDomainSheetData(sheet);
        return new SheetSheet(metaData(), points(), advantages(), disadvantages(), skills(), spells(), notes(), attributes(), secondaryCharacteristics());
    }

    private void setDomainSheetData(Sheet sheet) {
        this.domainSheet = sheet;
        this.domainAdvantages = advantages.from(domainSheet);
        this.domainSkills = skills.from(domainSheet);
        this.domainSpells = spells.from(domainSheet);
    }

    private Map<String, String> metaData() {
        return metaData.from(domainSheet);
    }

    private SheetPoints points() {
        return new SheetPoints(
                pointsTotal.from(domainSheet),
                pointsAdvantages.from(domainSheet),
                pointsDisadvantages.from(domainSheet),
                pointsSkills.from(domainSheet),
                pointsSpells.from(domainSheet),
                pointsUnspent.from(domainSheet));
    }

    private List<SheetAdvantage> advantages() {
        return domainAdvantages.stream()
                .filter(a -> (int) callCost.from(a) >= 0)
                .map(this::sheetAdvantageFromDomainAdvantage)
                .collect(Collectors.toList());
    }

    private List<SheetAdvantage> disadvantages() {
        return domainAdvantages.stream()
                .filter(a -> (int) callCost.from(a) < 0)
                .map(this::sheetAdvantageFromDomainAdvantage)
                .collect(Collectors.toList());
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

    private List<SheetSpell> spells() {
        List<SheetSpell> sheetSpells = new ArrayList<>();
        for (Spell domainSpell : domainSpells) {
            sheetSpells.add(sheetSpellFromDomainSpell(domainSpell));
        }
        return sheetSpells;
    }

    private SheetSpell sheetSpellFromDomainSpell(Spell domainSpell) {
        Attributes domainAttributes = attributes.from(domainSheet);
        Reflection level = withReflectionChain(call("level", domainAttributes));
        return new SheetSpell(
                name.from(domainSpell),
                cost.from(domainSpell),
                level.from(domainSpell),
                difficultyLevel.from(domainSpell),
                pageReference.from(domainSpell),
                colleges.from(domainSpell),
                powerSource.from(domainSpell),
                spellClasses.from(domainSpell),
                maintenanceCost.from(domainSpell),
                castingTime.from(domainSpell),
                duration.from(domainSpell));
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
        SheetBasicLift basicLift = new SheetBasicLift(basicLiftRealMetricSystem.from(attributes), basicLiftGameMetricSystem.from(attributes), basicLiftImperialSystem.from(attributes));
        return new SheetSecondaryCharacteristics(
                will.from(attributes),
                perception.from(attributes),
                vision.from(attributes),
                hearing.from(attributes),
                tasteAndSmell.from(attributes),
                touch.from(attributes),
                basicLift,
                hitPoints.from(attributes),
                fatiguePoints.from(attributes),
                basicSpeed.from(attributes),
                basicMove.from(attributes),
                damageThrusting.from(attributes),
                damageSwinging.from(attributes),
                frightCheck.from(attributes));
    }
}
