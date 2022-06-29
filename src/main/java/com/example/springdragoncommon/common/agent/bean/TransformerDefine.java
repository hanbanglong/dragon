package com.example.springdragoncommon.common.agent.bean;

import com.example.springdragoncommon.common.agent.utils.AgentMapUtils;

import java.lang.instrument.ClassFileTransformer;
import java.util.Map;

/**
 * @author hanbl
 */
public class TransformerDefine {
    private String name;

    private String claszName;

    private boolean enable;

    private boolean canRetransform;

    private Map<String, Object> arguments;

    private ClassFileTransformer instance;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClaszName() {
        return claszName;
    }

    public void setClaszName(String claszName) {
        this.claszName = claszName;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public boolean isCanRetransform() {
        return canRetransform;
    }

    public void setCanRetransform(boolean canRetransform) {
        this.canRetransform = canRetransform;
    }

    public Map<String, Object> getArguments() {
        return arguments;
    }

    public void setArguments(Map<String, Object> arguments) {
        this.arguments = arguments;
    }

    public ClassFileTransformer getInstance() {
        return instance;
    }

    public void setInstance(ClassFileTransformer instance) {
        this.instance = instance;
    }


    public static TransformerDefine build(String name, Map<String, Object> attributes) {
        TransformerDefine transformerDefine = new TransformerDefine();
        transformerDefine.setName(name);
        transformerDefine.setClaszName(AgentMapUtils.getValue(attributes, "class"));
        transformerDefine.setEnable(AgentMapUtils.getValue(attributes, "enable"));
        transformerDefine.setCanRetransform(AgentMapUtils.getValue(attributes, "canRetransform"));
        transformerDefine.setArguments(AgentMapUtils.getValue(attributes, "arguments"));
        return transformerDefine;
    }
}
