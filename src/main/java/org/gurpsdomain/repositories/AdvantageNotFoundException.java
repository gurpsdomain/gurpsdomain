package org.gurpsdomain.repositories;

public class AdvantageNotFoundException extends RuntimeException {
    public AdvantageNotFoundException(String advantageName) {
        super(String.format("advatage \"%s\" not found", advantageName));
    }
}
