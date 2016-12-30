package org.gurpsdomain.adapters.input.yaml.step;

import org.gurpsdomain.domain.Advantage;
import org.gurpsdomain.domain.Repository;
import org.gurpsdomain.domain.SheetBuilder;
import org.gurpsdomain.domain.description.AdvantageDescription;

import java.util.*;
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

                    List<Map<String, String>> modifierIdentifiers = new ArrayList<>();
                    for (Map<String, String> modifier : modifiers) {
                        Map<String, String> identifiers = new HashMap<>();
                        identifiers.put("name", modifier.getOrDefault("name", ""));
                        identifiers.put("variation", modifier.getOrDefault("variation", ""));
                        modifierIdentifiers.add(identifiers);
                    }

                    Advantage advantage = advantageDescription.createAdvantage(modifierIdentifiers, levels);
                    sheetBuilder.addAdvantage(advantage);
                }
            }
        }
    }
}
