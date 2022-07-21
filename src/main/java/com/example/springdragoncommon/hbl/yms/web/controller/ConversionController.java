package com.example.springdragoncommon.hbl.yms.web.controller;

import com.example.springdragoncommon.hbl.yms.web.bean.vo.ConversionVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hanbl
 */
@RestController
@RequestMapping("/yms/conversion/")
public class ConversionController {
    private static Logger logger = LoggerFactory.getLogger(ConversionController.class);

    @RequestMapping("saveConversion")
    public String saveConversion(ConversionVo conversionVo){
        logger.info(String.format("获取到的请求参数为：[%s]",conversionVo));
        return "保存成功！";
    }

    @RequestMapping("saveConversionByJson")
    public String saveConversionByJson(@RequestBody ConversionVo conversionVo){
        logger.info(String.format("获取到的请求参数为：[%s]",conversionVo));
        return "保存成功！";
    }

    @RequestMapping("queryDemo")
    public String queryDemo(@RequestBody String name){
        return "成功！！！！";
    }

}
