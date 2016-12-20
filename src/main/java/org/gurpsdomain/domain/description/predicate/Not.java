package org.gurpsdomain.domain.description.predicate;

import org.gurpsdomain.domain.description.ModifierDescription;

public class Not implements ModifierDescriptionPredicate {
    public static Not not(ModifierDescriptionPredicate predicate) {
        return new Not(predicate);
    }

    private final ModifierDescriptionPredicate predicate;

    private Not(ModifierDescriptionPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public boolean isFulfilledBy(ModifierDescription modifier) {
        return ! predicate.isFulfilledBy(modifier);
    }

}
