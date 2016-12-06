package org.gurpsdomain.domain;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DexterityTest {
    @Test
    public void shouldDetermineCorrectDexterity() {
        Attributes attributes = new Attributes();

        int dexterity = attributes.dexterity();

        assertThat(dexterity, is(10));
    }

    @Test
    public void shouldDetermineCorrectDexterityAfterDexterityBonus() {
        Attributes attributes = new Attributes();
        attributes.addDexterityBonus(1);

        int dexterity = attributes.dexterity();

        assertThat(dexterity, is(11));
    }

    @Test
    public void shouldDetermineCorrectDexterityAfterNegativeDexterityBonus() {
        Attributes attributes = new Attributes();
        attributes.addDexterityBonus(-1);

        int dexterity = attributes.dexterity();

        assertThat(dexterity, is(9));
    }

}
