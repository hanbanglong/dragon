package com.example.springdragoncommon.common.agent.bean;

import com.example.springdragoncommon.common.agent.utils.AgentMapUtils;
import com.example.springdragoncommon.common.agent.utils.BeanUtils;
import com.example.springdragoncommon.common.agent.utils.ReflectionUtil;
import org.springframework.util.StringUtils;

import java.lang.instrument.ClassFileTransformer;
import java.util.*;

/**
 * @author hanbl
 */
public class TransformerDefine {
    private String name;

    private String className;

    private boolean enable;

    private boolean canRetraceForm;

    private Map<String, Object> arguments;

    private ClassFileTransformer instance;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public boolean isCanRetraceForm() {
        return canRetraceForm;
    }

    public void setCanRetraceForm(boolean canRetraceForm) {
        this.canRetraceForm = canRetraceForm;
    }

    public Map<String, Object> getArguments() {
        return arguments;
    }

    public void setArguments(Map<String, Object> arguments) {
        this.arguments = arguments;
    }

    public ClassFileTransformer getInstance() {
        if (this.instance!=null){
            return this.instance;
        }
        synchronized (this){
            if (this.instance!=null){
                return this.instance;
            }
            this.instance = ReflectionUtil.createInstance(this.className);
            if (this.arguments != null) {
                Iterator<Map.Entry<String, Object>> it = this.arguments.entrySet().iterator();
                try {
                    while (it.hasNext()) {
                        Map.Entry<String, Object> entry = it.next();
                        String enhances = String.valueOf(entry.getValue());
                        String[] enhanceList = enhances.split("-");
                        List<String> enhancesNames = Arrays.asList(enhanceList);
                        List<String> list=new ArrayList<>(enhancesNames);
                        list.removeIf(e-> StringUtils.isEmpty(e));
                        BeanUtils.setPropertyByMethod(this.instance, entry.getKey(), list);
                    }
                } catch (Exception e) {
                    throw new IllegalArgumentException(e);
                }
            }
        }
        return instance;
    }

    public void setInstance(ClassFileTransformer instance) {
        this.instance = instance;
    }


    public static TransformerDefine build(String name, Map<String, Object> attributes) {
        TransformerDefine transformerDefine = new TransformerDefine();
        transformerDefine.setName(name);
        transformerDefine.setClassName(AgentMapUtils.getValue(attributes, "class"));
        transformerDefine.setEnable(AgentMapUtils.getValue(attributes, "enable"));
        transformerDefine.setCanRetraceForm(AgentMapUtils.getValue(attributes, "canRetransform"));
        transformerDefine.setArguments(AgentMapUtils.getValue(attributes, "arguments"));
        return transformerDefine;
    }
}
