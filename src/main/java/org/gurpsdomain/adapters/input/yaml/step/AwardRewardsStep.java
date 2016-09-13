package org.gurpsdomain.adapters.input.yaml.step;

import org.gurpsdomain.domain.SheetBuilder;

import java.util.List;
import java.util.Map;

public class AwardRewardsStep implements YamlBuildStep {

    @Override
    public void build(Map<String, Object> data, SheetBuilder sheetBuilder) {
        List<Integer> rewards = (List<Integer>) data.get("rewards");
        for (Integer reward: rewards) {
            sheetBuilder.award(reward);
        }
    }
}
