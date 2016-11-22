package org.gurpsdomain.adapters.output.converter;

import org.gurpsdomain.adapters.output.SheetConverter;
import org.gurpsdomain.adapters.output.domain.*;
import org.gurpsdomain.domain.Advantage;
import org.gurpsdomain.domain.Skill;
import org.gurpsdomain.domain.Cost;
import org.gurpsdomain.domain.Modifier;
import org.gurpsdomain.domain.Sheet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.gurpsdomain.adapters.output.converter.ReflectionCaller.call;
import static org.gurpsdomain.adapters.output.converter.ReflectionReader.read;

public class ReflectionConverter implements SheetConverter {
    @Override
    public SheetSheet convert(Sheet sheet) {
        Map<String, String> metaData;
        metaData = read("metaData").from(sheet);
        String note = read("note").from(sheet);

        SheetPoints sheetPoints = new SheetPoints(read("points", "total").from(sheet), read("points", "advantages").from(sheet));
        List<SheetAdvantage> sheetAdvantages = new ArrayList<>();
        List<Advantage> originalAdvantages = read("advantages").from(sheet);
        List<SheetSkill> sheetSkills = new ArrayList<>();
        List<Skill> originalSkills = read("skills").from(sheet);
        ReflectionReader readName = read("name");
        ReflectionCaller callCost = call("cost");
        ReflectionReader readCost = read("cost");
        ReflectionReader readPageReference = read("pageReference");
        ReflectionReader readModifiers = read("modifiers");
        ReflectionReader readValue = read("value");
        ReflectionReader readType = read("type");
        ReflectionReader readControllingAttribute = read("controllingAttribute");
        ReflectionReader readDifficultyLevel = read("difficultyLevel");
        for (Advantage originalAdvantage : originalAdvantages) {
            SheetAdvantage sheetAdvantage = new SheetAdvantage(readName.from(originalAdvantage), callCost.of(originalAdvantage), readPageReference.from(originalAdvantage));

            for (Modifier modifier : (List<Modifier>) readModifiers.from(originalAdvantage)) {
                Cost cost = readCost.from(modifier);
                SheetCost sheetCost = new SheetCost(readValue.from(cost), readType.from(cost));
                SheetModifier sheetModifier = new SheetModifier(readName.from(modifier), sheetCost, readPageReference.from(modifier));
                sheetAdvantage.addModifier(sheetModifier);
            }
            sheetAdvantages.add(sheetAdvantage);
        }

        for (Skill originalSkill : originalSkills) {
            SheetSkill sheetSkill= new SheetSkill(readName.from(originalSkill), readCost.from(originalSkill), readPageReference.from(originalSkill), readControllingAttribute.from(originalSkill), readDifficultyLevel.from(originalSkill));
            sheetSkills.add(sheetSkill);
        }


        return new SheetSheet(metaData, sheetPoints, sheetAdvantages, sheetSkills, note);
    }
}
