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

        SheetPoints sheetPoints = new SheetPoints(read("points", "total").from(sheet), read("points", "advantages").from(sheet), read("points", "skills").from(sheet));
        List<SheetAdvantage> sheetAdvantages = new ArrayList<>();
        List<Advantage> domainAdvantages = read("advantages").from(sheet);
        List<SheetSkill> sheetSkills = new ArrayList<>();
        List<Skill> domainSkills = read("skills").from(sheet);
        ReflectionReader readName = read("name");
        ReflectionCaller callCost = call("cost");
        ReflectionReader readCost = read("cost");
        ReflectionReader readPageReference = read("pageReference");
        ReflectionReader readModifiers = read("modifiers");
        ReflectionReader readValue = read("value");
        ReflectionReader readType = read("type");
        ReflectionReader readControllingAttribute = read("controllingAttribute");
        ReflectionReader readDifficultyLevel = read("difficultyLevel");
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

        for (Skill domainSkill : domainSkills) {
            SheetSkill sheetSkill= new SheetSkill(readName.from(domainSkill), readCost.from(domainSkill), readPageReference.from(domainSkill), readControllingAttribute.from(domainSkill), readDifficultyLevel.from(domainSkill));
            sheetSkills.add(sheetSkill);
        }


        return new SheetSheet(metaData, sheetPoints, sheetAdvantages, sheetSkills, note);
    }
}
