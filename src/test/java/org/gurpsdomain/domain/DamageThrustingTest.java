package org.gurpsdomain.domain;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DamageThrustingTest {
    @Test
    public void shouldDetermineCorrectDamageThrusting() {
        Attributes attributes = new Attributes();

        Dice damageThrusting = attributes.damageThrusting();

        assertThat(damageThrusting.toString(), is("1d-2"));
    }

    @Test
    public void shouldDetermineCorrectDamageThrustingAsString() {
        Attributes attributes = new Attributes();

        String damageThrustingAsString = attributes.damageThrustingAsString();

        assertThat(damageThrustingAsString, is("1d-2"));
    }

    @Test
    public void shouldDetermineCorrectDamageThrustingAfterStrengthThreeBonus() {
        Attributes attributes = new Attributes();
        attributes.addStrengthBonus(3);
        Dice damageThrusting = attributes.damageThrusting();

        assertThat(damageThrusting.toString(), is("1d"));
    }

    @Test
    public void shouldDetermineCorrectDamageThrustingAfterDamageThrustingBonus() {
        Attributes attributes = new Attributes();
        attributes.addDamageThrustingBonus(new Dice(1,3));
        Dice damageThrusting = attributes.damageThrusting();

        assertThat(damageThrusting.toString(), is("2d+1"));
    }
}
