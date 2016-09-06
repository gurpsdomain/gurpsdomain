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

class SetReligionStep implements YamlBuildStep {
	
	@Override
	public void build(Map<String, Object> data, SheetBuilder sheetBuilder) {
		sheetBuilder.addMetaData("identity", "religion", (String) data.getOrDefault("religion", ""));
	}
}

class SetRaceStep implements YamlBuildStep {
	
	@Override
	public void build(Map<String, Object> data, SheetBuilder sheetBuilder) {
		sheetBuilder.addMetaData("description", "race", (String) data.getOrDefault("race", ""));
	}
}

class SetGenderStep implements YamlBuildStep {
	
	@Override
	public void build(Map<String, Object> data, SheetBuilder sheetBuilder) {
		sheetBuilder.addMetaData("description", "gender", (String) data.getOrDefault("gender", ""));
	}
}

class SetAgeStep implements YamlBuildStep {
	
	@Override
	public void build(Map<String, Object> data, SheetBuilder sheetBuilder) {
		sheetBuilder.addMetaData("description", "age", data.getOrDefault("age", "").toString());
	}
}

class SetBirthdayStep implements YamlBuildStep {
	
	@Override
	public void build(Map<String, Object> data, SheetBuilder sheetBuilder) {
		sheetBuilder.addMetaData("description", "birthday", (String) data.getOrDefault("birthday", ""));
	}
}
	
class SetHeightStep implements YamlBuildStep {

		@Override
		public void build(Map<String, Object> data, SheetBuilder sheetBuilder) {
			sheetBuilder.addMetaData("description", "height", (String) data.getOrDefault("height", ""));
		}
}

class SetWeightStep implements YamlBuildStep {
	
	@Override
	public void build(Map<String, Object> data, SheetBuilder sheetBuilder) {
		sheetBuilder.addMetaData("description", "weight", (String) data.getOrDefault("weight", ""));
	}
}

class SetSizeStep implements YamlBuildStep {
	
	@Override
	public void build(Map<String, Object> data, SheetBuilder sheetBuilder) {
		sheetBuilder.addMetaData("description", "size", data.getOrDefault("size", "").toString());
	}
}

class SetTechLevelStep implements YamlBuildStep {
	
	@Override
	public void build(Map<String, Object> data, SheetBuilder sheetBuilder) {
		sheetBuilder.addMetaData("description", "TL", data.getOrDefault("TL", "").toString());
	}
}

class SetHairStep implements YamlBuildStep {
	
	@Override
	public void build(Map<String, Object> data, SheetBuilder sheetBuilder) {
		sheetBuilder.addMetaData("description", "hair", (String) data.getOrDefault("hair", ""));
	}
}

class SetEyesStep implements YamlBuildStep {
	
	@Override
	public void build(Map<String, Object> data, SheetBuilder sheetBuilder) {
		sheetBuilder.addMetaData("description", "eyes", (String) data.getOrDefault("eyes", ""));
	}
}

class SetSkinStep implements YamlBuildStep {
	
	@Override
	public void build(Map<String, Object> data, SheetBuilder sheetBuilder) {
		sheetBuilder.addMetaData("description", "skin", (String) data.getOrDefault("skin", ""));
	}
}

class SetHandStep implements YamlBuildStep {
	
	@Override
	public void build(Map<String, Object> data, SheetBuilder sheetBuilder) {
		sheetBuilder.addMetaData("description", "hand", (String) data.getOrDefault("hand", ""));
	}
}

class SetPlayerStep implements YamlBuildStep {
	
	@Override
	public void build(Map<String, Object> data, SheetBuilder sheetBuilder) {
		sheetBuilder.addMetaData("player information", "player", (String) data.getOrDefault("player", ""));
	}
}

class SetCampaignStep implements YamlBuildStep {
	
	@Override
	public void build(Map<String, Object> data, SheetBuilder sheetBuilder) {
		sheetBuilder.addMetaData("player information", "campaign", (String) data.getOrDefault("campaign", ""));
	}
}

class SetCreatedOnStep implements YamlBuildStep {
	
	@Override
	public void build(Map<String, Object> data, SheetBuilder sheetBuilder) {
		sheetBuilder.addMetaData("player information", "created on", (String) data.getOrDefault("created on", ""));
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
