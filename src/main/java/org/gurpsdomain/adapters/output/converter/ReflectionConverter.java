package org.gurpsdomain.adapters.output.converter;

import org.gurpsdomain.adapters.output.SheetConverter;
import org.gurpsdomain.adapters.output.domain.Points;
import org.gurpsdomain.adapters.output.domain.Sheet;

import static org.gurpsdomain.adapters.output.converter.ReflectionReader.read;

public class ReflectionConverter implements SheetConverter {
    @Override
    public Sheet convert(org.gurpsdomain.domain.Sheet sheet) {
        return new Sheet(new Points(read("points", "total").from(sheet)));
    }
}
