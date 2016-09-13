package org.gurpsdomain.repositories;

import org.gurpsdomain.domain.AdvantageDescriptionRepository;
import org.gurpsdomain.domain.description.AdvantageDescription;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryAdvantageDescriptionRepository implements AdvantageDescriptionRepository {
    public static InMemoryAdvantageDescriptionRepository loadedWith(String... locations) {
        InMemoryAdvantageDescriptionRepository repository = new InMemoryAdvantageDescriptionRepository();
        for (String location: locations) {
            loadWith(repository, location);
        }
        return repository;
    }

    private static void loadWith(InMemoryAdvantageDescriptionRepository repository, String location) {
        Reader reader;
        try {
            reader = new FileReader(new File(location));
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
        AdvantageBuilder builder = new AdvantageBuilder();
        Yaml yaml = new Yaml();
        Map<String, Object> data = (Map<String, Object>) yaml.load(reader);
        List<Map<String, Object>> advantages = (List<Map<String, Object>>) data.get("advantages");
        for (Map<String, Object> advantageData: advantages) {
            String advantageName = (String) advantageData.get("name");
            AdvantageDescription advantageDescription = builder.buildFrom(advantageData);
            repository.register(advantageName, advantageDescription);
        }
    }

    private Map<String, AdvantageDescription> advantages = new HashMap<String, AdvantageDescription>();

    @Override
    public boolean exists(String advantageName) {
        return advantages.containsKey(advantageName);
    }

    @Override
    public AdvantageDescription getByName(String advantageName) {
        if (!advantages.containsKey(advantageName)) {
            throw new AdvantageNotFoundException(advantageName);
        }
        return advantages.get(advantageName);
    }

    public void register(String advantageName, AdvantageDescription advantageDescription) {
        advantages.put(advantageName, advantageDescription);
    }
}

class AdvantageBuilder {
    public AdvantageDescription buildFrom(Map<String, Object> advantageData) {
        String advantageName = (String) advantageData.get("name");
        AdvantageDescription advantage = new AdvantageDescription(
            advantageName,
            (int) advantageData.get("basePoints"),
            (String) advantageData.get("reference")
        );
        return advantage;
    }
}