package org.gurpsdomain.domain;

public class SheetBuilder {
    public static SheetBuilder builder(int earned) {
        return new SheetBuilder(earned);
    }

    private Sheet sheet;

    private SheetBuilder(int earned) {
        this.sheet = new Sheet(earned);
    }

    public void award(int amount) {
        sheet.award(amount);
    }

    public  void addAdvantage(Advantage advantage) { sheet.addAdvantage(advantage);}

    public Sheet build() {
        return sheet;
    }
}
