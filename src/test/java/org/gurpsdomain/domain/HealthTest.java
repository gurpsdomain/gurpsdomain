package org.gurpsdomain.domain;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class HealthTest {
    @Test
    public void shouldDetermineCorrectHealth() {
        Attributes attributes = new Attributes();

        int health = attributes.health();

        assertThat(health, is(10));
    }

    @Test
    public void shouldDetermineCorrectHealthAfterHealthBonus() {
        Attributes attributes = new Attributes();
        attributes.addHealthBonus(1);

        int health = attributes.health();

        assertThat(health, is(11));
    }

    @Test
    public void shouldDetermineCorrectHealthAfterNegativeHealthBonus() {
        Attributes attributes = new Attributes();
        attributes.addHealthBonus(-1);

        int health = attributes.health();

        assertThat(health, is(9));
    }

}
