package org.gurpsdomain.domain;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class IntelligenceTest {
    @Test
    public void shouldDetermineCorrectIntelligence() {
        Attributes attributes = new Attributes();

        int intelligence = attributes.intelligence();

        assertThat(intelligence, is(10));
    }

    @Test
    public void shouldDetermineCorrectIntelligenceAfterIntelligenceBonus() {
        Attributes attributes = new Attributes();
        attributes.addIntelligenceBonus(1);

        int intelligence = attributes.intelligence();

        assertThat(intelligence, is(11));
    }

    @Test
    public void shouldDetermineCorrectIntelligenceAfterNegativeIntelligenceBonus() {
        Attributes attributes = new Attributes();
        attributes.addIntelligenceBonus(-1);

        int intelligence = attributes.intelligence();

        assertThat(intelligence, is(9));
    }

}
