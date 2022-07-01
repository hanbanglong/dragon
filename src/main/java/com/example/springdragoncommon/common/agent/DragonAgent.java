package com.example.springdragoncommon.common.agent;

import com.example.springdragoncommon.common.agent.bean.TransformerDefine;
import com.example.springdragoncommon.common.agent.utils.AgentConfigUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.util.Iterator;
import java.util.List;

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
        AgentConfigUtils agentConfigUtils = AgentConfigUtils.parseConfig(AgentConfigUtils.configName);
        List<TransformerDefine> list = agentConfigUtils.transformers();
        Iterator<TransformerDefine> iterator = list.iterator();
        while (iterator.hasNext()) {
            TransformerDefine define = iterator.next();
            if (define.isEnable()) {
                instrumentation.addTransformer(define.getInstance(), define.isCanRetraceForm());
                logger.info(" +++++++++ transformer: " + define.getClassName());
            }
        }
    }
}
