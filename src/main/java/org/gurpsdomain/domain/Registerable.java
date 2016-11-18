package org.gurpsdomain.domain;

public interface Registerable<T> {
    void registerIn(Repository<T> repository);
}
