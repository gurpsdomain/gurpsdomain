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
import static org.gurpsdomain.adapters.output.converter.Reflection.traverse;

public class ReflectionConverter implements SheetConverter {

    private Sheet domainSheet;
    private List<Advantage> domainAdvantages;
    private List<Skill> domainSkills;
    private List<Spell> domainSpells;
    private Reflection name = traverse(read("name"));
    private Reflection cost = traverse(read("cost"));
    private Reflection level = traverse(read("level"));
    private Reflection pageReference = traverse(read("pageReference"));
    private Reflection colleges = traverse(read("colleges"));
    private Reflection powerSource = traverse(read("powerSource"));
    private Reflection spellClasses = traverse(read("spellClasses"));
    private Reflection maintenanceCost = traverse(read("maintenanceCost"));
    private Reflection castingTime = traverse(read("castingTime"));
    private Reflection duration = traverse(read("duration"));
    private Reflection modifiers = traverse(read("modifiers"));
    private Reflection value = traverse(read("value"));
    private Reflection type = traverse(read("type"));
    private Reflection controllingAttribute = traverse(read("controllingAttribute"));
    private Reflection difficultyLevel = traverse(read("difficultyLevel"));
    private Reflection advantages = traverse(read("advantages"));
    private Reflection skills = traverse(read("skills"));
    private Reflection spells = traverse(read("spells"));
    private Reflection metaData = traverse(read("metaData"));
    private Reflection note = traverse(read("note"));
    private Reflection notes = traverse(read("notes"));
    private Reflection attributes = traverse(read("attributes"));
    private Reflection pointsTotal = traverse(read("points"), read("total"));
    private Reflection pointsAdvantages = traverse(read("points"), read("advantages"));
    private Reflection pointsDisadvantages = traverse(read("points"), read("disadvantages"));
    private Reflection pointsSkills = traverse(read("points"), read("skills"));
    private Reflection pointsSpells = traverse(read("points"), read("spells"));
    private Reflection pointsUnspent = traverse(read("points"), read("unspent"));
    private Reflection callCost = traverse(call("cost"));
    private Reflection health = traverse(call("health"));
    private Reflection dexterity = traverse(call("dexterity"));
    private Reflection intelligence = traverse(call("intelligence"));
    private Reflection strength = traverse(call("strength"));
    private Reflection will = traverse(call("will"));
    private Reflection perception = traverse(call("perception"));
    private Reflection vision = traverse(call("vision"));
    private Reflection hearing = traverse(call("hearing"));
    private Reflection tasteAndSmell = traverse(call("tasteAndSmell"));
    private Reflection touch = traverse(call("touch"));
    private Reflection basicLiftImperialSystem = traverse(call("basicLiftAsStringInImperialSystem"));
    private Reflection basicLiftRealMetricSystem = traverse(call("basicLiftAsStringInRealMetricSystem"));
    private Reflection basicLiftGameMetricSystem = traverse(call("basicLiftAsStringInGameMetricSystem"));
    private Reflection hitPoints = traverse(call("hitPoints"));
    private Reflection fatiguePoints = traverse(call("fatiguePoints"));
    private Reflection basicSpeed = traverse(call("basicSpeed"));
    private Reflection basicMove = traverse(call("basicMove"));
    private Reflection damageThrusting = traverse(call("damageThrustingAsString"));
    private Reflection damageSwinging = traverse(call("damageSwingingAsString"));
    private Reflection frightCheck = traverse(call("frightCheck"));

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
        Reflection level = traverse(call("level", domainAttributes));
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
        Reflection level = traverse(call("level", domainAttributes));
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
