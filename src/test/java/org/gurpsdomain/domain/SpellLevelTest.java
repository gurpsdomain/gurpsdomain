package org.gurpsdomain.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class SpellLevelTest {
    @Parameterized.Parameters(name = "cost {0} for difficulty very hard: '{1}' should give a spell level {2}")
    public static Collection<Object[]> data() {
        Collection<Object[]> data = new ArrayList<>();
        data.add(new Object[]{1, true, 7});
        data.add(new Object[]{1, false, 8});
        data.add(new Object[]{2, true, 8});
        data.add(new Object[]{2, false, 9});
        return data;
    }

    private Spell spell;
    private int expectedLevel;
    private Attributes attributes;

    public SpellLevelTest(int cost, boolean veryHard, int expectedLevel) {
        this.spell = new Spell("dummyName", cost, "dummyReference", veryHard);
        this.expectedLevel = expectedLevel;
        this.attributes = new Attributes();
    }

    @Test
    public void shouldDetermineCorrectLevel() {
        assertThat(spell.level(attributes), is(expectedLevel));
    }
}
