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
import static org.gurpsdomain.domain.DamageResistance.*;
import static org.gurpsdomain.domain.DamageResistanceBonus.damageResistanceBonus;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class DamageResistanceBonusTest {
    @Parameterized.Parameters(name = "A DR bonus {1} for {0} should give {3}")
    public static Collection<Object[]> data() {
        Collection<Object[]> data = new ArrayList<>();
        data.add(new Object[]{EYE, "1", traverse(call("eye")), 1});
        data.add(new Object[]{EYE, "4", traverse(call("eye")), 4});
        return data;
    }

    private final DamageResistanceBonus damageResistanceBonus;
    private final Reflection expectedAttribute;
    private final Object expectedValue;
    private DamageResistances damageResistances;

    public DamageResistanceBonusTest(DamageResistance damageResistance, String bonus, Reflection expectedAttribute, Object expectedValue) {
        this.damageResistanceBonus = damageResistanceBonus(damageResistance, bonus);
        this.expectedAttribute = expectedAttribute;
        this.expectedValue = expectedValue;
    }

    @Before
    public void createAttributes() {
        damageResistances = new DamageResistances();
    }

    @Test
    public void shouldEffectDamageResistances() {
        damageResistanceBonus.applyTo(damageResistances);

        assertThat(expectedAttribute.from(damageResistances), is(expectedValue));
    }
}