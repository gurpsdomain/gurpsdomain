package org.gurpsdomain.domain.description;

import org.gurpsdomain.domain.Repository;

public interface Registerable<T> {
    void registerIn(Repository<T> repository);
}
