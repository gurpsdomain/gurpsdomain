package org.gurpsdomain.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


@RunWith(Parameterized.class)
public class DiceAdditionTest {
    @Parameterized.Parameters(name = "(A dice {0} added to a dice {1} should give {2}")
    public static Collection<Object[]> data() {
        Collection<Object[]> data = new ArrayList<>();
        data.add(new Object[]{new Dice(1,1),new Dice(1,1), "2d+2"});
        data.add(new Object[]{new Dice(1,1),new Dice(1,-1),"2d"});
        data.add(new Object[]{new Dice(1,1),new Dice(1,0), "2d+1"});
        data.add(new Object[]{new Dice(1,1),new Dice(0,4), "1d+5"});
        return data;
    }

    private Dice dice;
    private String expectedString;

    public DiceAdditionTest(Dice firstDice, Dice secondDice, String expectedString) {
        this.dice = Dice.sumDice(firstDice,secondDice);
        this.expectedString = expectedString;
    }

    @Test
    public void shouldConstructCorrectString() {
        assertThat(dice.toString(), is(expectedString));

    }
}
