package org.gurpsdomain.domain;

import org.gurpsdomain.adapters.output.converter.Reflection;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;

import static org.gurpsdomain.adapters.output.converter.Reflection.call;
import static org.gurpsdomain.adapters.output.converter.Reflection.traverse;
import static org.gurpsdomain.domain.Attribute.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.gurpsdomain.domain.AttributeBonus.attributeBonus;

@RunWith(Parameterized.class)
public class AttributeBonusTest {
    @Parameterized.Parameters(name = "A bonus {1} for {0} should give {3}")
    public static Collection<Object[]> data() {
        Collection<Object[]> data = new ArrayList<>();
        data.add(new Object[]{STRENGTH, "2", traverse(call("strength")), 12});
        data.add(new Object[]{DEXTERITY, "-1", traverse(call("dexterity")), 9});
        data.add(new Object[]{HEALTH, "1", traverse(call("health")), 11});
        data.add(new Object[]{INTELLIGENCE, "2", traverse(call("intelligence")), 12});
        data.add(new Object[]{SIZE_MODIFIER, "1", traverse(call("sizeModifier")), 1});
        data.add(new Object[]{WILL, "1", traverse(call("will")), 11});
        data.add(new Object[]{MAGERY, "1", traverse(call("magery")), 1});
        data.add(new Object[]{FRIGHT_CHECK, "2", traverse(call("frightCheck")), 12});
        data.add(new Object[]{PERCEPTION, "1", traverse(call("perception")), 11});
        data.add(new Object[]{VISION, "2", traverse(call("vision")), 12});
        data.add(new Object[]{HEARING, "2", traverse(call("hearing")), 12});
        data.add(new Object[]{TASTE_AND_SMELL, "2", traverse(call("tasteAndSmell")), 12});
        data.add(new Object[]{TOUCH, "2", traverse(call("touch")), 12});
        data.add(new Object[]{HIT_POINTS, "2", traverse(call("hitPoints")), 12});
        data.add(new Object[]{FATIGUE_POINTS, "2", traverse(call("fatiguePoints")), 12});
        data.add(new Object[]{BASIC_LIFT, "1.3 lbs.", traverse(call("basicLiftAsStringInImperialSystem")), "21.0 lbs."});
        data.add(new Object[]{BASIC_LIFT, "-10.3 lbs.", traverse(call("basicLiftAsStringInImperialSystem")), "9.7 lbs."});
        data.add(new Object[]{BASIC_SPEED, "0.25", traverse(call("basicSpeed")), 5.25});
        data.add(new Object[]{BASIC_MOVE, "-2", traverse(call("basicMove")), 3});
        data.add(new Object[]{DODGE, "2", traverse(call("dodge")), 10});
        data.add(new Object[]{DAMAGE_SWINGING, "5d+2", traverse(call("damageSwingingAsString")), "6d+2"});
        data.add(new Object[]{DAMAGE_THRUSTING, "5d+2", traverse(call("damageThrustingAsString")), "6d"});
        return data;
    }

    private final AttributeBonus attributeBonus;
    private final Reflection expectedAttribute;
    private final Object expectedValue;
    private Attributes attributes;

    public AttributeBonusTest(Attribute attribute, String bonus, Reflection expectedAttribute, Object expectedValue) {
        this.attributeBonus = attributeBonus(attribute, bonus);
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