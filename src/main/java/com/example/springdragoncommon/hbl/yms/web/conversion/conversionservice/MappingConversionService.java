package com.example.springdragoncommon.hbl.yms.web.conversion.conversionservice;


import com.example.springdragoncommon.hbl.yms.web.conversion.utils.ConversionUtils;
import org.springframework.core.convert.converter.Converter;

import java.util.Date;


/**
 * @author hanblq
 */
public class MappingConversionService<T extends Date> implements Converter<String, Date> {
    @Override
    public Date convert(String s) {
        return ConversionUtils.MappingStringToDateMethod(s);
    }
}
