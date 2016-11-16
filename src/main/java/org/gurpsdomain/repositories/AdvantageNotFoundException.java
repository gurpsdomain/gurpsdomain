package org.gurpsdomain.repositories;

public class AdvantageNotFoundException extends RuntimeException {
    public AdvantageNotFoundException(String advantageName) {
        super(String.format("advantage \"%s\" not found", advantageName));
    }

}
