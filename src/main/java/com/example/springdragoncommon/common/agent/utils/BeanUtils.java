package com.example.springdragoncommon.common.agent.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author hanbl
 */
public class BeanUtils {

    public static <T> T instantiateClass(Class<T> aClass) throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        if (aClass.isInterface()){
            throw new IllegalArgumentException("Specified class is an interface");
        }
        Constructor<T> declaredConstructor = aClass.getDeclaredConstructor();
        return instantiateClass(declaredConstructor);
    }
    public static <T> T instantiateClass(Constructor<T> ctor, Object... args) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        makeAccessible(ctor);
        return ctor.newInstance(args);
    }

    public static void makeAccessible(Constructor<?> ctor) {
        if ((!Modifier.isPublic(ctor.getModifiers()) || !Modifier.isPublic(ctor.getDeclaringClass().getModifiers())) && !ctor.isAccessible()) {
            ctor.setAccessible(true);
        }
    }

    public static void setPropertyByMethod(Object obj, String name, List<String> value) {
        PropertyDescriptor propertyDescriptor = null;
        try {
            propertyDescriptor = new PropertyDescriptor(name, obj.getClass());
            List<String> convVal =value;
            Class<?>[] types = propertyDescriptor.getWriteMethod().getParameterTypes();
            if((value instanceof Collection) && (types.length > 0)){
                if(types[0].equals(Set.class)){
                    List<String> temp = new ArrayList<>();
                    temp.addAll(convVal);
                    convVal = temp;
                }
            }
            convVal = convVal.stream().distinct().collect(Collectors.toList());
            propertyDescriptor.getWriteMethod().invoke(obj, convVal);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
}
