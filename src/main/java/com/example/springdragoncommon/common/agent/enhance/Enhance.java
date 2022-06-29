package com.example.springdragoncommon.common.agent.enhance;

import com.example.springdragoncommon.common.agent.bean.ClassInfo;

import java.lang.reflect.Method;

/**
 * @author hanbl
 */
public interface Enhance {
    boolean matchClass(ClassInfo classInfo);

    void enhanceClass(ClassInfo classInfo);

    boolean matchMethod(ClassInfo classInfo, Method method);

    void enhanceMethod(ClassInfo classInfo,Method method);
}
