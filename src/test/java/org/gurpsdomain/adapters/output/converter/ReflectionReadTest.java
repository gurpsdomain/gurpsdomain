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

    @Test
    public void shouldReadPropertyOfSuperClass() {
        SingleValue singleValue = new SingleValue(ANY_INT_VALUE);

        Integer value = withReflectionChain(read("parentValue")).from(singleValue);

        assertThat(value, is(2 * ANY_INT_VALUE));
    }
}

class Parent {
    private int parentValue;

    public Parent(int parentValue) {
        this.parentValue = parentValue;
    }
}

class SingleValue extends Parent {
    private int value;

    public SingleValue(int value) {
        super(2 * value);
        this.value = value;
    }
}

class NestedValue {
    private SingleValue singleValue;

    public NestedValue(SingleValue singleValue) {
        this.singleValue = singleValue;
    }
}
