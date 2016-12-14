package org.gurpsdomain.adapters.output.converter;

import org.junit.Test;

import static org.gurpsdomain.adapters.output.converter.Reflection.call;
import static org.gurpsdomain.adapters.output.converter.Reflection.read;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import static org.gurpsdomain.adapters.output.converter.Reflection.withReflectionChain;

public class ReflectionTest {
    @Test
    public void shouldReadFieldsAndCallMethodsChainedFromObjects() {
        NestedReflection nestedReflection = new NestedReflection(new SingleReflection(37));

        Integer value = withReflectionChain(call("test"), read("value")).from(nestedReflection);

        assertThat(value, is(37));
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

    private SingleReflection test() {
        return singleReflection;
    }
}
