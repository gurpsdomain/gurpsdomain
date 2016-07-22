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

    @Test
    public void shouldReadPropertyTwoLevelsDeep() {
        NestedValue nestedValue = new NestedValue(new SingleValue(ANY_INT_VALUE));

        Integer value = read("singleValue", "value").from(nestedValue);

        assertThat(value, is(ANY_INT_VALUE));
    }
}

class ReflectionReader {
    public static ReflectionReader read(String... properties) {
        return new ReflectionReader(properties);
    }

    private String[] properties;

    private ReflectionReader(String... properties) {
        this.properties = properties;
    }


    public <T> T from(Object object) {
        Object current = object;
        for (String property: properties) {
            current = safeReadFrom(property, current);
        }
        return (T) current;
    }

    private <T> T safeReadFrom(String property, Object object) {
        try {
            return unsafeReadFrom(property, object);
        } catch (IllegalAccessException|NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    private <T> T unsafeReadFrom(String property, Object object) throws NoSuchFieldException, IllegalAccessException {
        Class<?> objectClass = object.getClass();
        Field field = objectClass.getDeclaredField(property);
        field.setAccessible(true);
        return (T) field.get(object);
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
