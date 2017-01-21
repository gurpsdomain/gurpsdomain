package org.gurpsdomain.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;

import static org.gurpsdomain.domain.Attribute.*;
import static org.gurpsdomain.domain.DifficultyLevel.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class SkillLevelTest {
    @Parameterized.Parameters(name = "cost {0} for difficulty {1} and controlling attribute level of {2} and a bonus of {3} should give a skill level {4}")
    public static Collection<Object[]> data() {
        Collection<Object[]> data = new ArrayList<>();
        data.add(new Object[]{ 1, EASY, INTELLIGENCE,"0" ,10 });
        data.add(new Object[]{ 2, EASY, INTELLIGENCE,"0" ,11 });
        data.add(new Object[]{ 3, EASY, INTELLIGENCE ,"0" ,11 });
        data.add(new Object[]{ 4, EASY, INTELLIGENCE ,"0" ,12 });
        data.add(new Object[]{ 1, EASY, DEXTERITY ,"0" ,10 });
        data.add(new Object[]{ 2, EASY, DEXTERITY ,"0" ,11 });
        data.add(new Object[]{ 3, EASY, DEXTERITY ,"0" ,11 });
        data.add(new Object[]{ 4, EASY, DEXTERITY ,"0" ,12 });
        data.add(new Object[]{ 1, AVERAGE, INTELLIGENCE ,"0" ,9 });
        data.add(new Object[]{ 2, AVERAGE, INTELLIGENCE ,"0" ,10 });
        data.add(new Object[]{ 3, AVERAGE, INTELLIGENCE ,"0" ,10 });
        data.add(new Object[]{ 4, AVERAGE, INTELLIGENCE ,"0" ,11 });
        data.add(new Object[]{ 1, HARD, DEXTERITY ,"0" ,8 });
        data.add(new Object[]{ 2, HARD, DEXTERITY ,"0" ,9 });
        data.add(new Object[]{ 3, VERY_HARD, DEXTERITY ,"0" ,8 });
        data.add(new Object[]{ 4, VERY_HARD, DEXTERITY ,"0" ,9 });
        data.add(new Object[]{ 1, EASY, INTELLIGENCE,"1" ,11 });
        data.add(new Object[]{ 1, EASY, DEXTERITY ,"-2" ,8 });
        data.add(new Object[]{ 1, AVERAGE, INTELLIGENCE ,"3" ,12 });
        data.add(new Object[]{ 1, HARD, DEXTERITY ,"-3" ,5 });
        data.add(new Object[]{ 3, VERY_HARD, DEXTERITY ,"4" ,12 });
        return data;
    }

    private Skill skill;
    private int expectedLevel;
    private Attributes attributes;

    public SkillLevelTest(int cost, DifficultyLevel difficultyLevel, Attribute controllingAttribute,String bonus, int expectedLevel) {
        this.skill = new Skill("dummyName",cost, controllingAttribute, difficultyLevel, "dummyReference");
        this.skill.addBonus(bonus);
        this.expectedLevel = expectedLevel;
        this.attributes = new Attributes();
    }

    @Test
    public void shouldDetermineCorrectLevel() {
        assertThat(skill.level(attributes), is(expectedLevel));
    }
}
