package org.gurpsdomain.adapters.output.converter;

import org.junit.Test;
import org.omg.CORBA.Any;
import org.omg.CORBA.Object;

import javax.xml.ws.wsaddressing.W3CEndpointReferenceBuilder;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import static org.gurpsdomain.adapters.output.converter.ReflectionConstructor.construct;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ReflectionTest {
    public static final int ANY_INT_VALUE = 37;
    @Test
    public void shouldCreateObjectsFromConstructorAndLiterals() throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        SingleValue single = construct(SingleValue.class).with(ANY_INT_VALUE).build();

        assertThat(single.value, is(ANY_INT_VALUE));
    }
}

class ReflectionConstructor<T> {
    public static <U> ReflectionConstructor<U> construct(Class<U> aClass) {
        return new ReflectionConstructor(aClass);
    }

    private List<ConstructorArgument<?>> constructorArguments = new ArrayList<ConstructorArgument<?>>();
    private Class<T> aClass;

    public ReflectionConstructor(Class<T> aClass) {
        this.aClass = aClass;
    }

    public ReflectionConstructor<T> with(int aValue) {
        constructorArguments.add(new ConstructorArgument<>(aValue, Integer.TYPE));
        return this;
    }

    public T build() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        ConstructorArgument<?> constructorArgument = constructorArguments.get(0);
        Constructor<T> constructor = aClass.getConstructor(constructorArgument.type);
        T object = (T) constructor.newInstance(constructorArgument.value);
        return object;
    }
}

class ConstructorArgument<T> {
    public final Class<T> type;
    public final T value;

    public ConstructorArgument(T value, Class<T> type){
        this.value = value;
        this.type = type;
    }
}

class SingleValue {
    public int value;

    public SingleValue(int value) {
        this.value = value;
    }
}
