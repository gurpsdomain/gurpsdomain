package org.gurpsdomain.adapters.input.yaml;

import org.gurpsdomain.adapters.input.SheetInput;
import org.gurpsdomain.adapters.input.yaml.step.*;
import org.gurpsdomain.domain.Repository;
import org.gurpsdomain.domain.Sheet;
import org.gurpsdomain.domain.SheetBuilder;
import org.gurpsdomain.domain.description.AdvantageDescription;
import org.gurpsdomain.repositories.InMemoryRepository;
import org.gurpsdomain.repositories.xml.AdvantageDescriptions;
import org.yaml.snakeyaml.Yaml;

import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import static org.gurpsdomain.domain.SheetBuilder.builder;

public class YamlSheetInput implements SheetInput {
    public static YamlSheetInput fromYaml(Reader reader) {
        Repository<AdvantageDescription> repository = InMemoryRepository.loadedWith(AdvantageDescriptions.class, "src/main/resources/data/advantages.basic-set.xml");
        YamlSheetInput yamlSheetInput = new YamlSheetInput(reader);
        yamlSheetInput.addBuildStep(new AddAdvantagesStep(repository));
        return yamlSheetInput;
    }

    private Reader reader;
    private SheetBuilder sheetBuilder;
    private Collection<YamlBuildStep> buildSteps = new ArrayList<YamlBuildStep>();

    private YamlSheetInput(Reader reader) {
        this.reader = reader;
        populateBuildSteps();
    }

    private void populateBuildSteps() {
        addBuildStep(new IdentityStep());
        addBuildStep(new PlayerInformationStep());
        addBuildStep(new DescriptionStep());
        addBuildStep(new SetBasePointsStep());
        addBuildStep(new AwardRewardsStep());
        addBuildStep(new SetNoteStep());
    }

    private void addBuildStep(YamlBuildStep buildStep) {
        buildSteps.add(buildStep);
    }

    @Override
    public Sheet produce() {
        if (sheetBuilder == null) {
            Yaml yaml = new Yaml();
            Map<String, Object> data = (Map<String, Object>) yaml.load(reader);
            sheetBuilder = builder();
            for (YamlBuildStep buildStep: buildSteps) {
                buildStep.build(data, sheetBuilder);
            }
        }
        return sheetBuilder.build();
    }
}
