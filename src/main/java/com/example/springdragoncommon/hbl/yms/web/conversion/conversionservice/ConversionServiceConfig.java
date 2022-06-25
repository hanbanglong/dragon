package com.example.springdragoncommon.hbl.yms.web.conversion.conversionservice;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author hanbl
 * 这里需要使用ConverterFactory，将其注入到FormatterRegistry才能生效，其他方式目前测试还未生效过
 */
@Configuration
@EnableWebMvc
public class ConversionServiceConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        //注册ConverterFactory
        registry.addConverterFactory(new MappingStringToNumberConverterFactory());
        WebMvcConfigurer.super.addFormatters(registry);
    }
}
