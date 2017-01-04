package org.gurpsdomain.domain;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FrightCheckTest {
    @Test
    public void shouldDetermineCorrectFrightCheck() {
        Attributes attributes = new Attributes();

        int frightCheck = attributes.frightCheck();

        assertThat(frightCheck, is(10));
    }

    @Test
    public void shouldDetermineCorrectFrightCheckAfterWillBonus() {
        Attributes attributes = new Attributes();
        attributes.addWillBonus(1);

        int frightCheck = attributes.frightCheck();

        assertThat(frightCheck, is(11));
    }

    @Test
    public void shouldDetermineCorrectFrightCheckAfterIntelligenceBonus() {
        Attributes attributes = new Attributes();
        attributes.addIntelligenceBonus(1);

        int frightCheck = attributes.frightCheck();

        assertThat(frightCheck, is(11));
    }

    @Test
    public void shouldDetermineCorrectWillAfterNegativeFrightCheckBonus() {
        Attributes attributes = new Attributes();
        attributes.addFrightCheckBonus(-1);

        int frightCheck = attributes.frightCheck();

        assertThat(frightCheck, is(9));
    }

}
