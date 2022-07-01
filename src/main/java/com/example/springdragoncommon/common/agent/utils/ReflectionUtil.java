package com.example.springdragoncommon.common.agent.utils;

import org.springframework.util.ClassUtils;

/**
 * @author hanbl
 */
public class ReflectionUtil {

    public static <T> T createInstance(String className){
        try {
            Class<?> aClass = ClassUtils.forName(className,ClassUtils.getDefaultClassLoader());
            return (T)BeanUtils.instantiateClass(aClass);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }

    }
}
