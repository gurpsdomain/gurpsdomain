package org.gurpsdomain.adapters.input.yaml;

import org.gurpsdomain.adapters.input.SheetInput;
import org.gurpsdomain.domain.Advantage;
import org.gurpsdomain.domain.AdvantageRepository;
import org.gurpsdomain.domain.Sheet;
import org.gurpsdomain.domain.SheetBuilder;
import org.gurpsdomain.repositories.InMemoryAdvantageRepository;
import org.yaml.snakeyaml.Yaml;

import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.gurpsdomain.domain.SheetBuilder.builder;

public class YamlSheetInput implements SheetInput {
    public static YamlSheetInput fromYaml(Reader reader) {
        return new YamlSheetInput(InMemoryAdvantageRepository.loadedWith("src/main/resources/data/basic-set.yml"), reader);
    }

    private AdvantageRepository repository;
    private Reader reader;
    private SheetBuilder sheetBuilder;
    private Collection<YamlBuildStep> buildSteps = new ArrayList<YamlBuildStep>();

    private YamlSheetInput(AdvantageRepository repository, Reader reader) {
        this.repository = repository;
        this.reader = reader;
        populateBuildSteps();
    }

    private void populateBuildSteps() {
        buildSteps.add(new SetNameStep());
        buildSteps.add(new SetTitleStep());
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

interface YamlBuildStep {
    void build(Map<String, Object> data, SheetBuilder sheetBuilder);
}

class SetNameStep implements YamlBuildStep {

    @Override
    public void build(Map<String, Object> data, SheetBuilder sheetBuilder) {
        sheetBuilder.addMetaData("identity", "name", (String) data.get("name"));
    }
}

class SetTitleStep implements YamlBuildStep {

    @Override
    public void build(Map<String, Object> data, SheetBuilder sheetBuilder) {
        sheetBuilder.addMetaData("identity", "title", (String) data.getOrDefault("title", ""));
    }
}

class SetBasePointsStep implements YamlBuildStep {

    @Override
    public void build(Map<String, Object> data, SheetBuilder sheetBuilder) {
        sheetBuilder.award((Integer) data.get("basepoints"));
    }
}

class AwardRewardsStep implements YamlBuildStep {

    @Override
    public void build(Map<String, Object> data, SheetBuilder sheetBuilder) {
        List<Integer> rewards = (List<Integer>) data.get("rewards");
        for (Integer reward: rewards) {
            sheetBuilder.award(reward);
        }
    }
}

class AddAdvantagesStep implements YamlBuildStep {
    private AdvantageRepository repository;

    public AddAdvantagesStep(AdvantageRepository repository) {
        this.repository = repository;
    }

    @Override
    public void build(Map<String, Object> data, SheetBuilder sheetBuilder) {
        List<Object> inputAdvantages = (List<Object>) data.get("advantages");
        for (Object inputAdvantage: inputAdvantages) {
            Map<String, Object> advantageData = (Map<String, Object>) inputAdvantage;
            String advantageName = (String) advantageData.get("name");
            if (repository.exists(advantageName)){
                Advantage advantage = repository.getByName(advantageName);
                sheetBuilder.addAdvantage(advantage);
            }
        }
    }
}
