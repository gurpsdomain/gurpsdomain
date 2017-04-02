package org.gurpsdomain.adapters.input.yaml;

import org.gurpsdomain.adapters.input.SheetInput;
import org.gurpsdomain.adapters.input.yaml.domain.InputSheet;
import org.gurpsdomain.adapters.input.yaml.step.*;
import org.gurpsdomain.domain.Repository;
import org.gurpsdomain.domain.Sheet;
import org.gurpsdomain.domain.SheetBuilder;
import org.gurpsdomain.domain.description.AdvantageDescription;
import org.gurpsdomain.domain.description.EquipmentDescription;
import org.gurpsdomain.domain.description.SkillDescription;
import org.gurpsdomain.domain.description.SpellDescription;
import org.gurpsdomain.domain.repositories.InMemoryRepository;
import org.gurpsdomain.domain.repositories.xml.AdvantageDescriptions;
import org.gurpsdomain.domain.repositories.xml.EquipmentDescriptions;
import org.gurpsdomain.domain.repositories.xml.SkillDescriptions;
import org.gurpsdomain.domain.repositories.xml.SpellDescriptions;
import org.yaml.snakeyaml.Yaml;

import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.gurpsdomain.adapters.input.yaml.step.AddAdvantagesStep.addAdvantagesStep;
import static org.gurpsdomain.adapters.input.yaml.step.AddAdvantagesStep.addDisadvantagesStep;
import static org.gurpsdomain.domain.SheetBuilder.builder;

public class YamlSheetInput implements SheetInput {
    public static Builder sheetInputBuilder() {
        return new Builder();
    }

    private Reader reader;
    private SheetBuilder sheetBuilder;
    private Collection<YamlBuildStep> buildSteps = new ArrayList<>();

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
        addBuildStep(new AddNotesStep());
    }

    private void addBuildStep(YamlBuildStep buildStep) {
        buildSteps.add(buildStep);
    }

    @Override
    public Sheet produce() {
        if (sheetBuilder == null) {
            Yaml yaml = new Yaml(InputSheet.constructor());
            InputSheet data = (InputSheet) yaml.load(reader);
            sheetBuilder = builder();
            for (YamlBuildStep buildStep: buildSteps) {
                buildStep.build(data, sheetBuilder);
            }
        }
        return sheetBuilder.build();
    }

    public static class Builder {
        private final List<String> advantageLocations = new ArrayList<>();
        private final List<String> skillLocations = new ArrayList<>();
        private final List<String> spellLocations = new ArrayList<>();
        private final List<String> equipmentLocations = new ArrayList<>();

        public void advantagesFrom(String location) {
            advantageLocations.add(location);
        }

        public void skillsFrom(String location) {
            skillLocations.add(location);
        }

        public void spellsFrom(String location) {
            spellLocations.add(location);
        }

        public void equipmentFrom(String location) {
            equipmentLocations.add(location);
        }

        public YamlSheetInput fromYaml(Reader reader) {
            Repository<AdvantageDescription> advantageRepository = InMemoryRepository.loadedWith(AdvantageDescriptions.class, advantageLocations);
            Repository<SkillDescription> skillRepository = InMemoryRepository.loadedWith(SkillDescriptions.class, skillLocations);
            Repository<SpellDescription> spellRepository = InMemoryRepository.loadedWith(SpellDescriptions.class, spellLocations);
            Repository<EquipmentDescription> equipmentRepository = InMemoryRepository.loadedWith(EquipmentDescriptions.class, equipmentLocations);
            YamlSheetInput yamlSheetInput = new YamlSheetInput(reader);
            yamlSheetInput.addBuildStep(new AddSkillsStep(skillRepository));
            yamlSheetInput.addBuildStep(new AddSpellsStep(spellRepository));
            yamlSheetInput.addBuildStep(addAdvantagesStep(advantageRepository));
            yamlSheetInput.addBuildStep(addDisadvantagesStep(advantageRepository));
            yamlSheetInput.addBuildStep(new AddEquipmentsStep(equipmentRepository));
            return yamlSheetInput;
        }
    }
}
