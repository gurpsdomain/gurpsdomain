package org.gurpsdomain.domain.description.predicate;

import org.gurpsdomain.domain.description.ModifierDescription;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Or implements ModifierDescriptionPredicate {
    public static Or or(ModifierDescriptionPredicate... predicates) {
        return new Or(Arrays.asList(predicates));
    }

    private final List<ModifierDescriptionPredicate> predicates = new ArrayList<>();

    private Or(Collection<ModifierDescriptionPredicate> predicates) {
        this.predicates.addAll(predicates);
    }

    @Override
    public boolean isFulfilledBy(ModifierDescription modifier) {
        return predicates.stream().anyMatch(p -> p.isFulfilledBy(modifier));
    }
}
