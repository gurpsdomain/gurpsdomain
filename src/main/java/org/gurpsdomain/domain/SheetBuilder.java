package org.gurpsdomain.domain;

import org.gurpsdomain.adapters.output.domain.Sheet;

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

    public Sheet build() {
        return sheet;
    }
}
