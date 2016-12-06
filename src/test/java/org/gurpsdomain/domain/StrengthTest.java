package org.gurpsdomain.domain;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StrengthTest {
    @Test
    public void shouldDetermineCorrectStrength() {
        Attributes attributes = new Attributes();

        int strength = attributes.strength();

        assertThat(strength, is(10));
    }

    @Test
    public void shouldDetermineCorrectStrengthAfterStrengthBonus() {
        Attributes attributes = new Attributes();
        attributes.addStrengthBonus(1);

        int strength = attributes.strength();

        assertThat(strength, is(11));
    }

    @Test
    public void shouldDetermineCorrectStrengthAfterNegativeStrengthBonus() {
        Attributes attributes = new Attributes();
        attributes.addStrengthBonus(-1);

        int strength = attributes.strength();

        assertThat(strength, is(9));
    }

}
