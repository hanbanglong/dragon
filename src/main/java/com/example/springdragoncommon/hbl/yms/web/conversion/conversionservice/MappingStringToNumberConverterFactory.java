package com.example.springdragoncommon.hbl.yms.web.conversion.conversionservice;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import java.util.Date;
/**
 *
 * @author hanbl
 */
public class MappingStringToNumberConverterFactory implements ConverterFactory<String, Date> {
    @Override
    public <T extends Date> Converter<String, T> getConverter(Class<T> aClass) {
        return new MappingConversionService();
    }
}
