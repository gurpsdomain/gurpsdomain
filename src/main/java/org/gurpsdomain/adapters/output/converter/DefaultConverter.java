package org.gurpsdomain.adapters.output.converter;

import org.gurpsdomain.adapters.output.SheetConverter;
import org.gurpsdomain.adapters.output.domain.Sheet;

public class DefaultConverter implements SheetConverter{
    @Override
    public Sheet convert(org.gurpsdomain.domain.Sheet sheet) {
        return sheet.output();
    }
}
