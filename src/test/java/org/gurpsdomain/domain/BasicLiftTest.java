package org.gurpsdomain.domain;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BasicLiftTest {
    @Test
    public void shouldDetermineCorrectBasicLift() {
        Attributes attributes = new Attributes();
        double basicLift = attributes.basicLift();
        assertThat(basicLift, is(20.0));
    }

    @Test
    public void shouldDetermineCorrectBasicLiftAfterStrengthOneBonus() {
        Attributes attributes = new Attributes();
        attributes.addStrengthBonus(1);
        double basicLift = attributes.basicLift();
        assertThat(basicLift, is(24.0));
    }

    @Test
    public void shouldDetermineCorrectBasicLiftAfterStrengthTwoBonus() {
        Attributes attributes = new Attributes();
        attributes.addStrengthBonus(2);
        double basicLift = attributes.basicLift();
        assertThat(basicLift, is(29.0));
    }
}
