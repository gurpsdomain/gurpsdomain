package org.gurpsdomain.domain;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class VisionTest {
    @Test
    public void shouldDetermineCorrectVision() {
        Attributes attributes = new Attributes();

        int vision = attributes.vision();

        assertThat(vision, is(10));
    }

    @Test
    public void shouldDetermineCorrectVisionAfterVisionBonus() {
        Attributes attributes = new Attributes();
        attributes.addVisionBonus(1);

        int vision = attributes.vision();

        assertThat(vision, is(11));
    }

    @Test
    public void shouldDetermineCorrectVisionAfterIntelligenceBonus() {
        Attributes attributes = new Attributes();
        attributes.addIntelligenceBonus(2);

        int vision = attributes.vision();

        assertThat(vision, is(12));
    }

    @Test
    public void shouldDetermineCorrectVisionAfterPerceptionBonus() {
        Attributes attributes = new Attributes();
        attributes.addPerceptionBonus(-1);

        int vision = attributes.vision();

        assertThat(vision, is(9));
    }

}
