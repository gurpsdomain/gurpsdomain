package org.gurpsdomain.domain;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BasicSpeedTest {
    @Test
    public void shouldDetermineCorrectBasicSpeed() {
        Attributes attributes = new Attributes();
        double basicSpeed = attributes.basicSpeed();
        assertThat(basicSpeed, is(5.0));
    }

    @Test
    public void shouldDetermineCorrectBasicSpeedAfterDexterityOneBonus() {
        Attributes attributes = new Attributes();
        attributes.addDexterityBonus(1);
        double basicSpeed = attributes.basicSpeed();
        assertThat(basicSpeed, is(5.25));
    }

    @Test
    public void shouldDetermineCorrectBasicSpeedAfterDexterityTwoBonus() {
        Attributes attributes = new Attributes();
        attributes.addDexterityBonus(2);
        double basicSpeed = attributes.basicSpeed();
        assertThat(basicSpeed, is(5.5));
    }

    @Test
    public void shouldDetermineCorrectBasicSpeedAfterDexterityFourBonus() {
        Attributes attributes = new Attributes();
        attributes.addDexterityBonus(4);
        double basicSpeed = attributes.basicSpeed();
        assertThat(basicSpeed, is(6.0));
    }

    @Test
    public void shouldDetermineCorrectBasicSpeedAfterBasicSpeedBonus() {
        Attributes attributes = new Attributes();
        attributes.addBasicSpeedBonus(0.25);
        double basicSpeed = attributes.basicSpeed();
        assertThat(basicSpeed, is(5.25));
    }

    @Test
    public void shouldDetermineCorrectBasicSpeedAfterBasicMoveBonus() {
        Attributes attributes = new Attributes();
        attributes.addBasicMoveBonus(1);
        double basicSpeed = attributes.basicSpeed();
        assertThat(basicSpeed, is(5.0));
    }
}
