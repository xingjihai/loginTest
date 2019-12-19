package com.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("LogTest")
public class LogTest {
    public static final  Logger logger  =  LoggerFactory.getLogger(LogTest.class );
    public static void main(String[] args) {
        if(logger.isErrorEnabled()){
            logger.error( "类 {}. 发生错误 " , LogTest.class.getName());
        }
        if(logger.isInfoEnabled()){
            logger.info( "类 {}. 发生错误2 " , LogTest.class.getName());
        }
    }
    @RequestMapping("showLog")
    @ResponseBody
    public void showLog(){
        try {
            if(logger.isErrorEnabled()){
                logger.error( "类 {}. 发生错误 " , LogTest.class.getName());
            }
            if(logger.isInfoEnabled()){
                logger.info( "类 {}. 发生错误2 " , LogTest.class.getName());
            }
            throw new RuntimeException("抛出异常");
        } catch (Exception e) {
//            System.out.println(e);
//            System.out.println("=================");
            e.printStackTrace();
        }
    }
}
