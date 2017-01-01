package org.gurpsdomain.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;

import static org.gurpsdomain.domain.Attribute.DEXTERITY;
import static org.gurpsdomain.domain.Attribute.INTELLIGENCE;
import static org.gurpsdomain.domain.DifficultyLevel.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class SkillLevelTest {
    @Parameterized.Parameters(name = "cost {0} for difficulty {1} and controlling attribute level of {2} should give a skill level {3}")
    public static Collection<Object[]> data() {
        Collection<Object[]> data = new ArrayList<>();
        data.add(new Object[]{ 1, EASY, INTELLIGENCE,10 });
        data.add(new Object[]{ 2, EASY, INTELLIGENCE,11 });
        data.add(new Object[]{ 3, EASY, INTELLIGENCE ,11 });
        data.add(new Object[]{ 4, EASY, INTELLIGENCE ,12 });
        data.add(new Object[]{ 1, EASY, DEXTERITY ,10 });
        data.add(new Object[]{ 2, EASY, DEXTERITY ,11 });
        data.add(new Object[]{ 3, EASY, DEXTERITY ,11 });
        data.add(new Object[]{ 4, EASY, DEXTERITY ,12 });
        data.add(new Object[]{ 1, AVERAGE, INTELLIGENCE ,9 });
        data.add(new Object[]{ 2, AVERAGE, INTELLIGENCE ,10 });
        data.add(new Object[]{ 3, AVERAGE, INTELLIGENCE ,10 });
        data.add(new Object[]{ 4, AVERAGE, INTELLIGENCE ,11 });
        data.add(new Object[]{ 1, HARD, DEXTERITY ,8 });
        data.add(new Object[]{ 2, HARD, DEXTERITY ,9 });
        data.add(new Object[]{ 3, VERY_HARD, DEXTERITY ,8 });
        data.add(new Object[]{ 4, VERY_HARD, DEXTERITY ,9 });
        return data;
    }

    private Skill skill;
    private int expectedLevel;
    private Attributes attributes;

    public SkillLevelTest(int cost, DifficultyLevel difficultyLevel, Attribute controllingAttribute, int expectedLevel) {
        this.skill = new Skill("dummyName",cost, controllingAttribute, difficultyLevel, "dummyReference");
        this.expectedLevel = expectedLevel;
        this.attributes = new Attributes();
    }

    @Test
    public void shouldDetermineCorrectLevel() {
        assertThat(skill.level(attributes), is(expectedLevel));
    }
}
