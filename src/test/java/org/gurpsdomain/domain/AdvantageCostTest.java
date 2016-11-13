package org.gurpsdomain.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(Parameterized.class)
public class AdvantageCostTest {
    private static final int BASE_COST = 100;
    private static final String ANY_PAGE_REFERENCE = "TEST1";

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> data() {
        Collection<Object[]> data = new ArrayList<Object[]>();
        data.add(new Object[]{"no modifiers", Arrays.asList(new Modifier[]{}), 100});
        return data;
    }

    private final Advantage advantage;
    private final int expectedCost;

    public AdvantageCostTest(String advantageName, List<Modifier> modifiers, int expectedCost) {
        this.advantage = new Advantage(advantageName, BASE_COST, ANY_PAGE_REFERENCE, modifiers);
        this.expectedCost = expectedCost;
    }

    @Test
    public void shouldDetermineCostOfAdvantage() {
        Points points = mock(Points.class);

        advantage.payCost(points);

        verify(points).addAdvantage(expectedCost);
    }
}
