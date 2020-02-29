package com.lhj.test;

import com.lhj.blog.configuration.MainConfigurationBean;
import com.lhj.common.support.JsonSupport;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConfigurationPropertiesTest {

    @Autowired
    MainConfigurationBean mainConfigurationBean;

    @Autowired
    JsonSupport jsonSupport;

    /*日志*/
    Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public  void getMainConfigurationProperties(){

        String result = jsonSupport.toJson(mainConfigurationBean);
        System.err.println(result);

    }

    @Test
    public  void  loggingTest(){

        //日志的级别；
        //由低到高   trace<debug<info<warn<error
        //可以调整输出的日志级别；日志就只会在这个级别以以后的高级别生效
        logger.trace("这是trace日志...");
        logger.debug("这是debug日志...");
        //SpringBoot默认给我们使用的是info级别的，没有指定级别的就用SpringBoot默认规定的级别；root级别
        logger.info("这是info日志...");
        logger.warn("这是warn日志...");
        try {
            String[] str = new String[1];
            String a = str[2];
        }catch (Exception e){
            e.printStackTrace();
            logger.error("异常日志",e);
        }
    }

}
