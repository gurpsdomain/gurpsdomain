package org.gurpsdomain.domain.description.predicate;

import documentation.Assign;
import documentation.Developer;
import org.gurpsdomain.domain.description.ModifierDescription;

@Assign(developer= Developer.PAUL, issue = 21)
@Assign(developer= Developer.PAUL, issue = 7)
public interface ModifierDescriptionPredicate {
    boolean isFullfilledBy(ModifierDescription modifier);
}
