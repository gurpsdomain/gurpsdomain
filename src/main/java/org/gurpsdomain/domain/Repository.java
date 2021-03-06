package org.gurpsdomain.domain;

public interface Repository<T> {
    boolean exists(String itemName);
    T getByName(String itemName);
    void register(String itemName, T item);
}
