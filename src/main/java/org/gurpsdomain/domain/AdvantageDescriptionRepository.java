package org.gurpsdomain.domain;

import org.gurpsdomain.domain.description.AdvantageDescription;

public interface AdvantageDescriptionRepository {
    boolean exists(String advantageName);
    AdvantageDescription getByName(String advantageName);
}
