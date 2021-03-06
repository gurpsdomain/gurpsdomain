package org.gurpsdomain.domain.description.predicate;

import org.gurpsdomain.domain.description.ModifierDescription;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class And implements ModifierDescriptionPredicate {
    public static And and(ModifierDescriptionPredicate... predicates) {
        return new And(Arrays.asList(predicates));
    }

    private final List<ModifierDescriptionPredicate> predicates = new ArrayList<>();

    private And(Collection<ModifierDescriptionPredicate> predicates) {
        this.predicates.addAll(predicates);
    }

    @Override
    public boolean isFulfilledBy(ModifierDescription modifier) {
        return predicates.stream().allMatch(p -> p.isFulfilledBy(modifier));
    }
}
