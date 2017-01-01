package org.gurpsdomain.domain.repositories;

import org.gurpsdomain.domain.description.Registerable;
import org.gurpsdomain.domain.Repository;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class InMemoryRepository<T> implements Repository<T> {
    public static <U extends Registerable<U>, V extends Iterable<U>> InMemoryRepository<U> loadedWith(Class<V> containerClass, String... locations) {
        InMemoryRepository<U> repository = new InMemoryRepository<>();
        for (String location: locations) {
            loadWith(containerClass, repository, location);
        }
        return repository;
    }

    private static <U extends Registerable<U>, V extends Iterable<U>> void loadWith(Class<V> containerClass, InMemoryRepository<U> repository, String location) {
        InputStream inputStream;
        try {
            inputStream = new FileInputStream(new File(location));
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException(e);
        }

        V items;
        try {
            JAXBContext context = JAXBContext.newInstance(containerClass);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            items = (V) unmarshaller.unmarshal(inputStream);
        } catch (JAXBException e) {
            throw new IllegalArgumentException(e);
        }

        for (U item: items) {
            item.registerIn(repository);
        }

    }

    private Map<String, T> items = new HashMap<>();

    @Override
    public boolean exists(String itemName) {
        return items.containsKey(itemName);
    }

    @Override
    public T getByName(String advantageName) {
        if (!items.containsKey(advantageName)) {
            throw new ItemNotFoundException(advantageName);
        }
        return items.get(advantageName);
    }

    public void register(String name, T item) {
        items.put(name, item);
    }
}
