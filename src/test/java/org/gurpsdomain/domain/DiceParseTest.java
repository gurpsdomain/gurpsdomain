package org.gurpsdomain.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


@RunWith(Parameterized.class)
public class DiceParseTest {
    @Parameterized.Parameters(name = "A string {0} should be parsed correctly to a dice")
    public static Collection<Object[]> data() {
        Collection<Object[]> data = new ArrayList<>();
        data.add(new Object[]{"2d+3",new Dice(2,3)});
        data.add(new Object[]{"2d-1",new Dice(2,-1)});
        data.add(new Object[]{"2d",new Dice(2,0)});
        data.add(new Object[]{"0d+5",new Dice(0,5)});
        return data;
    }

    private Dice expectedDice;
    private String inputString;

    public DiceParseTest(String inputString, Dice expectedDice) {
        this.inputString = inputString;
        this.expectedDice = expectedDice;
    }

    @Test
    public void shouldParseCorrectDice() {
        assertThat(Dice.parseDice(inputString).toString(), is(expectedDice.toString()));

    }
}
