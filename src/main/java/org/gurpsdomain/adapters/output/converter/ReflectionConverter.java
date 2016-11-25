package org.gurpsdomain.adapters.output.converter;

import org.gurpsdomain.adapters.output.SheetConverter;
import org.gurpsdomain.adapters.output.domain.*;
import org.gurpsdomain.domain.Advantage;
import org.gurpsdomain.domain.Skill;
import org.gurpsdomain.domain.Note;
import org.gurpsdomain.domain.Cost;
import org.gurpsdomain.domain.Modifier;
import org.gurpsdomain.domain.Sheet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.gurpsdomain.adapters.output.converter.ReflectionCaller.call;
import static org.gurpsdomain.adapters.output.converter.ReflectionReader.read;

public class ReflectionConverter implements SheetConverter {
    private ReflectionReader readName = read("name");
    private ReflectionReader readCost = read("cost");
    private ReflectionReader readPageReference = read("pageReference");
    private ReflectionReader readModifiers = read("modifiers");
    private ReflectionReader readValue = read("value");
    private ReflectionReader readType = read("type");
    private ReflectionReader readControllingAttribute = read("controllingAttribute");
    private ReflectionReader readDifficultyLevel = read("difficultyLevel");
    private ReflectionReader readAdvantages = read("advantages");
    private ReflectionReader readSkills = read("skills");
    private ReflectionReader readMetaData = read("metaData");
    private ReflectionReader readNote = read("note");
    private ReflectionReader readNotes = read("notes");
    private ReflectionCaller callCost = call("cost");
    private Sheet sheet;


    @Override
    public SheetSheet convert(Sheet sheet) {
        this.sheet = sheet;
        return new SheetSheet(metaData(), points(), advantages(), skills(), notes(), attributes());
    }

    private Map<String, String> metaData() {
        return readMetaData.from(sheet);
    }

    private SheetPoints points() {
        return new SheetPoints(read("points", "total").from(sheet), read("points", "advantages").from(sheet), read("points", "skills").from(sheet));
    }

    private SheetAttributes attributes() {
        return new SheetAttributes(read("attributes", "health").from(sheet), read("attributes", "dexterity").from(sheet), read("attributes", "intelligence").from(sheet), read("attributes", "strength").from(sheet), read("attributes", "will").from(sheet), read("attributes", "perception").from(sheet));
    }

    private List<SheetAdvantage> advantages() {
        List<SheetAdvantage> sheetAdvantages = new ArrayList<>();
        List<Advantage> domainAdvantages = readAdvantages.from(sheet);

        for (Advantage domainAdvantage : domainAdvantages) {
            SheetAdvantage sheetAdvantage = new SheetAdvantage(readName.from(domainAdvantage), callCost.of(domainAdvantage), readPageReference.from(domainAdvantage));

            for (Modifier modifier : (List<Modifier>) readModifiers.from(domainAdvantage)) {
                Cost cost = readCost.from(modifier);
                SheetCost sheetCost = new SheetCost(readValue.from(cost), readType.from(cost));
                SheetModifier sheetModifier = new SheetModifier(readName.from(modifier), sheetCost, readPageReference.from(modifier));
                sheetAdvantage.addModifier(sheetModifier);
            }
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
