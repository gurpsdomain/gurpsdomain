package org.gurpsdomain.adapters.output.converter;

import org.gurpsdomain.adapters.output.SheetConverter;
import org.gurpsdomain.adapters.output.domain.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.gurpsdomain.adapters.output.converter.ReflectionReader.read;

public class ReflectionConverter implements SheetConverter {
    @Override
    public Sheet convert(org.gurpsdomain.domain.Sheet sheet) {
        Map<String, String> metaData = new HashMap<String, String>();
        metaData = read("metaData").from(sheet);

        Points points = new Points(read("points", "total").from(sheet), read("points", "advantages").from(sheet));
        List<Advantage> advantages = new ArrayList<Advantage>();
        List<org.gurpsdomain.domain.Advantage> originalAdvantages = read("advantages").from(sheet);
        ReflectionReader readName = read("name");
        ReflectionReader readCost = read("cost");
        ReflectionReader readModifiers = read("modifiers");
        ReflectionReader readValue = read("value");
        ReflectionReader readType = read("type");
        for (org.gurpsdomain.domain.Advantage originalAdvantage : originalAdvantages) {
            Advantage sheetAdvantage = new Advantage(readName.from(originalAdvantage), readCost.from(originalAdvantage));

            for(org.gurpsdomain.domain.Modifier modifier : (List<org.gurpsdomain.domain.Modifier>) readModifiers.from(originalAdvantage)) {
                org.gurpsdomain.domain.Cost cost = readCost.from(modifier);
                Cost sheetCost = new Cost(readValue.from(cost), readType.from(cost));
                Modifier sheetModifier = new Modifier(readName.from(modifier), sheetCost);
                sheetAdvantage.addModifier(sheetModifier);
            }
            advantages.add(sheetAdvantage);
        }
        return new Sheet(metaData, points, advantages);
    }
}
