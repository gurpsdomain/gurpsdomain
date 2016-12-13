package org.gurpsdomain.adapters.output.converter;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class Reflection {
    public static Reflection withReflection(ReflectionOption... options) {
        return new Reflection(Arrays.asList(options));
    }

    public static ReflectionOption call(String methodName) {
        return new ReflectionCall(methodName);
    }

    public static ReflectionOption read(String fieldName) {
        return new ReflectionRead(fieldName);
    }

    private final List<ReflectionOption> options;
    private Reflection(List<ReflectionOption> options) {
        this.options = options;
    }

    public <T> T from(Object object) {
        Object current = object;
        for (ReflectionOption option: options) {
            current = option.actOn(current);
        }
        return (T) current;
    }
}

interface ReflectionOption {
    Object actOn(Object object);
}

class ReflectionCall implements ReflectionOption {
    private final String methodName;

    public ReflectionCall(String methodName) {
        this.methodName = methodName;
    }

    @Override
    public Object actOn(Object object) {
        return safeCallOf(methodName, object);
    }

    private <T> T safeCallOf(String method, Object object) {
        try {
            return unsafeCallOf(method, object);
        } catch (NoSuchMethodException|InvocationTargetException |IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private <T> T unsafeCallOf(String methodName, Object object) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> objectClass = object.getClass();
        Method method = objectClass.getDeclaredMethod(methodName);
        method.setAccessible(true);
        return (T) method.invoke(object);
    }

}

class ReflectionRead implements ReflectionOption {

    private final String fieldName;

    public ReflectionRead(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public Object actOn(Object object) {
        return safeReadFrom(fieldName, object);
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