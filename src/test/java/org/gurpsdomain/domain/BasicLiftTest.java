package org.gurpsdomain.domain;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BasicLiftTest {
    @Test
    public void shouldDetermineCorrectBasicLift() {
        Attributes attributes = new Attributes();
        Weight basicLift = attributes.basicLift();
        assertThat(basicLift.poundsInImperialSystem(), is(20.0));
    }

    @Test
    public void shouldDetermineCorrectBasicLiftAsString() {
        Attributes attributes = new Attributes();
        Weight basicLift = attributes.basicLift();
        assertThat(basicLift.toStringImperialSystemNotation(), is("20.0 lbs."));
    }

    @Test
    public void shouldDetermineCorrectBasicLiftAfterStrengthOneBonus() {
        Attributes attributes = new Attributes();
        attributes.addStrengthBonus(1);
        Weight basicLift = attributes.basicLift();
        assertThat(basicLift.toStringImperialSystemNotation(), is("24.0 lbs."));
    }

    @Test
    public void shouldDetermineCorrectBasicLiftAfterStrengthTwoBonus() {
        Attributes attributes = new Attributes();
        attributes.addStrengthBonus(2);
        Weight basicLift = attributes.basicLift();
        assertThat(basicLift.toStringImperialSystemNotation(), is("29.0 lbs."));
    }

    @Test
    public void shouldDetermineCorrectBasicLiftAfterBasicLiftBonus() {
        Attributes attributes = new Attributes();
        Weight bonus = new Weight(5);
        attributes.addBasicLiftBonus(bonus);
        Weight basicLift = attributes.basicLift();
        assertThat(basicLift.toStringImperialSystemNotation(), is("25.0 lbs."));
    }
}
