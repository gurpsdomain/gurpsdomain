package org.gurpsdomain.domain;

public interface AdvantageRepository {
    boolean exists(String advantageName);
    Advantage getByName(String advantageName);
}
