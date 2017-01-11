package org.gurpsdomain.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;

import static org.gurpsdomain.domain.DifficultyLevel.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class SpellLevelTest {
    @Parameterized.Parameters(name = "cost {0} for difficulty very hard: '{1}' should give a spell level {2}")
    public static Collection<Object[]> data() {
        Collection<Object[]> data = new ArrayList<>();
        data.add(new Object[]{1, VERY_HARD, 7});
        data.add(new Object[]{1, HARD, 8});
        data.add(new Object[]{2, VERY_HARD, 8});
        data.add(new Object[]{2, HARD, 9});
        return data;
    }

    private Spell spell;
    private int expectedLevel;
    private Attributes attributes;

    public SpellLevelTest(int cost, DifficultyLevel difficultyLevel, int expectedLevel) {
        this.spell = new Spell("dummyName", cost, "dummyReference", difficultyLevel , "dummyColleges" , "dummyPowerSource", "dummySpellClasses", "dummyMaintenanceCost", "dummyCastingTime", "dummyDuration");
        this.expectedLevel = expectedLevel;
        this.attributes = new Attributes();
    }

    @Test
    public void shouldDetermineCorrectLevel() {
        assertThat(spell.level(attributes), is(expectedLevel));
    }
}
