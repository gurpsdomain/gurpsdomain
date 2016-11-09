package org.gurpsdomain.adapters.input.yaml.step;

import org.gurpsdomain.domain.Advantage;
import org.gurpsdomain.domain.SheetBuilder;
import org.gurpsdomain.domain.description.AdvantageDescription;
import org.gurpsdomain.domain.description.AdvantageDescriptionRepository;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

                List<Map<String, String>> modifiers = (List <Map<String, String>>) (inputAdvantage.getOrDefault("modifiers", Collections.EMPTY_LIST));
                List<String> modifierNames = modifiers.stream().map(m -> m.get("name")).collect(Collectors.toList());

                Advantage advantage = advantageDescription.createAdvantage(modifierNames);
                sheetBuilder.addAdvantage(advantage);
            }
        }
    }
}
