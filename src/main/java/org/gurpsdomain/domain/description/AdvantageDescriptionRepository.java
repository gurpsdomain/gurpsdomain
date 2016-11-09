package org.gurpsdomain.domain.description;

public interface AdvantageDescriptionRepository {
    boolean exists(String advantageName);
    AdvantageDescription getByName(String advantageName);
}
