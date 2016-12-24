package org.gurpsdomain.domain.description.predicate;

import org.gurpsdomain.domain.description.CostDescription;
import org.gurpsdomain.domain.description.CostType;
import org.gurpsdomain.domain.description.ModifierDescription;
import org.junit.Test;

import static org.gurpsdomain.domain.description.predicate.And.and;
import static org.gurpsdomain.domain.description.predicate.Name.name;
import static org.gurpsdomain.domain.description.predicate.Not.not;
import static org.gurpsdomain.domain.description.predicate.Note.note;
import static org.gurpsdomain.domain.description.predicate.Or.or;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ModifierDescriptionPredicateTest {
    private static final CostDescription ANY_COST = new CostDescription(1, CostType.points);
    private static final String ANY_PAGE_REFERENCE = "B1";
    private static final String ANY_NAME = "dummyName";
    private static final String ANY_NOTE = "dummyNote";

    @Test
    public void nameShouldBeFulfilledByExactName() {
        ModifierDescriptionPredicate predicate = name("A");

        assertTrue(predicate.isFulfilledBy(aModifierWithName("A")));
        assertFalse(predicate.isFulfilledBy(aModifierWithName("a")));
        assertFalse(predicate.isFulfilledBy(aModifierWithName("B")));
        assertFalse(predicate.isFulfilledBy(aModifierWithName("AB")));
    }

    @Test
    public void noteShouldBeFulfilledByExactNote() {
        ModifierDescriptionPredicate predicate = note("A");

        assertTrue(predicate.isFulfilledBy(aModifierWithNote("A")));
        assertFalse(predicate.isFulfilledBy(aModifierWithNote("a")));
        assertFalse(predicate.isFulfilledBy(aModifierWithNote("B")));
        assertFalse(predicate.isFulfilledBy(aModifierWithNote("AB")));
    }

    @Test
    public void andShouldOperateAndOnPredicates() {
        ModifierDescriptionPredicate truePredicate = and(name("A"), Always.True);
        ModifierDescriptionPredicate falsePredicate = and(name("A"), Always.False);

        assertTrue(truePredicate.isFulfilledBy(aModifierWithName("A")));
        assertFalse(truePredicate.isFulfilledBy(aModifierWithName("B")));
        assertFalse(falsePredicate.isFulfilledBy(aModifierWithName("A")));
        assertFalse(falsePredicate.isFulfilledBy(aModifierWithName("B")));
    }

    @Test
    public void orShouldOperateOrOnPredicates() {
        ModifierDescriptionPredicate predicate = or(name("A"), name("B"));

        assertTrue(predicate.isFulfilledBy(aModifierWithName("A")));
        assertTrue(predicate.isFulfilledBy(aModifierWithName("B")));
        assertFalse(predicate.isFulfilledBy(aModifierWithName("C")));
    }

    @Test
    public void notShouldNegatePredicates() {
        ModifierDescriptionPredicate predicate = not(name("A"));

        assertFalse(predicate.isFulfilledBy(aModifierWithName("A")));
        assertTrue(predicate.isFulfilledBy(aModifierWithName("B")));
        assertTrue(predicate.isFulfilledBy(aModifierWithName("C")));
    }

    @Test
    public void predicatesCanBeMixed() {
        ModifierDescriptionPredicate predicate = and(not(name("A")),not(name("B")));

        assertFalse(predicate.isFulfilledBy(aModifierWithName("A")));
        assertFalse(predicate.isFulfilledBy(aModifierWithName("B")));
        assertTrue(predicate.isFulfilledBy(aModifierWithName("C")));
    }

    @Test
    public void validateByNameOrNote() {
        ModifierDescriptionPredicate predicate = or(name("A"), note("B"));

        assertTrue(predicate.isFulfilledBy(aModifierWithNameAndNote("A","B")));
        assertFalse(predicate.isFulfilledBy(aModifierWithNameAndNote("B","A")));
    }

    private ModifierDescription aModifierWithName(String aName) {
        return new ModifierDescription(aName, ANY_COST, ANY_PAGE_REFERENCE, ANY_NOTE);
    }

    private ModifierDescription aModifierWithNote(String aNote) {
        return new ModifierDescription(ANY_NAME, ANY_COST, ANY_PAGE_REFERENCE, aNote);
    }

    private ModifierDescription aModifierWithNameAndNote(String aName, String aNote) {
        return new ModifierDescription(aName, ANY_COST, ANY_PAGE_REFERENCE, aNote);
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