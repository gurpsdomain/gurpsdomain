package org.gurpsdomain.adapters.input.yaml.step;

import org.gurpsdomain.adapters.input.yaml.domain.InputSheet;
import org.gurpsdomain.domain.SheetBuilder;

import java.util.Arrays;
import java.util.Collection;

public abstract class CompositeStep implements YamlBuildStep {
    private final Collection<YamlBuildStep> steps;

    public CompositeStep(YamlBuildStep... steps) {
        this.steps = Arrays.asList(steps);
    }

    @Override
    public void build(InputSheet data, SheetBuilder sheetBuilder) {
        for (YamlBuildStep step: steps) {
            step.build(data, sheetBuilder);
        }
    }
}
