package org.gurpsdomain.adapters.output.converter;

import org.gurpsdomain.adapters.output.SheetConverter;
import org.gurpsdomain.adapters.output.domain.*;
import org.gurpsdomain.domain.Advantage;
import org.gurpsdomain.domain.Cost;
import org.gurpsdomain.domain.Modifier;
import org.gurpsdomain.domain.Sheet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.gurpsdomain.adapters.output.converter.ReflectionReader.read;

public class ReflectionConverter implements SheetConverter {
    @Override
    public SheetSheet convert(Sheet sheet) {
        Map<String, String> metaData = new HashMap<String, String>();
        metaData = read("metaData").from(sheet);

        SheetPoints sheetPoints = new SheetPoints(read("points", "total").from(sheet), read("points", "advantages").from(sheet));
        List<SheetAdvantage> sheetAdvantages = new ArrayList<SheetAdvantage>();
        List<Advantage> originalAdvantages = read("advantages").from(sheet);
        ReflectionReader readName = read("name");
        ReflectionReader readCost = read("cost");
        ReflectionReader readModifiers = read("modifiers");
        ReflectionReader readValue = read("value");
        ReflectionReader readType = read("type");
        for (Advantage originalAdvantage : originalAdvantages) {
            SheetAdvantage sheetAdvantage = new SheetAdvantage(readName.from(originalAdvantage), readCost.from(originalAdvantage));

            for(Modifier modifier : (List<Modifier>) readModifiers.from(originalAdvantage)) {
                Cost cost = readCost.from(modifier);
                SheetCost sheetCost = new SheetCost(readValue.from(cost), readType.from(cost));
                SheetModifier sheetModifier = new SheetModifier(readName.from(modifier), sheetCost);
                sheetAdvantage.addModifier(sheetModifier);
            }
            sheetAdvantages.add(sheetAdvantage);
        }
        return new SheetSheet(metaData, sheetPoints, sheetAdvantages);
    }
}
