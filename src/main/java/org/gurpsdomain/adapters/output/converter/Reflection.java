package org.gurpsdomain.adapters.output.converter;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Reflection {
    public static Reflection withReflectionChain(ReflectionOption... options) {
        return new Reflection(Arrays.asList(options));
    }


    public static ReflectionOption call(String methodName, Object... arguments) {
        return new ReflectionCall(methodName, arguments);
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
        for (ReflectionOption option : options) {
            current = option.actOn(current);
        }
        return (T) current;
    }
}

class ReflectionCall implements ReflectionOption {
    private final String methodName;
    private List<Object> arguments = new ArrayList<>();


    ReflectionCall(String methodName, Object... arguments) {
        this.arguments = Arrays.asList(arguments);
        this.methodName = methodName;
    }

    @Override
    public Object actOn(Object object) {
        return safeCallOf(methodName, object, arguments);
    }


    private <T> T safeCallOf(String method, Object object, List<Object> arguments) {
        try {
            return unsafeCallOf(method, object, arguments);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }


    private <T> T unsafeCallOf(String methodName, Object object, List<Object> arguments) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class[] argumentClasses = argumentClasses(arguments);
        Class<?> receiver = receiverOf(methodName, argumentClasses, object);
        Method method = receiver.getDeclaredMethod(methodName, argumentClasses);
        method.setAccessible(true);
        return (T) method.invoke(object, arguments.toArray(new Object[argumentClasses.length]));
    }

    private Class[] argumentClasses(List<Object> arguments) {
        List<Class> argumentTypes = new ArrayList<>();
        for (Object argument : arguments) {
            argumentTypes.add(argument.getClass());
        }
        return argumentTypes.toArray(new Class[arguments.size()]);
    }


    private Class<?> receiverOf(String methodName, Class[] argumentClasses, Object object) throws NoSuchMethodException {
        Class<?> currentClass = object.getClass();
        Method field = null;
        while (field == null) {
            try {
                field = currentClass.getDeclaredMethod(methodName, argumentClasses);
            } catch (NoSuchMethodException e) {
                Class<?> superClass = currentClass.getSuperclass();
                if (superClass == null) {
                    throw e;
                }
                currentClass = superClass;
            }
        }
        return currentClass;
    }
}

class ReflectionRead implements ReflectionOption {

    private final String fieldName;

    ReflectionRead(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public Object actOn(Object object) {
        return safeReadFrom(fieldName, object);
    }

    private <T> T safeReadFrom(String property, Object object) {
        try {
            return unsafeReadFrom(property, object);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    private <T> T unsafeReadFrom(String property, Object object) throws IllegalAccessException, NoSuchFieldException {
        Class<?> receiver = receiverOf(property, object);
        object = receiver.cast(object);
        Field field = receiver.getDeclaredField(property);
        field.setAccessible(true);
        return (T) field.get(object);
    }

    private Class<?> receiverOf(String property, Object object) throws NoSuchFieldException {
        Class<?> currentClass = object.getClass();
        Field field = null;
        while (field == null) {
            try {
                field = currentClass.getDeclaredField(property);
            } catch (NoSuchFieldException e) {
                Class<?> superClass = currentClass.getSuperclass();
                if (superClass == null) {
                    throw e;
                }
                currentClass = superClass;
            }
        }
        return currentClass;
    }
}