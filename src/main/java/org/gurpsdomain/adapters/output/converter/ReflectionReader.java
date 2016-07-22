package org.gurpsdomain.adapters.output.converter;

import java.lang.reflect.Field;

public class ReflectionReader {
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
