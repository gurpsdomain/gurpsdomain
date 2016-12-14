package org.gurpsdomain.adapters.output.converter;

import org.junit.Test;

import static org.gurpsdomain.adapters.output.converter.Reflection.call;
import static org.gurpsdomain.adapters.output.converter.Reflection.read;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import static org.gurpsdomain.adapters.output.converter.Reflection.withReflectionChain;

public class ReflectionTest {
    @Test
    public void shouldCallMethodAndReadFieldChainedFromObjects() {
        NestedReflection nestedReflection = new NestedReflection(new SingleReflection(37));

        Integer value = withReflectionChain(call("testNoParameters"), read("value")).from(nestedReflection);

        assertThat(value, is(37));
    }

    @Test
    public void shouldCallMethodWithParameterAndReadFieldChainedFromObjects() {
        NestedReflection nestedReflection = new NestedReflection(new SingleReflection(0));
        int parameter = 55;

        Integer value = withReflectionChain(call("testWithSingleParameter", parameter), read("value")).from(nestedReflection);

        assertThat(value, is(55));
    }
}


class SingleReflection {
    private int value;

    public SingleReflection(int value) {
        this.value = value;
    }
}

class NestedReflection {
    private SingleReflection singleReflection;

    public NestedReflection(SingleReflection singleReflection) {
        this.singleReflection = singleReflection;
    }

    private SingleReflection testNoParameters() {
        return singleReflection;
    }

    private SingleReflection testWithSingleParameter(Integer value) {
        return new SingleReflection(value);
    }
}
