package com.example.springdragoncommon.config.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * @author hanblq
 */
public class MonitorMethodApplicationListener implements ApplicationListener {

    private static Logger logger=LoggerFactory.getLogger(MonitorMethodApplicationListener.class);

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        logger.info(String.format("本次请求信息为：%s",applicationEvent.toString()));
    }
}
