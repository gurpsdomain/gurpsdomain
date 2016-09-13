package org.gurpsdomain.repositories;

import org.gurpsdomain.domain.Advantage;
import org.gurpsdomain.domain.AdvantageRepository;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryAdvantageRepository implements AdvantageRepository {
    public static InMemoryAdvantageRepository loadedWith(String... locations) {
        InMemoryAdvantageRepository repository = new InMemoryAdvantageRepository();
        for (String location: locations) {
            loadWith(repository, location);
        }
        return repository;
    }

    private static void loadWith(InMemoryAdvantageRepository repository, String location) {
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
            Advantage advantage = builder.buildFrom(advantageData);
            repository.register(advantageName, advantage);
        }
    }

    private Map<String, Advantage> advantages = new HashMap<String, Advantage>();

    @Override
    public boolean exists(String advantageName) {
        return advantages.containsKey(advantageName);
    }

    @Override
    public Advantage getByName(String advantageName) {
        if (!advantages.containsKey(advantageName)) {
            throw new AdvantageNotFoundException(advantageName);
        }
        return advantages.get(advantageName);
    }

    public void register(String advantageName, Advantage advantage) {
        advantages.put(advantageName, advantage);
    }
}

class AdvantageBuilder {
    public Advantage buildFrom(Map<String, Object> advantageData) {
        String advantageName = (String) advantageData.get("name");
        Advantage advantage = new Advantage(
            advantageName,
            (int) advantageData.get("basePoints"),
            (String) advantageData.get("reference"),
            (List) advantageData.get("modifiers")
        );
        return advantage;
    }
}