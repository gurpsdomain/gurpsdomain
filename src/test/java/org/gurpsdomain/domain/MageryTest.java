package org.gurpsdomain.domain;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MageryTest {
    @Test
    public void shouldDetermineCorrectMagery() {
        Attributes attributes = new Attributes();

        int magery = attributes.magery();

        assertThat(magery, is(0));
    }

    @Test
    public void shouldDetermineCorrectMageryAfterMageryBonus() {
        Attributes attributes = new Attributes();
        attributes.addMageryBonus(2);

        int magery = attributes.magery();

        assertThat(magery, is(2));
    }
}
