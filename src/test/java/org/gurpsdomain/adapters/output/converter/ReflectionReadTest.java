package org.gurpsdomain.adapters.output.converter;

import org.junit.Test;

import static org.gurpsdomain.adapters.output.converter.Reflection.withReflectionChain;
import static org.gurpsdomain.adapters.output.converter.Reflection.read;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ReflectionReadTest {
    private static final int ANY_INT_VALUE = 37;

    @Test
    public void shouldReadPropertyOneLevelDeep() {
        SingleValue singleValue = new SingleValue(ANY_INT_VALUE);

        Integer value = withReflectionChain(read("value")).from(singleValue);

        assertThat(value, is(ANY_INT_VALUE));
    }

    @Test
    public void shouldReadPropertyTwoLevelsDeep() {
        NestedValue nestedValue = new NestedValue(new SingleValue(ANY_INT_VALUE));

        Integer value = withReflectionChain(read("singleValue"), read("value")).from(nestedValue);

        assertThat(value, is(ANY_INT_VALUE));
    }
}

class SingleValue {
    private int value;

    public SingleValue(int value) {
        this.value = value;
    }
}

class NestedValue {
    private SingleValue singleValue;

    public NestedValue(SingleValue singleValue) {
        this.singleValue = singleValue;
    }
}
