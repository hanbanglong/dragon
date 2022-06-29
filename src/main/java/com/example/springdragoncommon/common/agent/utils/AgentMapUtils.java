package com.example.springdragoncommon.common.agent.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author hanbl
 */
public class AgentMapUtils {


    public static final <T> T getValue(Map<String, Object> map, String key) {
        return getValue(map, "\\.", key);
    }

    /**
     * 支持层级获取key值
     * @param map
     * @param splitterTag
     * @param key
     * @param <T>
     * @return
     */
    public static <T> T getValue(Map<String, Object> map, final String splitterTag, String key) {
        if (map==null){
            return null;
        }
        List<String> keys = Arrays.asList(key.split(splitterTag));
        Collections.reverse(keys);
        int max = keys.size();
        Object object=null;
        while(max>1){
            object = map.get(keys.get(max - 1));
            if (object==null){
                return null;
            }
            if (!(object instanceof Map)){
                throw new IllegalArgumentException("");
            }
            map = (Map<String, Object>) object;
            --max;
        }
        if (map == null) {
            return null;
        }
        Object res = map.get(keys.get(0));
        return res != null ? (T) res : null;
    }
}
