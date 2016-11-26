package org.gurpsdomain.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


@RunWith(Parameterized.class)
public class DiceTest {
    @Parameterized.Parameters(name = "({0},{1}) plus ({2},{3}) should give {4}")
    public static Collection<Object[]> data() {
        Collection<Object[]> data = new ArrayList<>();
        data.add(new Object[]{1, 1, null, null, "1d+1"});
        data.add(new Object[]{1, -1, null, null, "1d-1"});
        data.add(new Object[]{1, 0, null, null, "1d"});
        data.add(new Object[]{0, 4, null, null, "0d+4"});
        data.add(new Object[]{1, 1, 2, 3, "3d+4"});
        data.add(new Object[]{1, -1, 4, 1, "5d"});
        data.add(new Object[]{1, 1, 2, null, "3d+1"});
        data.add(new Object[]{1, -1, -1, null, "0d-1"});
        data.add(new Object[]{1, 1, null, 2, "1d+3"});
        data.add(new Object[]{1, -1, null, 2, "1d+1"});
        return data;
    }

    private Dice dice;
    private String expectedString;

    public DiceTest(int number1, int bonus1, Integer number2, Integer bonus2, String expectedString) {
        Dice dice2;
        this.dice = new Dice(number1, bonus1);
        if (!(number2 == null || bonus2 == null)) {
            dice2 = new Dice(number2, bonus2);
            this.dice.addDice(dice2);
        } else {
            if (!(number2 == null)) {
                this.dice.addNumber(number2);
            } else {
                if (!(bonus2 == null)) {
                    this.dice.addBonus(bonus2);
                }
            }
        }
        this.expectedString = expectedString;
    }

    @Test
    public void shouldConstructCorrectString() {
        assertThat(dice.toString(), is(expectedString));

    }
}
