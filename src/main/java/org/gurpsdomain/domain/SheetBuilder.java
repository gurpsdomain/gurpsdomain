package org.gurpsdomain.domain;

import static org.gurpsdomain.domain.Util.slice;

public class SheetBuilder {
    public static SheetBuilder builder() {
        return new SheetBuilder();
    }

    private Sheet sheet;

    private SheetBuilder() {
        this.sheet = new Sheet();
    }

    public void award(int amount) {
        sheet.award(amount);
    }

    public  void addAdvantage(Advantage advantage) { sheet.addAdvantage(advantage);}

    public  void addMetaData(String... keys) {
        String value = keys[keys.length - 1];
        keys = slice(keys, 0, keys.length - 1);
        sheet.setMetaDataProperty(keys[0], value);
    }

    public Sheet build() {
        return sheet;
    }
}

class Util {
    public static String[] slice(String[] original, int startInclusive, int finishExclusive) {
        String[] slice = new String[finishExclusive - startInclusive];
        for (int index = startInclusive; index < finishExclusive; index++) {
            slice[index - startInclusive] = original[index];
        }
        return slice;
    }
}
