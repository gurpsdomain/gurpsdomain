package org.gurpsdomain.adapters.input.yaml.step;

import org.gurpsdomain.domain.Advantage;
import org.gurpsdomain.domain.Repository;
import org.gurpsdomain.domain.SheetBuilder;
import org.gurpsdomain.domain.description.AdvantageDescription;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AddAdvantagesStep implements YamlBuildStep {
    private Repository<AdvantageDescription> repository;

    public AddAdvantagesStep(Repository<AdvantageDescription> repository) {
        this.repository = repository;
    }

    @Override
    public void build(Map<String, Object> data, SheetBuilder sheetBuilder) {
        List<Map<String, Object>> inputAdvantages = (List<Map<String, Object>>) data.get("advantages");
        if (!(inputAdvantages == null)) {
            for (Map<String, Object> inputAdvantage : inputAdvantages) {
                String advantageName = (String) inputAdvantage.get("name");
                if (repository.exists(advantageName)) {
                    AdvantageDescription advantageDescription = repository.getByName(advantageName);

                    Integer levels = (int) inputAdvantage.getOrDefault("levels", 0);

                    List<Map<String, String>> modifiers = (List<Map<String, String>>) (inputAdvantage.getOrDefault("modifiers", Collections.EMPTY_LIST));
                    List<Map<String, String>> attributeBonuses = (List<Map<String, String>>) (inputAdvantage.getOrDefault("attribute_bonus", Collections.EMPTY_LIST));
                    List<String> modifierNames = modifiers.stream().map(m -> m.get("name")).collect(Collectors.toList());
                    List<String> attributeBonusAttributes = attributeBonuses.stream().map(m -> m.get("attribute")).collect(Collectors.toList());

                    Advantage advantage = advantageDescription.createAdvantage(modifierNames,attributeBonusAttributes,levels);
                    sheetBuilder.addAdvantage(advantage);
                }
            }
        }
    }
}
