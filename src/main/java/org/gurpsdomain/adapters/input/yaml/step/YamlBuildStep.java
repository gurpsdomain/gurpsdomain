package org.gurpsdomain.adapters.input.yaml.step;

import org.gurpsdomain.adapters.input.yaml.domain.InputSheet;
import org.gurpsdomain.domain.SheetBuilder;

public interface YamlBuildStep {
    void build(InputSheet data, SheetBuilder sheetBuilder);
}
