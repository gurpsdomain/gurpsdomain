package org.gurpsdomain.repositories;

import org.gurpsdomain.domain.Advantage;
import org.gurpsdomain.domain.AdvantageRepository;

import java.util.HashMap;
import java.util.Map;

public class InMemoryAdvantageRepository implements AdvantageRepository {
    private Map<String, Advantage> advantages = new HashMap<String, Advantage>();

    {
        advantages.put("Absolute Direction", new Advantage("Absolute Direction", 5, "B34"));
        advantages.put("Flexibility", new Advantage("Flexibility", 5, "B56"));
    }

    @Override
    public boolean exists(String advantageName) {
        return advantages.containsKey(advantageName);
    }

    @Override
    public Advantage getByName(String advantageName) {
        return advantages.get(advantageName);
    }
}
