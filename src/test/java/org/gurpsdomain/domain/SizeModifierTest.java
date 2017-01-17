package org.gurpsdomain.domain;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SizeModifierTest {
    @Test
    public void shouldDetermineCorrectSizeModifier() {
        Attributes attributes = new Attributes();

        int sizeModifier = attributes.sizeModifier();

        assertThat(sizeModifier, is(0));
    }

    @Test
    public void shouldDetermineCorrectSizeModifierAfterSizeModifierBonus() {
        Attributes attributes = new Attributes();
        attributes.addSizeModifierBonus(1);

        int sizeModifier = attributes.sizeModifier();

        assertThat(sizeModifier, is(1));
    }

    @Test
    public void shouldDetermineCorrectSizeModifierAfterNegativeSizeModifierBonus() {
        Attributes attributes = new Attributes();
        attributes.addSizeModifierBonus(-1);

        int sizeModifier = attributes.sizeModifier();

        assertThat(sizeModifier, is(-1));
    }

}
