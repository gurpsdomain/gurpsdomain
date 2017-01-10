package org.gurpsdomain.adapters.output.converter;

import org.junit.Test;

import static org.gurpsdomain.adapters.output.converter.Reflection.call;
import static org.gurpsdomain.adapters.output.converter.Reflection.traverse;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ReflectionCallTest {
    private static final Integer ANY_INTEGER = 37;

    @Test
    public void shouldCallMethodOneLevelDeep() {
        SingleLevel level = new SingleLevel(ANY_INTEGER);

        Integer value = traverse(call("testMethod")).from(level);

        assertThat(value, is(ANY_INTEGER));
    }

    @Test
    public void shouldCallMethodTwoLevelsDeep() {
        NestedLevel level = new NestedLevel(new SingleLevel(ANY_INTEGER));

        Integer value = traverse(call("getLevel"),call("testMethod")).from(level);

        assertThat(value, is(ANY_INTEGER));
    }
}

class SingleLevel {
    private final int n;
    public SingleLevel(int n) {
        this.n = n;
    }

    private int testMethod() {
        return n;
    }
}

class NestedLevel {
    private final SingleLevel level;
    public NestedLevel(SingleLevel level) {
        this.level = level;
    }

    private SingleLevel getLevel() {
        return level;
    }
}
