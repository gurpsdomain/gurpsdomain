package org.gurpsdomain.domain.description.predicate;

import documentation.Assign;
import documentation.Developer;
import org.gurpsdomain.domain.description.ModifierDescription;

public interface ModifierDescriptionPredicate {
    boolean isFulfilledBy(ModifierDescription modifier);
}
