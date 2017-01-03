package org.gurpsdomain.domain;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DamageSwingingTest {
    @Test
    public void shouldDetermineCorrectDamageSwinging() {
        Attributes attributes = new Attributes();

        Dice damageSwinging = attributes.damageSwinging();

        assertThat(damageSwinging.toString(), is("1d"));
    }

    @Test
    public void shouldDetermineCorrectDamageSwingingAsString() {
        Attributes attributes = new Attributes();

        String damageSwingingAsString = attributes.damageSwingingAsString();

        assertThat(damageSwingingAsString, is("1d"));
    }

    @Test
    public void shouldDetermineCorrectDamageSwingingAfterStrengthThreeBonus() {
        Attributes attributes = new Attributes();
        attributes.addStrengthBonus(3);
        Dice damageSwinging = attributes.damageSwinging();

        assertThat(damageSwinging.toString(), is("2d-1"));
    }

    @Test
    public void shouldDetermineCorrectDamageSwingingAfterDamageSwingingBonus() {
        Attributes attributes = new Attributes();
        attributes.addDamageSwingingBonus(new Dice(1,3));
        Dice damageSwinging = attributes.damageSwinging();

        assertThat(damageSwinging.toString(), is("2d+3"));
    }
}
