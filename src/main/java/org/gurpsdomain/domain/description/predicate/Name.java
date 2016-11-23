package org.gurpsdomain.domain.description.predicate;

import org.gurpsdomain.domain.description.ModifierDescription;

public class Name implements ModifierDescriptionPredicate {
    private final String wantedName;

    public Name(String wantedName) {
        this.wantedName = wantedName;
    }

    @Override
    public boolean fullfilledBy(ModifierDescription modifier) {
        return modifier.matchesName(wantedName);
    }
}
