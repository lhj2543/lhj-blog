package com.lhj.consumer;

import com.lhj.ribbon.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;


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
@SpringBootApplication(scanBasePackages = "com.lhj",exclude= {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@EnableEurekaClient //注册进eureka服务中
@RibbonClient(name = "LHJ-BLOG-MANAGE",configuration = MySelfRule.class)
public class ConsumerMainAppliction {

    public static void main(String[] args) {
        // Spring boot 应用启动
        SpringApplication.run(ConsumerMainAppliction.class,args);
    }

}
