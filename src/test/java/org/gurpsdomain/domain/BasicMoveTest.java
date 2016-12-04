package org.gurpsdomain.domain;

import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BasicMoveTest {
    @Test
    public void shouldDetermineCorrectBasicMove() {
        Attributes attributes = new Attributes(10);

        int basicMove = attributes.basicMove();

        assertThat(basicMove, is(5));
    }

    @Test
    public void shouldDetermineCorrectBasicMoveAfterBasicMoveBonus() {
        Attributes attributes = new Attributes(10);
        attributes.applyBasicMoveBonus(1);

        int basicMove = attributes.basicMove();

        assertThat(basicMove, is(6));
    }

    @Test
    public void shouldDetermineCorrectBasicMoveAfterBasicSpeedBonus() {
        Attributes attributes = new Attributes(10);
        attributes.applyBasicSpeedBonus(1);

        int basicMove = attributes.basicMove();

        assertThat(basicMove, is(6));
    }
}
