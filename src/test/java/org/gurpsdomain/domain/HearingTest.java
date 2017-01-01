package org.gurpsdomain.domain;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class HearingTest {
    @Test
    public void shouldDetermineCorrectHearing() {
        Attributes attributes = new Attributes();

        int hearing = attributes.hearing();

        assertThat(hearing, is(10));
    }

    @Test
    public void shouldDetermineCorrectHearingAfterHearingBonus() {
        Attributes attributes = new Attributes();
        attributes.addHearingBonus(1);

        int hearing = attributes.hearing();

        assertThat(hearing, is(11));
    }

    @Test
    public void shouldDetermineCorrectHearingAfterIntelligenceBonus() {
        Attributes attributes = new Attributes();
        attributes.addIntelligenceBonus(2);

        int hearing = attributes.hearing();

        assertThat(hearing, is(12));
    }

    @Test
    public void shouldDetermineCorrectHearingAfterPerceptionBonus() {
        Attributes attributes = new Attributes();
        attributes.addPerceptionBonus(-1);

        int hearing = attributes.hearing();

        assertThat(hearing, is(9));
    }

}
