package org.gurpsdomain.domain;

import org.gurpsdomain.adapters.output.converter.Reflection;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;

import static org.gurpsdomain.adapters.output.converter.Reflection.call;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class AttributeBonusTest {
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Collection<Object[]> data = new ArrayList<>();
        data.add(new Object[]{Attribute.INTELLIGENCE, 1, Reflection.withReflectionChain(call("intelligence")), 11});
        data.add(new Object[]{Attribute.INTELLIGENCE, 2, Reflection.withReflectionChain(call("intelligence")), 12});
        data.add(new Object[]{Attribute.STRENGTH, 1, Reflection.withReflectionChain(call("strength")), 11});
        data.add(new Object[]{Attribute.STRENGTH, 2, Reflection.withReflectionChain(call("strength")), 12});
        return data;
    }

    private final AttributeBonus attributeBonus;
    private final Reflection expectedAttribute;
    private final int expectedValue;
    private Attributes attributes;

    public AttributeBonusTest(Attribute attribute, int bonus, Reflection expectedAttribute, int expectedValue) {
        this.attributeBonus = new AttributeBonus(attribute, bonus);
        this.expectedAttribute = expectedAttribute;
        this.expectedValue = expectedValue;
    }

    @Before
    public void createAttributes() {
        attributes = new Attributes();
    }

    @Test
    public void shouldEffectAttributes() {
        attributeBonus.applyTo(attributes);

        assertThat(expectedAttribute.from(attributes), is(expectedValue));
    }
}