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
        data.add(new Object[]{new AttributeBonus(Attribute.INTELLIGENCE, 1), Reflection.withReflectionChain(call("intelligence"))});
        return data;
    }

    private final AttributeBonus attributeBonus;
    private final Reflection attribute;
    private Attributes attributes;

    public AttributeBonusTest(AttributeBonus attributeBonus, Reflection attribute) {
        this.attributeBonus = attributeBonus;
        this.attribute = attribute;
    }

    @Before
    public void createAttributes() {
        attributes = new Attributes();
    }

    @Test
    public void shouldEffectAttributes() {
        attributeBonus.applyTo(attributes);

        assertThat(attribute.from(attributes), is(11));
    }
}