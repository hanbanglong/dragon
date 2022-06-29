package com.example.springdragoncommon.common.agent.utils;

import java.net.URL;

/**
 * @author hanblq
 */
public class AgentFileUtil {

    public static URL getFile(String name){
        return getFile(AgentFileUtil.class.getClassLoader(),name);
    }
    public static URL getFile(ClassLoader loader,String name){
        return loader.getResource(name);
    }
}
