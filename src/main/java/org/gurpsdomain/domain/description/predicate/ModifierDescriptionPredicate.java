package org.gurpsdomain.domain.description.predicate;

import documentation.Assign;
import documentation.Developer;
import org.gurpsdomain.domain.description.ModifierDescription;

@Assign(developer= Developer.PAUL, issues = {21, 7})
@Assign(developer= Developer.DAAN, issues = {21, 7})
public interface ModifierDescriptionPredicate {
    boolean isFulfilledBy(ModifierDescription modifier);
}
