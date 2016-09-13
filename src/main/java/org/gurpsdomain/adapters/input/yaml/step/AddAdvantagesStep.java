package org.gurpsdomain.adapters.input.yaml.step;

import org.gurpsdomain.domain.Advantage;
import org.gurpsdomain.domain.AdvantageDescriptionRepository;
import org.gurpsdomain.domain.SheetBuilder;
import org.gurpsdomain.domain.description.AdvantageDescription;

import java.util.List;
import java.util.Map;

public class AddAdvantagesStep implements YamlBuildStep {
    private AdvantageDescriptionRepository repository;

    public AddAdvantagesStep(AdvantageDescriptionRepository repository) {
        this.repository = repository;
    }

    @Override
    public void build(Map<String, Object> data, SheetBuilder sheetBuilder) {
        List<Object> inputAdvantages = (List<Object>) data.get("advantages");
        for (Object inputAdvantage: inputAdvantages) {
            Map<String, Object> advantageData = (Map<String, Object>) inputAdvantage;
            String advantageName = (String) advantageData.get("name");
            if (repository.exists(advantageName)){
                AdvantageDescription advantageDescription = repository.getByName(advantageName);
				Advantage advantage = advantageDescription.createAdvantage();
                sheetBuilder.addAdvantage(advantage);
            }
        }
    }
}
