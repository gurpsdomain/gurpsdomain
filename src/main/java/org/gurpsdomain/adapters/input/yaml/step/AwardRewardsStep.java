package org.gurpsdomain.adapters.input.yaml.step;

import org.gurpsdomain.adapters.input.yaml.domain.InputSheet;
import org.gurpsdomain.domain.SheetBuilder;

import java.util.List;

public class AwardRewardsStep implements YamlBuildStep {

    @Override
    public void build(InputSheet data, SheetBuilder sheetBuilder) {
        List<Integer> rewards = data.rewards;
        if (!(rewards == null)) {
            for (Integer reward : rewards) {
                sheetBuilder.award(reward);
            }
        }
    }
}
