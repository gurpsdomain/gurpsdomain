package org.gurpsdomain.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;

import static org.gurpsdomain.domain.Attribute.IQ;
import static org.gurpsdomain.domain.DifficultyLevel.EASY;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class SkillLevelTest {
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Collection<Object[]> data = new ArrayList<>();
        data.add(new Object[]{ new Skill("Airshipman", 1, IQ, EASY, "B185"), 0 });
        return data;
    }

    private Skill skill;
    private int expectedDelta;

    public SkillLevelTest(Skill skill, int expectedDelta) {
        this.skill = skill;
        this.expectedDelta = expectedDelta;
    }

    @Test
    public void shouldDetermineCorrectDelta() {
        assertThat(skill.delta(), is(expectedDelta));
    }
}
