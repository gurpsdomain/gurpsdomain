package org.gurpsdomain.domain;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FatiguePointsTest {
    @Test
    public void shouldDetermineCorrectFatiguePoints() {
        Attributes attributes = new Attributes();

        int fatiguePoints = attributes.fatiguePoints();

        assertThat(fatiguePoints, is(10));
    }

    @Test
    public void shouldDetermineCorrectFatiguePointsAfterFatiguePointsBonus() {
        Attributes attributes = new Attributes();
        attributes.addFatiguePointsBonus(1);

        int fatiguePoints = attributes.fatiguePoints();

        assertThat(fatiguePoints, is(11));
    }

    @Test
    public void shouldDetermineCorrectFatiguePointsAfterNegativeFatiguePointsBonus() {
        Attributes attributes = new Attributes();
        attributes.addFatiguePointsBonus(-1);

        int fatiguePoints = attributes.fatiguePoints();

        assertThat(fatiguePoints, is(9));
    }

}
