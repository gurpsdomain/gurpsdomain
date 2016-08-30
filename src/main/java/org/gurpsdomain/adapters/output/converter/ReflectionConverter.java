package org.gurpsdomain.adapters.output.converter;

import org.gurpsdomain.adapters.output.SheetConverter;
import org.gurpsdomain.adapters.output.domain.Advantage;
import org.gurpsdomain.adapters.output.domain.Points;
import org.gurpsdomain.adapters.output.domain.Sheet;

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
        for (org.gurpsdomain.domain.Advantage originalAdvantage : originalAdvantages) {
            advantages.add(new Advantage(readName.from(originalAdvantage), readCost.from(originalAdvantage)));
        }
        return new Sheet(metaData, points, advantages);
    }
}
