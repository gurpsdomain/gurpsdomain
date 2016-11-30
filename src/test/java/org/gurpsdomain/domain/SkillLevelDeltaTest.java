package org.gurpsdomain.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;

import static org.gurpsdomain.domain.Attribute.INTELLIGENCE;;
import static org.gurpsdomain.domain.DifficultyLevel.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class SkillLevelDeltaTest {
    @Parameterized.Parameters(name = "cost {0} for difficulty {1} should add {2} levels")
    public static Collection<Object[]> data() {
        Collection<Object[]> data = new ArrayList<>();
        data.add(new Object[]{ 1, EASY, 0 });
        data.add(new Object[]{ 2, EASY, 1 });
        data.add(new Object[]{ 3, EASY, 1 });
        data.add(new Object[]{ 4, EASY, 2 });
        data.add(new Object[]{ 5, EASY, 2 });
        data.add(new Object[]{ 6, EASY, 2 });
        data.add(new Object[]{ 7, EASY, 2 });
        data.add(new Object[]{ 8, EASY, 3 });

        data.add(new Object[]{ 1, AVERAGE, 0-1 });
        data.add(new Object[]{ 2, AVERAGE, 1-1 });
        data.add(new Object[]{ 3, AVERAGE, 1-1 });
        data.add(new Object[]{ 4, AVERAGE, 2-1 });
        data.add(new Object[]{ 5, AVERAGE, 2-1 });
        data.add(new Object[]{ 6, AVERAGE, 2-1 });
        data.add(new Object[]{ 7, AVERAGE, 2-1 });
        data.add(new Object[]{ 8, AVERAGE, 3-1 });
        return data;
    }

    private Skill skill;
    private int expectedDelta;

    public SkillLevelDeltaTest(int cost, DifficultyLevel difficultyLevel, int expectedDelta) {
        this.skill = new Skill("Airshipman",cost, INTELLIGENCE, difficultyLevel, "B185");
        this.expectedDelta = expectedDelta;
    }

    @Test
    public void shouldDetermineCorrectDelta() {
        int result = 0;
        Method delta = null;
        try {
            delta = Skill.class.getDeclaredMethod("delta",null );
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        delta.setAccessible(true);

        try {
            result = (int) delta.invoke(skill, null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        assertThat(result, is(expectedDelta));
    }
}
