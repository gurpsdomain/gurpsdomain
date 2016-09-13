package org.gurpsdomain.adapters.input.yaml.step;

import org.gurpsdomain.domain.SheetBuilder;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

public abstract class CompositeStep implements YamlBuildStep {
    private final Collection<YamlBuildStep> steps;

    public CompositeStep(YamlBuildStep... steps) {
        this.steps = Arrays.asList(steps);
    }

    @Override
    public void build(Map<String, Object> data, SheetBuilder sheetBuilder) {
        for (YamlBuildStep step: steps) {
            step.build(data, sheetBuilder);
        }
    }
}
