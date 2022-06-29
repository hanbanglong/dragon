package com.example.springdragoncommon.common.agent.utils;

import com.example.springdragoncommon.common.agent.bean.TransformerDefine;
import org.springframework.util.CollectionUtils;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
    private List<String> enhances = new ArrayList<>();

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


    private void parseTransformer(Map<String, Object> transformers) {
        if (transformers != null && transformers.size() > 0) {
            Iterator<Map.Entry<String, Object>> iterator = transformers.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Object> entry = iterator.next();
                this.enhances.add(entry.getKey());
                this.transformerDefineMap.put(entry.getKey(), TransformerDefine.build(entry.getKey(), (Map<String, Object>) entry.getValue()));
            }
        }
    }


    public static AgentConfigUtils parseConfig(String file) throws IOException {
        return new AgentConfigUtils(parse(file));
    }

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
}
