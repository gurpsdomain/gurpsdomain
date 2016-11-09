package org.gurpsdomain.repositories;

import org.gurpsdomain.domain.description.AdvantageDescriptionRepository;
import org.gurpsdomain.domain.description.AdvantageDescription;
import org.gurpsdomain.repositories.xml.Advantages;

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

        for (AdvantageDescription advantageDescription: advantages) {
            advantageDescription.registerIn(repository);
        }

    }

    private Map<String, AdvantageDescription> advantageDescriptions = new HashMap<String, AdvantageDescription>();

    @Override
    public boolean exists(String advantageName) {
        return advantageDescriptions.containsKey(advantageName);
    }

    @Override
    public AdvantageDescription getByName(String advantageName) {
        if (!advantageDescriptions.containsKey(advantageName)) {
            throw new AdvantageNotFoundException(advantageName);
        }
        return advantageDescriptions.get(advantageName);
    }

    public void register(String advantageName, AdvantageDescription advantageDescription) {
        advantageDescriptions.put(advantageName, advantageDescription);
    }
}
