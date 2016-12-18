package org.gurpsdomain.domain;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class HitPointsTest {
    @Test
    public void shouldDetermineCorrectHitPoints() {
        Attributes attributes = new Attributes();

        int hitPoints = attributes.hitPoints();

        assertThat(hitPoints, is(10));
    }

    @Test
    public void shouldDetermineCorrectHitPointsAfterHitPointsBonus() {
        Attributes attributes = new Attributes();
        attributes.addHitPointsBonus(1);

        int hitPoints = attributes.hitPoints();

        assertThat(hitPoints, is(11));
    }

    @Test
    public void shouldDetermineCorrectHitPointsAfterNegativeHitPointsBonus() {
        Attributes attributes = new Attributes();
        attributes.addHitPointsBonus(-1);

        int hitPoints = attributes.hitPoints();

        assertThat(hitPoints, is(9));
    }

}
