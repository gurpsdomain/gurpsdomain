package org.gurpsdomain.repositories;

import org.gurpsdomain.domain.Advantage;
import org.gurpsdomain.domain.AdvantageRepository;

import java.util.HashMap;
import java.util.Map;

public class InMemoryAdvantageRepository implements AdvantageRepository {
    private Map<String, Advantage> advantages = new HashMap<String, Advantage>();

    {
        advantages.put("Enhanced Dexterity", new Advantage("Enhanced Dexterity"));
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
