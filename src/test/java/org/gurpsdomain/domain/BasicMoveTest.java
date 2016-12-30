package org.gurpsdomain.domain;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BasicMoveTest {
    @Test
    public void shouldDetermineCorrectBasicMove() {
        Attributes attributes = new Attributes();

        int basicMove = attributes.basicMove();

        assertThat(basicMove, is(5));
    }

    @Test
    public void shouldDetermineCorrectBasicMoveAfterDexterityOneBonus() {
        Attributes attributes = new Attributes();
        attributes.addDexterityBonus(1);
        int basicMove = attributes.basicMove();
        assertThat(basicMove, is(5));
    }

    @Test
    public void shouldDetermineCorrectBasicMoveAfterDexterityThreeBonus() {
        Attributes attributes = new Attributes();
        attributes.addDexterityBonus(3);
        int basicMove = attributes.basicMove();
        assertThat(basicMove, is(5));
    }

    @Test
    public void shouldDetermineCorrectBasicMoveAfterDexterityFourBonus() {
        Attributes attributes = new Attributes();
        attributes.addDexterityBonus(4);
        int basicMove = attributes.basicMove();
        assertThat(basicMove, is(6));
    }

    @Test
    public void shouldDetermineCorrectBasicMoveAfterBasicMoveBonus() {
        Attributes attributes = new Attributes();
        attributes.addBasicMoveBonus(1);

        int basicMove = attributes.basicMove();

        assertThat(basicMove, is(6));
    }

    @Test
    public void shouldDetermineCorrectBasicMoveAfterBasicSpeedBonus() {
        Attributes attributes = new Attributes();
        attributes.addBasicSpeedBonus(1.0);

        int basicMove = attributes.basicMove();

        assertThat(basicMove, is(5));
    }
}
