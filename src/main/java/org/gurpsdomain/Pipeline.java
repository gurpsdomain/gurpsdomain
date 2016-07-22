package org.gurpsdomain;

import org.gurpsdomain.adapters.input.SheetInput;
import org.gurpsdomain.adapters.output.SheetConverter;
import org.gurpsdomain.adapters.output.SheetOutput;
import org.gurpsdomain.adapters.output.converter.ReflectionConverter;
import org.gurpsdomain.domain.Sheet;

public class Pipeline {
    public static Pipeline flow(SheetInput input) {
        return new Pipeline(input);
    }

    private final SheetConverter converter = new ReflectionConverter();
    private final Sheet sheet;

    private Pipeline(SheetInput input) {
        this.sheet = input.produce();
    }

    public void into(SheetOutput output) {
        output.export(converter.convert(sheet));
    }
}
