package org.gurpsdomain.adapters.input.yaml.step;

import org.gurpsdomain.domain.SheetBuilder;

import java.util.Map;

public class SetBasePointsStep implements YamlBuildStep {

    @Override
    public void build(Map<String, Object> data, SheetBuilder sheetBuilder) {
        sheetBuilder.award((Integer) data.get("basepoints"));
    }
}