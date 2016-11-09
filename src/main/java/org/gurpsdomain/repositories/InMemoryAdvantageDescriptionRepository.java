package org.gurpsdomain.repositories;

import org.gurpsdomain.domain.AdvantageDescriptionRepository;
import org.gurpsdomain.domain.Modifier;
import org.gurpsdomain.domain.description.AdvantageDescription;
import org.gurpsdomain.repositories.xml.Advantage;
import org.gurpsdomain.repositories.xml.Advantages;
import org.yaml.snakeyaml.Yaml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryAdvantageDescriptionRepository implements AdvantageDescriptionRepository {
    public static InMemoryAdvantageDescriptionRepository loadedWithXML(String... locations) {
        InMemoryAdvantageDescriptionRepository repository = new InMemoryAdvantageDescriptionRepository();
        for (String location: locations) {
            loadWithXML(repository, location);
        }
        return repository;
    }

    private static void loadWithXML(InMemoryAdvantageDescriptionRepository repository, String location) {
        InputStream inputStream;
        try {
            inputStream = new FileInputStream(new File(location));
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException(e);
        }

        Advantages advantages;
        try {
            JAXBContext context = JAXBContext.newInstance(Advantages.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            advantages = (Advantages) unmarshaller.unmarshal(inputStream);
        } catch (JAXBException e) {
            throw new IllegalArgumentException(e);
        }

        AdvantageBuilderFromXmlAdvantage builder = new AdvantageBuilderFromXmlAdvantage();
        for (Advantage advantage: advantages) {
            String advantageName = advantage.getName();
            AdvantageDescription advantageDescription = builder.buildFrom(advantage);
            repository.register(advantageName, advantageDescription);
        }

    }

    public static InMemoryAdvantageDescriptionRepository loadedWithYaml(String... locations) {
        InMemoryAdvantageDescriptionRepository repository = new InMemoryAdvantageDescriptionRepository();
        for (String location: locations) {
            loadWithYaml(repository, location);
        }
        return repository;
    }

    private static void loadWithYaml(InMemoryAdvantageDescriptionRepository repository, String location) {
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
        if(advantageData.get("modifiers") != null) {
            for(Object modifier : ((List) advantageData.get("modifiers"))){
                advantage.modifiers.add(new Modifier(((Map<String, String>) modifier).get("name")));
            }
        }
        return advantage;
    }
}

class AdvantageBuilderFromXmlAdvantage {
    public AdvantageDescription buildFrom(Advantage advantageData) {
        String advantageName = (String) advantageData.getName();
        AdvantageDescription advantage = new AdvantageDescription(
                advantageName,
                (int) advantageData.getBasePoints(),
                (String) advantageData.getReference()

        );
        if(advantageData.getModifiers() != null) {
            for(org.gurpsdomain.repositories.xml.Modifier modifier : advantageData.getModifiers()){
                advantage.modifiers.add(new Modifier(modifier.getName()));
            }
        }
        return advantage;
    }
}