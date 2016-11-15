package org.gurpsdomain.adapters.output;

import org.gurpsdomain.adapters.output.domain.SheetSheet;

public interface SheetConverter {
    SheetSheet convert(org.gurpsdomain.domain.Sheet sheet);
}
