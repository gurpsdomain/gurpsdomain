package org.gurpsdomain.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


@RunWith(Parameterized.class)
public class DiceSimpleAdditionTest {
    @Parameterized.Parameters(name = "(A number {0} added to a dice {1} should give {2}")
    public static Collection<Object[]> data() {
        Collection<Object[]> data = new ArrayList<>();
        data.add(new Object[]{0,new Dice(1,1), "1d+1"});
        data.add(new Object[]{-1,new Dice(1,-1),"0d-1"});
        data.add(new Object[]{2,new Dice(1,0), "3d"});
        data.add(new Object[]{1,new Dice(0,4), "1d+4"});
        return data;
    }

    private Dice dice;
    private String expectedString;

    public DiceSimpleAdditionTest(int number, Dice dice, String expectedString) {
        this.dice = dice.addNumber(number);
        this.expectedString = expectedString;
    }

    @Test
    public void shouldConstructCorrectString() {
        assertThat(dice.toString(), is(expectedString));

    }
}
