package org.gurpsdomain.domain;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TouchTest {
    @Test
    public void shouldDetermineCorrectTouch() {
        Attributes attributes = new Attributes();

        int touch = attributes.touch();

        assertThat(touch, is(10));
    }

    @Test
    public void shouldDetermineCorrectTouchAfterTouchBonus() {
        Attributes attributes = new Attributes();
        attributes.addTouchBonus(1);

        int touch = attributes.touch();

        assertThat(touch, is(11));
    }

    @Test
    public void shouldDetermineCorrectTouchAfterIntelligenceBonus() {
        Attributes attributes = new Attributes();
        attributes.addIntelligenceBonus(2);

        int touch = attributes.touch();

        assertThat(touch, is(12));
    }

    @Test
    public void shouldDetermineCorrectTouchAfterPerceptionBonus() {
        Attributes attributes = new Attributes();
        attributes.addPerceptionBonus(-1);

        int touch = attributes.touch();

        assertThat(touch, is(9));
    }

}
