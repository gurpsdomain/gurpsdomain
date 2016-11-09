package org.gurpsdomain.repositories;

import org.gurpsdomain.domain.AdvantageDescriptionRepository;
import org.gurpsdomain.domain.Modifier;
import org.gurpsdomain.domain.description.AdvantageDescription;
import org.gurpsdomain.repositories.xml.Advantage;
import org.gurpsdomain.repositories.xml.Advantages;
import org.gurpsdomain.repositories.xml.ModifierDescription;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
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

class AdvantageBuilderFromXmlAdvantage {
    public AdvantageDescription buildFrom(Advantage advantageData) {
        String advantageName = advantageData.getName();
        AdvantageDescription advantage = new AdvantageDescription(
                advantageName,
                advantageData.getBasePoints(),
                advantageData.getPageReference()

        );
        if(advantageData.getModifiers() != null) {
            for(ModifierDescription modifier : advantageData.getModifiers()){
                advantage.modifiers.add(modifier);
            }
        }
        return advantage;
    }
}