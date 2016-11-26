package org.gurpsdomain.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

;

@RunWith(Parameterized.class)
public class DiceTest {
    @Parameterized.Parameters(name = "number {0} and bonus {1} should give {2}")
    public static Collection<Object[]> data() {
        Collection<Object[]> data = new ArrayList<>();
        data.add(new Object[]{ 1, 1, "1d+1" });
        data.add(new Object[]{ 1, -1, "1d-1" });
        data.add(new Object[]{ 1, 0, "1d" });
        data.add(new Object[]{ 0, 4, "0d+4" });
        return data;
    }

    private Dice dice;
    private String expectedString;

    public DiceTest(int number, int bonus, String expectedString) {
        this.dice = new Dice(number,bonus);
        this.expectedString = expectedString;
    }

    @Test
    public void shouldConstructCorrectString() {
        assertThat(dice.toString(), is(expectedString));
    }
}
