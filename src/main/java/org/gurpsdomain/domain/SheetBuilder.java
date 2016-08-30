package org.gurpsdomain.domain;

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

    public  void addMetaData(String key, String value) { sheet.setMetaDataProperty(key, value);}

    public Sheet build() {
        return sheet;
    }
}
