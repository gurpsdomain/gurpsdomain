package org.gurpsdomain.domain;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TasteAndSmellTest {
    @Test
    public void shouldDetermineCorrectTasteAndSmell() {
        Attributes attributes = new Attributes();

        int tasteAndSmell = attributes.tasteAndSmell();

        assertThat(tasteAndSmell, is(10));
    }

    @Test
    public void shouldDetermineCorrectTasteAndSmellAfterTasteAndSmellBonus() {
        Attributes attributes = new Attributes();
        attributes.addTasteAndSmellBonus(1);

        int tasteAndSmell = attributes.tasteAndSmell();

        assertThat(tasteAndSmell, is(11));
    }

    @Test
    public void shouldDetermineCorrectTasteAndSmellAfterIntelligenceBonus() {
        Attributes attributes = new Attributes();
        attributes.addIntelligenceBonus(2);

        int tasteAndSmell = attributes.tasteAndSmell();

        assertThat(tasteAndSmell, is(12));
    }

    @Test
    public void shouldDetermineCorrectTasteAndSmellAfterPerceptionBonus() {
        Attributes attributes = new Attributes();
        attributes.addPerceptionBonus(-1);

        int tasteAndSmell = attributes.tasteAndSmell();

        assertThat(tasteAndSmell, is(9));
    }

}
