package org.gurpsdomain.repositories;

import org.gurpsdomain.domain.Advantage;

public class AdvantageNotFoundException extends RuntimeException {
    public AdvantageNotFoundException(String advantageName) {
        super(String.format("advantage \"%s\" not found", advantageName));
    }

}
