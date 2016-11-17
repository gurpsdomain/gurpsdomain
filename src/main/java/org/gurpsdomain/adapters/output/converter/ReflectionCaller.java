package org.gurpsdomain.adapters.output.converter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionCaller {
    public static ReflectionCaller call(String... methods) {
        return new ReflectionCaller(methods);
    }

    private String[] methods;

    private ReflectionCaller(String... methods) {
        this.methods = methods;
    }

    public <T> T of(Object object) {
        Object current = object;
        for (String method: methods) {
            current = safeCallOf(method, current);
        }
        return (T) current;
    }

    private <T> T safeCallOf(String method, Object object) {
        try {
            return unsafeCallOf(method, object);
        } catch (NoSuchMethodException|InvocationTargetException|IllegalAccessException e) {
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
