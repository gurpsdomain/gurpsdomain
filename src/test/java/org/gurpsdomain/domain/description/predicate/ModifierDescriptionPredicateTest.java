package org.gurpsdomain.domain.description.predicate;

import org.gurpsdomain.domain.description.CostDescription;
import org.gurpsdomain.domain.description.CostType;
import org.gurpsdomain.domain.description.ModifierDescription;
import org.junit.Test;

import static org.gurpsdomain.domain.description.predicate.And.and;
import static org.gurpsdomain.domain.description.predicate.Name.name;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ModifierDescriptionPredicateTest {
    private static final CostDescription ANY_COST = new CostDescription(1, CostType.points);
    private static final String ANY_PAGE_REFERENCE = "B1";

    @Test
    public void nameShouldBeFulfilledByExactName() {
        ModifierDescriptionPredicate predicate = name("A");

        assertTrue(predicate.isFulfilledBy(aModifierNamed("A")));
        assertFalse(predicate.isFulfilledBy(aModifierNamed("B")));
    }

    @Test
    public void andShouldCombinePredicates() {
        ModifierDescriptionPredicate predicate = and(name("A"), Always.True);

        assertTrue(predicate.isFulfilledBy(aModifierNamed("A")));
        assertFalse(predicate.isFulfilledBy(aModifierNamed("B")));
    }

    private ModifierDescription aModifierNamed(String aName) {
        return new ModifierDescription(aName, ANY_COST, ANY_PAGE_REFERENCE);
    }
}

enum Always implements ModifierDescriptionPredicate {
    True(true), False(false);

    private boolean answer;

    Always(boolean answer) {
        this.answer = answer;
    }

    @Override
    public boolean isFulfilledBy(ModifierDescription modifier) {
        return answer;
    }
}