package com.example.springdragoncommon.hbl.yms.web.hotupdateconfig.config;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author hanblq
 */
public class ConfigurationParams {
    public final static Map<String, String> configurationMap = new ConcurrentHashMap<>(256);
	public static String DATA_TYPE_String="1";
    public static String DATA_TYPE_LIST_OBJECT="2";
	public static String DATA_TYPE_LIST_String="3";
    public static String MATCHER="[\u4e00-\u9fa5]";
}
