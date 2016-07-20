package org.gurpsdomain;

import org.gurpsdomain.adapters.input.SheetInput;
import org.gurpsdomain.adapters.output.SheetOutput;
import org.gurpsdomain.domain.Sheet;

public class Pipeline {
    public static Pipeline flow(SheetInput input) {
        return new Pipeline(input);
    }

    private final Sheet sheet;

    private Pipeline(SheetInput input) {
        this.sheet = input.produce();
    }

    public void into(SheetOutput output) {
        org.gurpsdomain.adapters.output.domain.Sheet outputSheet = sheet.output();
        output.export(outputSheet);
    }
}
