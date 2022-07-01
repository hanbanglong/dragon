package com.example.springdragoncommon.common.agent.utils;

import com.example.springdragoncommon.common.agent.bean.TransformerDefine;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.lang.instrument.ClassFileTransformer;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author hanbl
 */
public class AgentConfigUtils {

    /**
     * 配置信息，即用于配置相关的过滤信息进行使用的
     */
    public static final String configName = "yms-agent-config.yml";

    private Map<String, Object> config;
    private Map<String, TransformerDefine> transformerDefineMap = new ConcurrentHashMap<>();
    private List<String> transformerNames = new ArrayList<>();

    public AgentConfigUtils(Map<String, Object> config) {
        this.config = config;
        this.init(this.config);
    }

    /**
     * 初始化相关参数值；
     * @param config
     */
    private void  init(Map<String, Object> config){
        if (!CollectionUtils.isEmpty(config)){
            Map<String, Object> transformers = AgentMapUtils.getValue(config, "dragon.common.agent.transformer.transformers");
            parseTransformer(transformers);
        }
    }
    private List<String> parseEnhances(Map<String, Object> enhances) {
        Iterator<Map.Entry<String, Object>> iterator1 = enhances.entrySet().iterator();
        ArrayList<String> enhancesNameList=null;
        while (iterator1.hasNext()){
            Map.Entry<String, Object> next = iterator1.next();
            String value = String.valueOf(next.getValue());
            String[] split = value.split("-");
            List<String> enhancesNames = Arrays.asList(split);
            enhancesNameList = new ArrayList<>(enhancesNames);
            if (!CollectionUtils.isEmpty(enhancesNameList)){
                enhancesNameList.removeIf(p->StringUtils.isEmpty(p));
            }
        }
        return enhancesNameList;
    }

    private void parseTransformer(Map<String, Object> transformers) {
        if (transformers != null && transformers.size() > 0) {
            Iterator<Map.Entry<String, Object>> iterator = transformers.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Object> entry = iterator.next();
                this.transformerNames.add(entry.getKey());
                this.transformerDefineMap.put(entry.getKey(), TransformerDefine.build(entry.getKey(), (Map<String, Object>) entry.getValue()));
            }
            TransformerDefine httpTransformer = transformerDefineMap.get("httptransformer");
            ClassFileTransformer  instance= httpTransformer.getInstance();
           /* if (instance instanceof AgentClassTransformer){
                AgentClassTransformer agentClassTransformer=(AgentClassTransformer) instance;
                //agentClassTransformer.setEnhances(agentClassTransformer.getEnhances());
            }*/
        }
    }


    public static AgentConfigUtils parseConfig(String file) throws IOException {
        return new AgentConfigUtils(parse(file));
    }

    /**
     * 读取路径下自定义的配置文件
     * @param file
     * @return
     * @throws IOException
     */
    public static Map<String, Object> parse(String file) throws IOException {
        //创建配置文件类
        Yaml yaml = new Yaml();
        URL url = AgentFileUtil.getFile(file);
        InputStream inputStream =null;
        try {
            inputStream = url.openStream();
            return yaml.load(inputStream);
        }finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }


    public List<TransformerDefine> transformers() {
        List<TransformerDefine> list = new ArrayList<>();

        Set<Map.Entry<String, TransformerDefine>> sets = this.transformerDefineMap.entrySet();
        Iterator<Map.Entry<String, TransformerDefine>> it = sets.iterator();
        while (it.hasNext()) {
            Map.Entry<String, TransformerDefine> entry = it.next();
            if (this.transformerNames.contains(entry.getKey())) {
                list.add(entry.getValue());
            }
        }
        return list;
    }
}
