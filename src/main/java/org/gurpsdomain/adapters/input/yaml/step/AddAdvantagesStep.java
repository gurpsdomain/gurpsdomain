package org.gurpsdomain.adapters.input.yaml.step;

import org.gurpsdomain.adapters.input.yaml.domain.InputAdvantage;
import org.gurpsdomain.adapters.input.yaml.domain.InputModifier;
import org.gurpsdomain.adapters.input.yaml.domain.InputSheet;
import org.gurpsdomain.domain.Advantage;
import org.gurpsdomain.domain.Repository;
import org.gurpsdomain.domain.SheetBuilder;
import org.gurpsdomain.domain.description.AdvantageDescription;
import org.gurpsdomain.domain.description.predicate.ModifierDescriptionPredicate;
import org.gurpsdomain.domain.description.predicate.Name;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.gurpsdomain.domain.description.predicate.And.and;
import static org.gurpsdomain.domain.description.predicate.Note.note;

public class AddAdvantagesStep implements YamlBuildStep {
    enum Category {
        ADVANTAGES {
            @Override
            List<InputAdvantage> getInputAdvantagesFrom(InputSheet sheet) {
                return sheet.advantages();
            }
        },
        DISADVANTAGES {
            @Override
            List<InputAdvantage> getInputAdvantagesFrom(InputSheet sheet) {
                return sheet.disadvantages();
            }
        };

        abstract List<InputAdvantage> getInputAdvantagesFrom(InputSheet sheet);
    }

    public static AddAdvantagesStep addAdvantagesStep(Repository<AdvantageDescription> repository) {
        return new AddAdvantagesStep(repository, Category.ADVANTAGES);
    }

    public static AddAdvantagesStep addDisadvantagesStep(Repository<AdvantageDescription> repository) {
        return new AddAdvantagesStep(repository, Category.DISADVANTAGES);
    }

    private final Repository<AdvantageDescription> repository;
    private final Category category;

    private AddAdvantagesStep(Repository<AdvantageDescription> repository, Category category) {
        this.repository = repository;
        this.category = category;
    }

    @Override
    public void build(InputSheet data, SheetBuilder sheetBuilder) {
        List<InputAdvantage> inputAdvantages = category.getInputAdvantagesFrom(data);
        for (InputAdvantage inputAdvantage : inputAdvantages) {
            String advantageName = inputAdvantage.name;
            if (repository.exists(advantageName)) {
                AdvantageDescription advantageDescription = repository.getByName(advantageName);

                Integer levels = inputAdvantage.levels();

                List<ModifierDescriptionPredicate> modifierDescriptionPredicates =
                        inputAdvantage.modifiers()
                                .stream()
                                .map(m -> m.descriptionPredicate())
                                .collect(Collectors.toList());

                    Advantage advantage = advantageDescription.createAdvantage(modifierDescriptionPredicates, levels);
                    sheetBuilder.addAdvantage(advantage);
            }
            else {sheetBuilder.addMessageText("Advantage named '" + advantageName + "' not found.");}
        }
    }
}
