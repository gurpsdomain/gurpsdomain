package org.gurpsdomain.domain;

import org.gurpsdomain.adapters.output.converter.ReflectionReader;
import org.gurpsdomain.domain.description.CostType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;

import static org.gurpsdomain.adapters.output.converter.ReflectionReader.read;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import static org.gurpsdomain.domain.AdvantageCostTestCase.anAdvantage;

@RunWith(Parameterized.class)
public class AdvantageCostTest {
    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> data() {
        Collection<Object[]> data = new ArrayList<Object[]>();
        data.add(new Object[]{anAdvantage().withBaseCost(100).hasExpectedCost(100)});
        data.add(new Object[]{anAdvantage().withBaseCost(100).withPointModifier(10).hasExpectedCost(110)});
        data.add(new Object[]{anAdvantage().withBaseCost(100).withPointModifier(-5).hasExpectedCost(95)});
        data.add(new Object[]{anAdvantage().withBaseCost(100).withPointModifier(7).withPointModifier(-3).hasExpectedCost(104)});
        data.add(new Object[]{anAdvantage().withBaseCost(100).withPercentageModifier(11).hasExpectedCost(111)});
        data.add(new Object[]{anAdvantage().withBaseCost(50).withPercentageModifier(11).hasExpectedCost(56)});
        data.add(new Object[]{anAdvantage().withBaseCost(50).withPercentageModifier(-11).hasExpectedCost(45)});
        data.add(new Object[]{anAdvantage().withBaseCost(50).withPercentageModifier(11).withPercentageModifier(33).hasExpectedCost(72)});
        data.add(new Object[]{anAdvantage().withBaseCost(100).withPercentageModifier(95).hasExpectedCost(195)});
        data.add(new Object[]{anAdvantage().withBaseCost(100).withPercentageModifier(-95).hasExpectedCost(20)});
        data.add(new Object[]{anAdvantage().withBaseCost(100).withPercentageModifier(12).withPointModifier(10).hasExpectedCost(124)});
        data.add(new Object[]{anAdvantage().withBaseCost(100).withPercentageModifier(-40).withPercentageModifier(-10).withPointModifier(5).withPointModifier(15).hasExpectedCost(60)});
        data.add(new Object[]{anAdvantage().withBaseCost(100).withPointsPerLevel(5).withLevel(5).hasExpectedCost(125)});
        data.add(new Object[]{anAdvantage().withBaseCost(100).withPercentageModifier(-40).withPercentageModifier(-10).withPointModifier(5).withPointModifier(15).withPointsPerLevel(10).withLevel(2).hasExpectedCost(70)});
        return data;
    }

    private final Advantage advantage;
    private final int expectedCost;

    public AdvantageCostTest(AdvantageCostTestCase testCase) {
        this.advantage = testCase.create();
        this.expectedCost = testCase.expectedCost;
    }

    @Test
    public void shouldDetermineCostOfAdvantage() {
        Points points = mock(Points.class);

        advantage.payCost(points);

        verify(points).addAdvantage(expectedCost);
    }
}

class AdvantageCostTestCase {
    private static final String ANY_PAGE_REFERENCE = "TEST1";

    public static AdvantageCostTestCase anAdvantage() {
        return new AdvantageCostTestCase();
    }

    private int baseCost = 100;
    private int pointsPerLevel = 10;


    private List<Modifier> modifiers = new ArrayList<Modifier>();
    private List<AttributeBonus> attributeBonuses = new ArrayList<AttributeBonus>();
    private List<AdvantageLevel> levels = new ArrayList<AdvantageLevel>();
    public int expectedCost = 100;

    private AdvantageCostTestCase(){};

    public AdvantageCostTestCase withBaseCost(int baseCost) {
        this.baseCost = baseCost;
        return this;
    }

    public AdvantageCostTestCase withLevel(int amount) {
        for(int i = 0; i < amount; i++) {
            this.levels.add(new AdvantageLevel(new Cost(pointsPerLevel, CostType.points)));
        }
        return this;
    }

    public AdvantageCostTestCase withPointsPerLevel(int amount) {
        this.pointsPerLevel = amount;
        return this;
    }

    public AdvantageCostTestCase withPointModifier(int value) {
        this.modifiers.add(new Modifier(String.format("%d point modifier", value), new Cost(value, CostType.points), ANY_PAGE_REFERENCE));
        return this;
    }

    public AdvantageCostTestCase withPercentageModifier(int value) {
        this.modifiers.add(new Modifier(String.format("%d percent modifier", value), new Cost(value, CostType.percentage), ANY_PAGE_REFERENCE));
        return this;
    }

    public AdvantageCostTestCase hasExpectedCost(int expectedCost) {
        this.expectedCost = expectedCost;
        return this;
    }

    public Advantage create() {
        return new Advantage(this.toString(), baseCost, ANY_PAGE_REFERENCE, modifiers, attributeBonuses, levels);
    }

    @Override
    public String toString() {
        ReflectionReader readValue = read("cost", "value");
        ReflectionReader readCostType = read("cost", "type");
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("base cost %d", baseCost));
        builder.append(" ");
        builder.append(String.format("expected cost %d", expectedCost));
        builder.append(" ");
        StringJoiner joiner = new StringJoiner(", ", "(", ")");
        for (Modifier modifier: modifiers) {
            int value = readValue.from(modifier);
            CostType costType = readCostType.from(modifier);
            joiner.add(String.format("%d%s", value, costType.equals(CostType.percentage)? "%": ""));
        }
        builder.append(joiner);

        return builder.toString();
    }
}