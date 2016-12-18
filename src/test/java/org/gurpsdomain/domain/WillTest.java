package org.gurpsdomain.domain;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class WillTest {
    @Test
    public void shouldDetermineCorrectWill() {
        Attributes attributes = new Attributes();

        int will = attributes.will();

        assertThat(will, is(10));
    }

    @Test
    public void shouldDetermineCorrectWillAfterWillBonus() {
        Attributes attributes = new Attributes();
        attributes.addWillBonus(1);

        int will = attributes.will();

        assertThat(will, is(11));
    }

    @Test
    public void shouldDetermineCorrectWillAfterNegativeWillBonus() {
        Attributes attributes = new Attributes();
        attributes.addWillBonus(-1);

        int will = attributes.will();

        assertThat(will, is(9));
    }

}
