package com.example.springdragoncommon.hbl.yms.web.hotupdateconfig.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
//import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

//@Configuration
//@DependsOn("")
public class LoadConfigFromDb {
    private static Logger log= LoggerFactory.getLogger(LoadConfigFromDb.class);

    @Resource
    private ConfigurableEnvironment env;

   /* @Qualifier("dbjdbc")
    @Resource
    private JdbcTemplate jdbcTemplate;*/

    //@PostConstruct
   /* public void initializePropertySourceFromDb() {
        MutablePropertySources propertySources = env.getPropertySources();
        Map<String, Object> propertyMap = new ConcurrentHashMap<>(256);
        try {
            String sql = "select key,value from db where dl='0'";
            List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
            if (!CollectionUtils.isEmpty(maps)){
                maps.stream().forEach(p->{
                    String configuration_key = String.valueOf(p.get("key"));
                    String configuration_value = String.valueOf(p.get("value"));
                    propertyMap.put(configuration_key,configuration_value);
                });
            }
            Properties properties = new Properties();
            properties.putAll(propertyMap);
            PropertiesPropertySource dbPropertySource = new PropertiesPropertySource("dbPropertySource", properties);
            Pattern p = compile("^applicationConfig.*");
            String name = null;
            boolean flag = false;
            for (PropertySource<?> source : propertySources) {
                if (p.matcher(source.getName()).matches()) {
                    name = source.getName();
                    flag = true;
                    log.info("Find propertySources ".concat(name));
                    break;
                }
            }
            if(flag) {
                propertySources.addBefore(name, dbPropertySource);
            } else {
                propertySources.addFirst(dbPropertySource);
            }
            ConfigurationParams.configurationMap.clear();

        }catch (Exception e){

        }

    }*/
}
