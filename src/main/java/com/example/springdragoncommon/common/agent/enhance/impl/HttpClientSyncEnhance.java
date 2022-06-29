package com.example.springdragoncommon.common.agent.enhance.impl;

import com.example.springdragoncommon.common.agent.bean.ClassInfo;
import com.example.springdragoncommon.common.agent.enhance.Enhance;

import java.lang.reflect.Method;

/**
 * @author hanbl
 */
public class HttpClientSyncEnhance implements Enhance {
    @Override
    public boolean matchClass(ClassInfo classInfo) {
        return false;
    }

    @Override
    public void enhanceClass(ClassInfo classInfo) {

    }

    @Override
    public boolean matchMethod(ClassInfo classInfo, Method method) {
        return false;
    }

    @Override
    public void enhanceMethod(ClassInfo classInfo, Method method) {

    }
}
