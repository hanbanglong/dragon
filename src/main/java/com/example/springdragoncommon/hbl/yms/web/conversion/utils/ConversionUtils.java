package com.example.springdragoncommon.hbl.yms.web.conversion.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
/**
 * @author hanbl
 */
public class ConversionUtils {

    private static Logger logger= LoggerFactory.getLogger(ConversionUtils.class);
    /**
     * 日期格式
     */
    public static SimpleDateFormat smt = new SimpleDateFormat("yyyy-MM-dd HH:mm:sss");

    /**
     * 将各种格式的string类型的日期转换成Date类型
     * @param dateString 字符串类型的时间戳
     * @return
     */
    public static Date MappingStringToDateMethod(String dateString){
        Date date=null;
        boolean mark=false;
        if (StringUtils.isEmpty(dateString)){
            return null;
        }
        try {
            LocalDateTime parse = LocalDateTime.parse(dateString);
            Instant instant = parse.atZone(ZoneId.systemDefault()).toInstant();
            date=Date.from(instant);
            mark=false;
        }catch (Exception e){
            mark=true;
        }
        if (mark){
            try {
                date= new Date(dateString);
                mark=false;
            } catch (Exception parseException) {
                mark=true;
            }
        }
        if (mark){
            try {
                date= new Date(Long.valueOf(dateString));
                mark=false;
            } catch (Exception parseException) {
                mark=true;
            }
        }
        if (mark){
            try {
                date = smt.parse(dateString);
                mark=false;
            } catch (Exception e) {
            }
        }
        if (mark){
        logger.error("字符串时间转换异常");
        }
        return date;
    }
}
