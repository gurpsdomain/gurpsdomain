package org.gurpsdomain.adapters.input.yaml.step;

import org.gurpsdomain.adapters.input.yaml.domain.InputSheet;
import org.gurpsdomain.domain.SheetBuilder;


public class SetSizeStep implements YamlBuildStep {

    @Override
    public void build(InputSheet data, SheetBuilder sheetBuilder) {
        sheetBuilder.setSize(data.size());
    }
}
