package org.gurpsdomain.domain;

import org.gurpsdomain.domain.description.CostType;
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
    private static final String ANY_PAGE_REFERENCE = "TEST1";

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> data() {
        Collection<Object[]> data = new ArrayList<Object[]>();
        data.add(new Object[]{"no modifiers", Arrays.asList(new Modifier[]{}),100, 100});
        data.add(new Object[]{"single modifier (10 points)", Arrays.asList(new Modifier("10 point modifier", new Cost(10, CostType.points)) ),100 , 110});
        data.add(new Object[]{"single modifier (-5 points)", Arrays.asList(new Modifier("-5 point modifier", new Cost(-5, CostType.points)) ),100, 95});
        data.add(new Object[]{"two modifiers (7 points, -3 points)", Arrays.asList(new Modifier("7 point modifier", new Cost(7, CostType.points)),new Modifier("-3 point modifier", new Cost(-3, CostType.points))),100, 104});
        data.add(new Object[]{"single modifier (11 percent), base 100", Arrays.asList(new Modifier("11 percent modifier", new Cost(11, CostType.percentage)) ),100, 111});
        data.add(new Object[]{"single modifier (11 percent), base 50", Arrays.asList(new Modifier("11 percent modifier", new Cost(11, CostType.percentage)) ),50, 56}); //rounding of point costs is always up. See B101
        data.add(new Object[]{"single modifier (-11 percent), base 50", Arrays.asList(new Modifier("-11 percent modifier", new Cost(-11, CostType.percentage)) ),50, 45}); //rounding of point costs is always up. See B101
        data.add(new Object[]{"two modifier (11 percent, 33 percent), base 50", Arrays.asList(new Modifier("11 percent modifier", new Cost(11, CostType.percentage)),new Modifier("33 percent modifier", new Cost(33, CostType.percentage)) ),50, 72}); //rounding of point costs is always up. See B101
        data.add(new Object[]{"single modifier (95 percent), base 100", Arrays.asList(new Modifier("95 percent modifier", new Cost(95, CostType.percentage)) ),100, 195});//No upper bound for posititive percentages
        data.add(new Object[]{"single modifier (-95 percent), base 100", Arrays.asList(new Modifier("-95 percent modifier", new Cost(-95, CostType.percentage)) ),100, 20}); //Cost reduction by negative percentages is capped at -80%
        data.add(new Object[]{"mixed modifiers (12 percent, 10 points), base 100", Arrays.asList(new Modifier("12 percent modifier", new Cost(12, CostType.percentage)),new Modifier("10 point modifier", new Cost(10, CostType.points)) ),100, 124});
        data.add(new Object[]{"mixed modifiers (-40 percent,-10 percent, 5 points,  15 points), base 100", Arrays.asList(new Modifier("-40 percent modifier", new Cost(-40, CostType.percentage)),new Modifier("-10 percent modifier", new Cost(-10, CostType.percentage)),new Modifier("5 point modifier", new Cost(5, CostType.points)),new Modifier("15 point modifier", new Cost(15, CostType.points)) ),100, 60});
        return data;
    }

    private final Advantage advantage;
    private final int expectedCost;

    public AdvantageCostTest(String advantageName, List<Modifier> modifiers,int baseCost, int expectedCost) {
        this.advantage = new Advantage(advantageName, baseCost, ANY_PAGE_REFERENCE, modifiers);
        this.expectedCost = expectedCost;
    }

    @Test
    public void shouldDetermineCostOfAdvantage() {
        Points points = mock(Points.class);

        advantage.payCost(points);

        verify(points).addAdvantage(expectedCost);
    }
}
