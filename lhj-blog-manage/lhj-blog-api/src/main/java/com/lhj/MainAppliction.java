package com.lhj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;


/*
*SpringBoot推荐给容器中添加组件的方式；推荐使用全注解的方式
    1、配置类@Configuration------>Spring配置文件
    2、使用@Bean给容器中添加组件

**/
//@ImportResource(locations = {"classpath:beans.xml"})//想让Spring的配置文件生效，加载进来；@ImportResource标注在一个配置类上 推荐使用注解版注册组件

/**
 *  @SpringBootApplication 来标注一个主程序类，说明这是一个Spring Boot应用
 *  scanBasePackages 扫描包路径
 */
@SpringBootApplication(scanBasePackages = "com.lhj")
public class MainAppliction {

    public static void main(String[] args) {
        // Spring boot 应用启动
        SpringApplication.run(MainAppliction.class,args);
    }

}
