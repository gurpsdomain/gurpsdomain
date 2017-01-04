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
        data.add(new Object[]{Attribute.INTELLIGENCE, "1", Reflection.withReflectionChain(call("intelligence")), 11});
        data.add(new Object[]{Attribute.INTELLIGENCE, "2", Reflection.withReflectionChain(call("intelligence")), 12});
        data.add(new Object[]{Attribute.STRENGTH, "1", Reflection.withReflectionChain(call("strength")), 11});
        data.add(new Object[]{Attribute.STRENGTH, "2", Reflection.withReflectionChain(call("strength")), 12});
        data.add(new Object[]{Attribute.BASIC_SPEED, "0.25", Reflection.withReflectionChain(call("basicSpeed")), 5.25});
        data.add(new Object[]{Attribute.BASIC_MOVE, "-2", Reflection.withReflectionChain(call("basicMove")), 3});
        data.add(new Object[]{Attribute.DEXTERITY, "-1", Reflection.withReflectionChain(call("dexterity")), 9});
        data.add(new Object[]{Attribute.HEARING, "2", Reflection.withReflectionChain(call("hearing")), 12});
        data.add(new Object[]{Attribute.PERCEPTION, "1", Reflection.withReflectionChain(call("perception")), 11});
        data.add(new Object[]{Attribute.WILL, "1", Reflection.withReflectionChain(call("will")), 11});
        data.add(new Object[]{Attribute.FRIGHT_CHECK, "2", Reflection.withReflectionChain(call("frightCheck")), 12});
        return data;
    }

    private final AttributeBonus attributeBonus;
    private final Reflection expectedAttribute;
    private final Object expectedValue;
    private Attributes attributes;

    public AttributeBonusTest(Attribute attribute, String bonus, Reflection expectedAttribute, Object expectedValue) {
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