package org.gurpsdomain.adapters.output.converter;

import org.junit.Test;

import java.lang.reflect.Field;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.gurpsdomain.adapters.output.converter.ReflectionReader.read;

public class ReflectionReaderTest {
    private static final int ANY_INT_VALUE = 37;

    @Test
    public void shouldReadPropertyOneLevelDeep() {
        SingleValue singleValue = new SingleValue(ANY_INT_VALUE);

        Integer value = read("value").from(singleValue);

        assertThat(value, is(ANY_INT_VALUE));
    }
}

class ReflectionReader {
    public static ReflectionReader read(String property) {
        return new ReflectionReader(property);
    }

    private String property;

    private ReflectionReader(String property) {
        this.property = property;
    }


    public <T> T from(Object object) {
        return safeFrom(object);
    }

    private <T> T safeFrom(Object object) {
        try {
            return unsafeFrom(object);
        } catch (IllegalAccessException|NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    private <T> T unsafeFrom(Object object) throws NoSuchFieldException, IllegalAccessException {
        Class<?> objectClass = object.getClass();
        Field field = objectClass.getDeclaredField(property);
        return (T) field.get(object);
    }
}

class SingleValue {
    public int value;

    public SingleValue(int value) {
        this.value = value;
    }
}
