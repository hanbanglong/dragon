package com.example.springdragoncommon.hbl.yms.spi.initializer;

import com.example.springdragoncommon.hbl.yms.spi.service.SpiMethodService;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hanbl
 */
public class SpiApplicationContextInitializer implements ApplicationContextInitializer {

    private static Logger logger= LoggerFactory.getLogger(SpiApplicationContextInitializer.class);


    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        //获取当前上下文的类加载器
        ClassLoader classLoader = configurableApplicationContext.getClassLoader();
        List<String> spiMethodServiceNameList = SpringFactoriesLoader.loadFactoryNames(SpiMethodService.class, classLoader);
        if (!StringUtils.isEmpty(spiMethodServiceNameList)){
            List<SpiMethodService> spiMethodServiceList=new ArrayList<>(spiMethodServiceNameList.size());
            spiMethodServiceNameList.stream().forEach(p->{
                try {
                    Class<?> aClass = ClassUtils.forName(p, classLoader);
                    Constructor<?> declaredConstructor = aClass.getDeclaredConstructor();
                    SpiMethodService spiMethodService = (SpiMethodService) BeanUtils.instantiateClass(declaredConstructor);
                    spiMethodServiceList.add(spiMethodService);
                } catch (ClassNotFoundException | NoSuchMethodException e) {
                    logger.error(String.format("获取spi扩展实现的接口异常",e));
                }
            });
            //将集合注册到工厂中
            configurableApplicationContext.getBeanFactory().registerSingleton("spiMethodServices",spiMethodServiceList);
        }
    }
}
