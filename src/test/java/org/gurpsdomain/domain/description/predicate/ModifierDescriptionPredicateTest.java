package org.gurpsdomain.domain.description.predicate;

import org.gurpsdomain.domain.description.CostDescription;
import org.gurpsdomain.domain.description.CostType;
import org.gurpsdomain.domain.description.ModifierDescription;
import org.junit.Test;

import static org.gurpsdomain.domain.description.predicate.And.and;
import static org.gurpsdomain.domain.description.predicate.ModifierDescriptionBuilder.aModifierDescription;
import static org.gurpsdomain.domain.description.predicate.Name.name;
import static org.gurpsdomain.domain.description.predicate.Not.not;
import static org.gurpsdomain.domain.description.predicate.Note.note;
import static org.gurpsdomain.domain.description.predicate.Or.or;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ModifierDescriptionPredicateTest {

    @Test
    public void nameShouldBeFulfilledByExactName() {
        ModifierDescriptionPredicate predicate = name("A");

        assertTrue(predicate.isFulfilledBy(aModifierDescription().withName("A").build()));
        assertFalse(predicate.isFulfilledBy(aModifierDescription().withName("a").build()));
        assertFalse(predicate.isFulfilledBy(aModifierDescription().withName("B").build()));
        assertFalse(predicate.isFulfilledBy(aModifierDescription().withName("AB").build()));
    }

    @Test
    public void noteShouldBeFulfilledByExactNote() {
        ModifierDescriptionPredicate predicate = note("A");

        assertTrue(predicate.isFulfilledBy(aModifierDescription().withNote("A").build()));
        assertFalse(predicate.isFulfilledBy(aModifierDescription().withNote("a").build()));
        assertFalse(predicate.isFulfilledBy(aModifierDescription().withNote("B").build()));
        assertFalse(predicate.isFulfilledBy(aModifierDescription().withNote("AB").build()));
    }

    @Test
    public void andShouldOperateAndOnPredicates() {
        ModifierDescriptionPredicate truePredicate = and(name("A"), Always.True);
        ModifierDescriptionPredicate falsePredicate = and(name("A"), Always.False);

        assertTrue(truePredicate.isFulfilledBy(aModifierDescription().withName("A").build()));
        assertFalse(truePredicate.isFulfilledBy(aModifierDescription().withName("B").build()));
        assertFalse(falsePredicate.isFulfilledBy(aModifierDescription().withName("A").build()));
        assertFalse(falsePredicate.isFulfilledBy(aModifierDescription().withName("B").build()));
    }

    @Test
    public void orShouldOperateOrOnPredicates() {
        ModifierDescriptionPredicate predicate = or(name("A"), name("B"));

        assertTrue(predicate.isFulfilledBy(aModifierDescription().withName("A").build()));
        assertTrue(predicate.isFulfilledBy(aModifierDescription().withName("B").build()));
        assertFalse(predicate.isFulfilledBy(aModifierDescription().withName("C").build()));
    }

    @Test
    public void notShouldNegatePredicates() {
        ModifierDescriptionPredicate predicate = not(name("A"));

        assertFalse(predicate.isFulfilledBy(aModifierDescription().withName("A").build()));
        assertTrue(predicate.isFulfilledBy(aModifierDescription().withName("B").build()));
        assertTrue(predicate.isFulfilledBy(aModifierDescription().withName("C").build()));
    }

    @Test
    public void predicatesCanBeMixed() {
        ModifierDescriptionPredicate predicate = and(not(name("A")), not(name("B")));

        assertFalse(predicate.isFulfilledBy(aModifierDescription().withName("A").build()));
        assertFalse(predicate.isFulfilledBy(aModifierDescription().withName("B").build()));
        assertTrue(predicate.isFulfilledBy(aModifierDescription().withName("C").build()));
    }

    @Test
    public void validateByNameOrNote() {
        ModifierDescriptionPredicate predicate = or(name("A"), note("B"));

        assertTrue(predicate.isFulfilledBy(aModifierDescription().withName("A").withNote("B").build()));
        assertTrue(predicate.isFulfilledBy(aModifierDescription().withName("A").withNote("B").build()));
        assertFalse(predicate.isFulfilledBy(aModifierDescription().withName("B").withNote("A").build()));
    }
}

class ModifierDescriptionBuilder {
    private String name = "aDummyName";
    private String note;
    private final CostDescription ANY_COST = new CostDescription(1, "points");
    private final String ANY_PAGE_REFERENCE = "B123";

    static ModifierDescriptionBuilder aModifierDescription(){return new ModifierDescriptionBuilder();}

    public ModifierDescriptionBuilder() {
    }

    public ModifierDescriptionBuilder withName(String name){
        this.name = name;
        return this;
    }

    public ModifierDescriptionBuilder withNote(String note){
        this.note = note;
        return this;
    }

    public ModifierDescription build() {
        return new ModifierDescription(name, ANY_COST, ANY_PAGE_REFERENCE, note);
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