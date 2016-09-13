package org.gurpsdomain.adapters.input.yaml.step;

import org.gurpsdomain.domain.SheetBuilder;

import java.util.Map;

public interface YamlBuildStep {
    void build(Map<String, Object> data, SheetBuilder sheetBuilder);
}
