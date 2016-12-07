package org.gurpsdomain.domain.description.predicate;

import org.gurpsdomain.domain.description.ModifierDescription;

public class Name implements ModifierDescriptionPredicate {
    public static Name name(String wantedName) {
        return new Name(wantedName);
    }
    private final String wantedName;

    private Name(String wantedName) {
        this.wantedName = wantedName;
    }

    @Override
    public boolean isFulfilledBy(ModifierDescription modifier) {
        return modifier.matchesName(wantedName);
    }
}
