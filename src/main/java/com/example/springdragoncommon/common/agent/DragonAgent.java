package com.example.springdragoncommon.common.agent;

import com.example.springdragoncommon.common.agent.utils.AgentConfigUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.instrument.Instrumentation;

/**
 * @author hanblq
 */
public class DragonAgent {

    /**
     * 日志
     */
    private static Logger logger= LoggerFactory.getLogger(DragonAgent.class);

    /**
     *
     * @param agentArgs
     * @param instrumentation 探针
     */
    public static void premain(String agentArgs, Instrumentation instrumentation) throws IOException {
        AgentConfigUtils.parseConfig(AgentConfigUtils.configName);
    }
}
