package com.example.springdragoncommon.hbl.yms.web.hotupdateconfig.task;

import com.example.springdragoncommon.hbl.yms.web.hotupdateconfig.config.ConfigurationParams;
/*import org.slf4j.Logger;
import org.slf4j.LoggerFactory;*/
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Qualifier;
///import org.springframework.cloud.endpoint.event.RefreshEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
//import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

/**
 *
 */
//@Component
public class ConfigurationTask implements ApplicationContextAware {

    //private static Logger log = LoggerFactory.getLogger(ConfigurationTask.class);

    private ApplicationContext context;

    @Resource
    private ConfigurableEnvironment env;

/*    @Qualifier("onedbJdbcTemplate")
    @Resource
    private JdbcTemplate jdbcTemplate;*/


//    @Scheduled(cron = "*/5 * * * * ?")
//    public void schedule() {
//        log.info("======================================定时任务1====================================");
//        try {
//            String sql = "select key,value from db where type ='1' and dl='0'";
//            List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
//            List<String> updateList = new ArrayList<>();
//            if (!CollectionUtils.isEmpty(maps)) {
//                maps.stream().forEach(p -> {
//                    String configuration_key = String.valueOf(p.get("key"));
//                    String configuration_value = String.valueOf(p.get("value"));
//                    updateList.add(configuration_key);
//                    ConfigurationParams.configurationMap.put(configuration_key, configuration_value);
//                });
//            }
//            Map<String, String> propertyMap = ConfigurationParams.configurationMap;
//            if (!propertyMap.isEmpty()) {
//                refresh(propertyMap);
//                ConfigurationParams.configurationMap.clear();
//            }
//            if (!CollectionUtils.isEmpty(updateList)) {
//                StringBuilder builder = new StringBuilder();
//                builder.append("(");
//                updateList.stream().forEach(p -> {
//                    builder.append("'" + p + "',");
//                });
//                builder.deleteCharAt(builder.length() - 1);
//                builder.append(")");
//                //修改配置状态
//                String updateSql = "update db set type='0' where key in " + builder.toString();
//                jdbcTemplate.update(updateSql);
//            }
//        } catch (Exception e) {
//            log.error("定时拉取热更新配置触发刷新事件异常：" + e.getMessage());
//        }
//    }
//
//    private void refresh(Map<String, String> propertyMap) {
//        MutablePropertySources propertySources = env.getPropertySources();
//        try {
//            boolean updateCheck = false;
//            PropertiesPropertySource dbPropertySource = null;
//            for (PropertySource<?> source : propertySources) {
//                if ("dbPropertySource".equals(source.getName())) {
//                    Map<String, Object> propertyMapNew = (Map<String, Object>) source.getSource();
//                    propertyMapNew.putAll(propertyMap);
//                    propertySources.remove(source.getName());
//                    Properties properties = new Properties();
//                    properties.putAll(propertyMapNew);
//                    dbPropertySource = new PropertiesPropertySource("dbPropertySource", properties);
//                    updateCheck = true;
//                }
//            }
//            if (updateCheck) {
//                propertySources.remove("dbPropertySource");
//            } else {
//                Properties properties = new Properties();
//                properties.putAll(propertyMap);
//                dbPropertySource = new PropertiesPropertySource("dbPropertySource", properties);
//            }
//            String name = null;
//            boolean flag = false;
//            Pattern p = compile("^applicationConfig.*");
//            for (PropertySource<?> source : propertySources) {
//                if (p.matcher(source.getName()).matches()) {
//                    name = source.getName();
//                    flag = true;
//                    log.info("Find propertySources ".concat(name));
//                    break;
//                }
//            }
//            if (flag) {
//                propertySources.addBefore(name, dbPropertySource);
//            } else {
//                propertySources.addFirst(dbPropertySource);
//            }
//        } catch (Exception e) {
//            log.error("定时拉取热更新配置刷新异常：" + e.getMessage());
//        }
//        context.publishEvent(new RefreshEvent(this, null, "线程上下文更新"));
//    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
