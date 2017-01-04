package org.gurpsdomain.adapters.input.yaml.step;

import org.gurpsdomain.domain.Advantage;
import org.gurpsdomain.domain.Repository;
import org.gurpsdomain.domain.SheetBuilder;
import org.gurpsdomain.domain.description.AdvantageDescription;
import org.gurpsdomain.domain.description.predicate.ModifierDescriptionPredicate;
import org.gurpsdomain.domain.description.predicate.Name;

import java.util.*;

import static org.gurpsdomain.domain.description.predicate.And.and;
import static org.gurpsdomain.domain.description.predicate.Note.note;

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
                    List<ModifierDescriptionPredicate> modifierDescriptionPredicates = new ArrayList<>();
                    for (Map<String, String> modifier: modifiers) {
                        String modifierName = modifier.get("name");
                        ModifierDescriptionPredicate predicate;
                        if (modifier.containsKey("variation") && !modifier.get("variation").equals("")) {
                            String modifierVariation = modifier.get("variation");
                            predicate = and(Name.name(modifierName), note(modifierVariation));
                        } else {
                            predicate = Name.name(modifierName);
                        }
                        modifierDescriptionPredicates.add(predicate);
                    }

                    Advantage advantage = advantageDescription.createAdvantage(modifierDescriptionPredicates, levels);
                    sheetBuilder.addAdvantage(advantage);
                }
            }
        }
    }
}
