package org.gurpsdomain.domain.description;

import org.gurpsdomain.domain.Repository;

public interface AdvantageDescriptionRepository extends Repository<AdvantageDescription> {
    boolean exists(String advantageName);
    AdvantageDescription getByName(String advantageName);
}
