package org.gurpsdomain.adapters.input.yaml.step;

import org.gurpsdomain.domain.Advantage;
import org.gurpsdomain.domain.description.AdvantageDescriptionRepository;
import org.gurpsdomain.domain.SheetBuilder;
import org.gurpsdomain.domain.description.AdvantageDescription;
import org.gurpsdomain.domain.description.ModifierDescription;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class AddAdvantagesStep implements YamlBuildStep {
    private AdvantageDescriptionRepository repository;

    public AddAdvantagesStep(AdvantageDescriptionRepository repository) {
        this.repository = repository;
    }

    @Override
    public void build(Map<String, Object> data, SheetBuilder sheetBuilder) {
        List<Map<String, Object>> inputAdvantages = (List<Map<String, Object>>) data.get("advantages");
        for (Map<String, Object> inputAdvantage: inputAdvantages) {
            String advantageName = (String) inputAdvantage.get("name");
            if (repository.exists(advantageName)){
                AdvantageDescription advantageDescription = repository.getByName(advantageName);

                Advantage advantage = advantageDescription.createAdvantage();
                for(Map<String, String> inputModifier: ((List <Map<String, String>>) (inputAdvantage.getOrDefault("modifiers", Collections.EMPTY_LIST)))) {
                    String modifierName = inputModifier.get("name");
                    if (advantageDescription.hasModifier(modifierName)) {
                        advantage.modifiers.add(advantageDescription.createModifier(modifierName));
                    }
                }
                sheetBuilder.addAdvantage(advantage);
            }
        }
    }
}
