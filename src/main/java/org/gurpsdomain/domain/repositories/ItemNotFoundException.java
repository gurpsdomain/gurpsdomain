package org.gurpsdomain.domain.repositories;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(String itemName) {
        super(String.format("item \"%s\" not found", itemName));
    }
}
