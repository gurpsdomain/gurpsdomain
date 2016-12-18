package org.gurpsdomain.domain;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PerceptionTest {
    @Test
    public void shouldDetermineCorrectPerception() {
        Attributes attributes = new Attributes();

        int perception = attributes.perception();

        assertThat(perception, is(10));
    }

    @Test
    public void shouldDetermineCorrectPerceptionAfterPerceptionBonus() {
        Attributes attributes = new Attributes();
        attributes.addPerceptionBonus(1);

        int perception = attributes.perception();

        assertThat(perception, is(11));
    }

    @Test
    public void shouldDetermineCorrectPerceptionAfterNegativePerceptionBonus() {
        Attributes attributes = new Attributes();
        attributes.addPerceptionBonus(-1);

        int perception = attributes.perception();

        assertThat(perception, is(9));
    }

}
