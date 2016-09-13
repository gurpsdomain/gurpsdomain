package org.gurpsdomain.adapters.input.yaml;

import org.gurpsdomain.adapters.input.SheetInput;
import org.gurpsdomain.adapters.input.yaml.step.*;
import org.gurpsdomain.domain.AdvantageDescriptionRepository;
import org.gurpsdomain.domain.Sheet;
import org.gurpsdomain.domain.SheetBuilder;
import org.gurpsdomain.repositories.InMemoryAdvantageDescriptionRepository;
import org.yaml.snakeyaml.Yaml;

import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import static org.gurpsdomain.domain.SheetBuilder.builder;

public class YamlSheetInput implements SheetInput {
    public static YamlSheetInput fromYaml(Reader reader) {
        return new YamlSheetInput(InMemoryAdvantageDescriptionRepository.loadedWith("src/main/resources/data/basic-set.yml"), reader);
    }

    private AdvantageDescriptionRepository repository;
    private Reader reader;
    private SheetBuilder sheetBuilder;
    private Collection<YamlBuildStep> buildSteps = new ArrayList<YamlBuildStep>();

    private YamlSheetInput(AdvantageDescriptionRepository repository, Reader reader) {
        this.repository = repository;
        this.reader = reader;
        populateBuildSteps();
    }

    private void populateBuildSteps() {
		buildSteps.add(new SetPlayerStep());
		buildSteps.add(new SetCampaignStep());
		buildSteps.add(new SetCreatedOnStep());
        buildSteps.add(new SetNameStep());
        buildSteps.add(new SetTitleStep());
		buildSteps.add(new SetReligionStep());
		buildSteps.add(new SetRaceStep());
		buildSteps.add(new SetGenderStep());
		buildSteps.add(new SetAgeStep());
		buildSteps.add(new SetBirthdayStep());
		buildSteps.add(new SetHeightStep());
		buildSteps.add(new SetWeightStep());
		buildSteps.add(new SetSizeStep());
		buildSteps.add(new SetTechLevelStep());
		buildSteps.add(new SetHairStep());
		buildSteps.add(new SetEyesStep());
		buildSteps.add(new SetSkinStep());
		buildSteps.add(new SetHandStep());
        buildSteps.add(new SetBasePointsStep());
        buildSteps.add(new AwardRewardsStep());
        buildSteps.add(new AddAdvantagesStep(repository));
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


